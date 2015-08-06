package me.kordun.test.util;


import org.dbunit.database.*;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;
import java.io.FileOutputStream;
import java.util.Random;

public class DataSetUtil {
    public static void createFullDataSet(IDatabaseConnection connection, String filename) throws Exception{

        ITableFilter filter = new DatabaseSequenceFilter(connection);
        IDataSet fullDataSet = new FilteredDataSet(filter, connection.createDataSet());
        Long rand = new Random().nextLong();

        String name = filename != null ? filename : rand + "dataset.xml";
        FlatXmlWriter datasetWriter = new FlatXmlWriter(new FileOutputStream(name));
        datasetWriter.setDocType("dataset.dtd");
        datasetWriter.write(fullDataSet);

        FlatDtdDataSet.write(fullDataSet, new FileOutputStream("dataset.dtd"));
        connection.close();
    }

    public static  void createDataSet(IDatabaseConnection connection) throws Exception
    {
        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable("TABLE", "SELECT * FROM TABLE");

        FlatDtdDataSet.write(dataSet, new FileOutputStream("dataset.dtd"));
        FlatXmlDataSet.write(dataSet, new FileOutputStream("-DataSet.xml"));

        connection.close();
    }

}
