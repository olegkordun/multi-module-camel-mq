package me.kordun.test.integration;

import me.kordun.test.util.DataSetUtil;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import static org.dbunit.Assertion.assertEqualsIgnoreCols;

public abstract class DbUnitTest {
    private static final Logger log = LoggerFactory.getLogger(DbUnitTest.class);

    private static final String defaultTestDataSetFile = "/test-data/xml/dataset.xml";
    private static IDataSet defaultDataset = loadDatasetFile(defaultTestDataSetFile);

    public void setUpDatabase() {
        setUpDatabase(defaultTestDataSetFile);
    }

    /**
     * Setup database with given dataset file
     * @param dataSetFile - file
     */
    public void setUpDatabase(String dataSetFile) {
        log.info("Setting up the database");

        try {
            IDataSet dataSet = loadDatasetFile(dataSetFile);
            IDatabaseConnection connection = getConnection();

            DatabaseOperation.DELETE_ALL.execute(connection, defaultDataset);
            DatabaseOperation.INSERT.execute(connection, dataSet);

        } catch (final Exception e) {
            log.error("Could not init data", e);
            throw new IllegalStateException("Could not init data", e.fillInStackTrace());
        }

        log.info("Successfully populated DB from {}", dataSetFile);
    }

    public void tearDown() {
        //see setUpDatabase()
    }

    /**
     * Assert database ignoring ID column
     * @param expectedDataSet - file
     */
    public void assertDatabaseIgnoreIdColumn(String expectedDataSet) {
        String[] columnsToIgnore = {"ID"};
        assertDatabase(expectedDataSet, columnsToIgnore);
    }

    /**
     * Assert database ignoring given list of columns
     * @param expectedDataSet - file
     * @param ignoreCols - column names array
     */
    public void assertDatabase(String expectedDataSet, String[] ignoreCols) {
        assertDatabase(expectedDataSet, null, ignoreCols);
    }

    /**
     * Assert database with specific tables and columns
     * @param expectedDataSet - file
     * @param tables - table and select SQL map
     * @param columnsToIgnore - column names array
     */
    public void assertDatabase(String expectedDataSet, Map<String, String> tables, String[] columnsToIgnore) {

        log.info("Comparing database with dataset {}", expectedDataSet);
        IDataSet expected = loadDatasetFile(expectedDataSet);
        try {
            IDataSet actual;
            if (tables != null) {
                QueryDataSet dataSet = new QueryDataSet(getConnection());
                for (Map.Entry<String, String> entry : tables.entrySet()) {
                    dataSet.addTable(entry.getKey(), entry.getValue());
                }
                actual = dataSet;
            } else {
                actual = getConnection().createDataSet();
            }

            for (String table : expected.getTableNames()) {
                ITable expectedTable = expected.getTable(table);
                ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actual.getTable(table),
                        expectedTable.getTableMetaData().getColumns());
                assertEqualsIgnoreCols(expectedTable, filteredActualTable, columnsToIgnore);
            }
        } catch (final Exception e) {
            throw new IllegalStateException("Could not compare Database with file", e);
        }
        log.info("Successfully asserted that database equals dataset {}", expectedDataSet);
    }

    private IDatabaseConnection getConnection() throws Exception {
        log.info("Configuring connection");
        //TODO:Move JDBC Url to Properties
        Connection conn = DriverManager
                .getConnection("jdbc:hsqldb:hsql://localhost/test", "sa", "");

        IDatabaseConnection connection = new DatabaseConnection(conn);
        DatabaseConfig dbConfig = connection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
        return connection;
    }

    /**
     * Create dataset with given filename
     * @param fileName -file
     */
    public void createDataset(String fileName) {
        log.info("Create dataset file from database");
        try {
            DataSetUtil.createFullDataSet(getConnection(), fileName);
        } catch (final Exception e) {
            throw new IllegalStateException("Could not clean up the database", e);
        }
        log.info("Successfully created dataset from database");
    }

    /**
     * Load dataset from file
     * @param dataSetFile file
     * @return
     */
    private static IDataSet loadDatasetFile(String dataSetFile) {
        log.info("Loading dataset file {}", dataSetFile);
        ClassPathResource resource = new ClassPathResource(dataSetFile);
        if (!resource.exists()) {
            throw new IllegalStateException("Cannot load resource from " + dataSetFile);
        }

        CachedDataSet dst = new CachedDataSet();
        try (InputStream is = resource.getInputStream()) {
            FlatXmlProducer producer = new FlatXmlProducer(new InputSource(is), false);

            producer.setConsumer(dst);
            producer.produce();
        } catch (IOException | DataSetException e) {
            log.error("Cannot set dataset from " + dataSetFile, e);
        }
        log.info("Dataset loaded from file");
        return dst;
    }
}
