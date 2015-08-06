package me.kordun.test.integration;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class UserIT extends BaseIT {
    private static final Logger log = LoggerFactory.getLogger(UserIT.class);
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
        log.info("testSuccessCreateUser");

        String json = "{" +
                "  \"userName\": \"consequatur\"," +
                "  \"lastName\": \"expedita\"," +
                "  \"email\": \"expe@dita.cc\"," +
                "  \"passwordDigest\": \"91a73fd806ab2c005c13b4dc19130a884e909dea3f72d46e30266fe1a1f588d8\"," +

                "  \"firstName\": \"dolore\"," +
                "  \"roles\": [" +
                "    \"ROLE_COMPANYUSER\"," +
                "    \"ROLE_USER\"" +
                "  ]," +
                "  \"companyId\": \"\" }";

        given().request().body(json)
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON)
                .log().everything()
                .expect()
                .statusCode(HttpStatus.SC_CREATED)

                .when()
                .post("/users").then().log().everything();

        // createDataset();

        //Check database
        String[] ignoreCols = {"id", "user_id", "company_id",};
        Map<String, String> tables = new HashMap<>();
        tables.put("users", "select * from users where username = 'consequatur'");
        tables.put("user_role", "select * from user_role " +
                "where user_id in (select id from users where username = 'consequatur') order by role_name");
        assertDatabase("test-data/xml/expected/createUserExpected.xml", tables,ignoreCols);
    }



    @Test
    public void testGetUserFromDatabase() throws Exception {
        log.info("testGetUserFromDatabase");

        Response response =
                given().request()
                        .cookie("JSESSIONID", jSession)
                        .contentType(ContentType.JSON)
                        .log().everything()
                        .expect()
                        .statusCode(HttpStatus.SC_OK)
                        .when()
                        .get("/users/1")
                        .andReturn();

        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"email\": \"asd@asd.ru\",\n" +
                "    \"userName\": \"user\",\n" +
                "    \"firstName\": \"firstname\",\n" +
                "    \"lastName\": \"lastname\",\n" +
                "    \"comment\": \"comment\",\n" +
                "    \"roles\": [\n" +
                "        \"ROLE_COMPANYUSER\"\n" +

                "    ],\n" +
                "    \"companyId\": null\n" +
                "}";

        JSONAssert.assertEquals(expected, response.asString(), false);
    }

    @Test
    public void testUpdateUser() throws Exception {
        log.info("testUpdateUser");

        String json = "{" +
                "  \"userName\": \"new-userName\"," +
                "  \"lastName\": \"new lastName\"," +
                "  \"email\": \"new@new.cc\"," +

                "  \"firstName\": \"new firstName\"," +
                "  \"roles\": [" +
                "    \"ROLE_COMPANYUSER\"," +
                "    \"ROLE_USER\"" +
                "  ]," +
                "  \"companyId\": \"\" }";
        given().request()
                .body(json)
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON)
                .log().everything()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .put("/users/1").then().log().everything();

       // createDataset();
        //Check database
        String[] ignoreCols = {};
        Map<String, String> tables = new HashMap<>();
        tables.put("users", "select * from users where id = '1'");
        tables.put("user_role", "select * from user_role where user_id in " +
                "(select id from users where id = '1') order by role_name");
        assertDatabase("test-data/xml/expected/updateUserExpected.xml", tables, ignoreCols);

    }

    @Test
    public void testUpdateUserPassword() throws Exception {
        log.info("testUpdateUserPassword");

        String json = "{" +
                "  \"userName\": \"new-userName\"," +
                "  \"lastName\": \"new lastName\"," +
                "  \"email\": \"new@new.cc\"," +
                "  \"passwordDigest\": \"someDigestValue\"," +

                "  \"firstName\": \"new firstName\"," +
                "  \"roles\": [" +
                "    \"ROLE_COMPANYUSER\"," +
                "    \"ROLE_USER\"" +
                "  ]," +
                "  \"companyId\": \"\" }";
        given().request()
                .body(json)
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON)
                .log().everything()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .put("/users/1").then().log().everything();

        // createDataset();
        //Check database
        String[] ignoreCols = {};
        Map<String, String> tables = new HashMap<>();
        tables.put("users", "select * from users where id = '1'");
        tables.put("user_role", "select * from user_role where user_id in " +
                "(select id from users where id = '1') order by role_name");
        assertDatabase("test-data/xml/expected/updateUserWithPasswordExpected.xml", tables, ignoreCols);

    }

    @Test
    public void testGetUsersListFromDatabase() throws Exception {
        log.info("testGetUsersListFromDatabase");
        Response response =
                given().request()
                        .cookie("JSESSIONID", jSession)
                        .contentType(ContentType.JSON)
                        .log().everything()
                        .expect()
                        .statusCode(HttpStatus.SC_OK)
                        .log().everything()
                        .when()
                        .get("/users/")
                        .andReturn();

        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"email\": \"asd@asd.ru\",\n" +
                "        \"userName\": \"user\",\n" +
                "        \"firstName\": \"firstname\",\n" +
                "        \"lastName\": \"lastname\",\n" +
                "        \"comment\": \"comment\",\n" +
                "        \"roles\": [\n" +
                "            \"ROLE_COMPANYUSER\",\n" +

                "        ],\n" +
                "        \"companyId\": null\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"email\": \"asd@asd.ru\",\n" +
                "        \"userName\": \"admin\",\n" +
                "        \"firstName\": \"firstname\",\n" +
                "        \"lastName\": \"lastname\",\n" +
                "        \"comment\": \"comment\",\n" +
                "        \"roles\": [\n" +
                "            \"ROLE_ADMIN\"\n" +
                "        ],\n" +
                "        \"companyId\": null\n" +
                "    }\n" +
                "]";

        JSONAssert.assertEquals(expected, response.asString(), false);
    }

    @Test
    public void testGetRolesList() throws Exception {
        log.info("testGetRolesList");
        Response response =
                given().request()
                        .cookie("JSESSIONID", jSession)
                        .contentType(ContentType.JSON)
                        .log().everything()
                        .expect()
                        .statusCode(HttpStatus.SC_OK)
                        .log().everything()
                        .when()
                        .get("/all-roles-list/")
                        .andReturn();

        String expected = "[\n" +
                "    \"ROLE_USER\",\n" +
                "    \"ROLE_ADMIN\",\n" +
                "    \"ROLE_COMPANYUSER\"\n" +
                "]";

        JSONAssert.assertEquals(expected, response.asString(), false);
    }

    @Test
    public void testUpdatePassword() throws Exception {
        log.info("testUpdatePassword");

        given().request().param("passwordDigest", "91a73fd806ab2c005c13b4dc19130a884e909dea3f72d46e30266fe1a1f588d8" )
                .cookie("JSESSIONID", jSession)
                .contentType(ContentType.JSON)
                .log().everything()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .put("/users/1/password").then().log().everything();

         //createDataset();
        //Check database
        String[] ignoreCols = {"id", "user_id", "company_id", "password"};
        Map<String, String> tables = new HashMap<>();
        tables.put("users", "select * from users where id = '1'");

        assertDatabase("test-data/xml/expected/updateUserPasswordExpected.xml", tables, ignoreCols);
    }

}
