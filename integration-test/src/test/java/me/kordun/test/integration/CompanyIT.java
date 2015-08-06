package me.kordun.test.integration;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.jayway.restassured.RestAssured.given;

public class CompanyIT extends BaseIT {
    private static final Logger log = LoggerFactory.getLogger(CompanyIT.class);
    private static String jSession;

    @Override
    public void setUpDatabase() {
        super.setUpDatabase("/test-data/xml/only-users-dataset.xml");
    }

    @Before
    public void before() {
        setUpDatabase();
        log.info("login before test");
        Response response = given().
                param("username", "admin").
                param("password", "123").
                expect()
                .statusCode(200)
                .cookie("JSESSIONID")
                .log().ifError()
                .when()
                .post("/login")
                .andReturn();
        jSession = response.getCookie("JSESSIONID");
    }

    @Test
    public void testSuccessCreate() throws Exception {
        log.info("testSuccessCreate");
        String json = "{" +
                "\"status\":\"ACTIVE\"," +
                "\"name\":\"NAME\"," +
                "\"phone\":\"123123\"," +
                "\"email\":\"email@asda.ru\"," +
                "\"info\":\"info\"," +
                "\"details\":\"details\"" +
                ",\"category\":\"HIGHT_RISK\"}\n";

        given().request().body(json)
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON).
                log().everything().
                expect()
                .statusCode(HttpStatus.SC_CREATED)
                .log().ifError()
                .when()
                .post("/companies").then().log().everything();

        //createDataset();
        assertDatabaseIgnoreIdColumn("test-data/xml/expected/createCompanyExpected.xml");
    }

    @Test
    public void testGetCompanyFromDatabase() throws Exception {
        log.info("testGetCompanyFromDatabase");
        super.setUpDatabase("/test-data/xml/one-company.xml");
        Response response =
                given().request()
                        .cookie("JSESSIONID", jSession)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .expect()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .when()
                        .get("/companies/1")
                        .andReturn();

        String expected = "{" +
                "    'id': 1," +
                "    'status': 'ACTIVE'," +
                "    'name': 'Some NAME'," +
                "    'phone': '123123'," +
                "    'email': 'email@email.ru'," +
                "    'info': 'Some info'," +
                "    'details': 'Some details'," +
                "    'category': 'HIGHT_RISK'" +
                "}";

        JSONAssert.assertEquals(expected, response.asString(), false);
    }

    @Test
    public void testUpdate() throws Exception {
        log.info("testUpdate");

        String update = "{" +
                "\"status\":\"BLOCKED\"," +
                "\"name\":\"NAME new\"," +
                "\"phone\":\"123123 new\"," +
                "\"email\":\"new@new.ru\"," +
                "\"info\":\"info new\"," +
                "\"details\":\"details new\"" +
                ",\"category\":\"HIGHT_RISK\"}\n";


        given().request()
                .body(update)
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON).
                expect()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .when()
                .put("/companies/1").then().log().everything();

        //createDataset();
        assertDatabaseIgnoreIdColumn("test-data/xml/expected/updateCompanyExpected.xml");
    }


    @Test
    public void testGetCompanyListFromDatabase() throws Exception {
        log.info("testGetCompanyFromDatabase");
        super.setUpDatabase("/test-data/xml/three-companies.xml");
        Response response =
                given().request()
                        .cookie("JSESSIONID", jSession)
                        .contentType(ContentType.JSON)
                        .log().everything()
                        .expect()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .when()
                        .get("/companies/")
                        .andReturn();

        String expected = "[" +
                "    {" +
                "        'id': 1," +
                "        'status': 'ACTIVE'," +
                "        'name': 'Some one NAME'," +
                "        'phone': '123123 one'," +
                "        'email': 'emailone@email.ru'," +
                "        'info': 'Some info one'," +
                "        'details': 'Some details one'," +
                "        'category': 'LOW_RISK'" +
                "    }," +
                "    {" +
                "        'id': 2," +
                "        'status': 'ACTIVE'," +
                "        'name': 'Some NAME two'," +
                "        'phone': '123123 two'," +
                "        'email': 'emailtwo@email.ru'," +
                "        'info': 'Some info two'," +
                "        'details': 'Some details two'," +
                "        'category': 'HIGHT_RISK'" +
                "    }," +
                "    {" +
                "        'id': 3," +
                "        'status': 'BLOCKED'," +
                "        'name': 'Some NAME 3'," +
                "        'phone': '11231231'," +
                "        'email': 'email3@email.ru'," +
                "        'info': 'Some info 3'," +
                "        'details': 'Some details 3'," +
                "        'category': 'HIGHT_RISK'" +
                "    }" +
                "]";

        JSONAssert.assertEquals(expected, response.asString(), false);
    }

    @Test
    public void testWrongEmailCreate() throws Exception {
        log.info("testWrongEmailCreate");

        String json = "{\"id\":\"a6c13751-107f-4625-b68e-c537316a9706\"," +
                "\"status\":\"ACTIVE\",\"name\":\"NAME\",\"phone\":\"123123\"," +
                "\"email\":\"wrong\"," +
                "\"info\":\"info\"," +
                "\"details\":\"details\"" +
                ",\"category\":\"HIGHT_RISK\"}\n";

        given().request().body(json)
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON).
                log().everything().
                expect()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().ifError()
                .when()
                .post("/companies").then().log().everything();
    }

    @Test
    public void testWrongNameCreate() throws Exception {
        log.info("testWrongNameCreate");

        given().request().body("{" +
                "                \"name\": \"\"," +
                "                \"phone\": \"123123\"," +
                "                \"email\": \"email@email.com\"," +
                "                \"info\": \"info\"," +
                "                \"details\": \"details\"," +
                "                \"category\": \"HIGHT_RISK\"" +
                "            }")
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON).
                log().everything().
                expect()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .when()
                .post("/companies").then().log().everything();
    }

    @Test
    public void testWrongInfoCreate() throws Exception {
        log.info("testWrongInfoCreate");

        given().request().body("{" +

                "                \"name\": \"name\"," +
                "                \"phone\": \"123123\"," +
                "                \"email\": \"email@email.com\"," +
                "                \"details\": \"details\"," +
                "                \"category\": \"HIGHT_RISK\"" +
                "            }")
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON).
                log().all().
                expect()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .when()
                .post("/companies").then().log().everything();
    }

    @Test
    public void testWrongCategoryCreate() throws Exception {
        log.info("testWrongCategoryCreate");

        given().request().body("{" +

                "                \"name\": \"name\"," +
                "                \"phone\": \"123123\"," +
                "                \"email\": \"email@email.com\"," +
                "                \"details\": \"details\"," +
                "                \"info\": \"info\"," +
                "                \"category\": \"WRONG_SHIT\"" +
                "            }")
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON).
                log().all().
                expect()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .when()
                .post("/companies").then().log().everything();
    }

    @Test
    public void testEmptyCategoryCreate() throws Exception {
        log.info("testEmptyCategoryCreate");

        given().request().body("{" +

                "                \"name\": \"name\"," +
                "                \"phone\": \"123123\"," +
                "                \"email\": \"email@email.com\"," +
                "                \"details\": \"details\"," +
                "                \"info\": \"info\"" +
                "            }")
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON).
                log().all().
                expect()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .when()
                .post("/companies").then().log().everything();
    }
}
