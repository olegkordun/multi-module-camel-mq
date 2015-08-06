package me.kordun.test.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.path.json.JsonPath;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

import static com.jayway.restassured.RestAssured.*;

public class LoginIT extends BaseIT {
    private static final Logger log = LoggerFactory.getLogger(LoginIT.class);

    @Test
    public void testSuccessLogin() throws Exception {
        log.info("testSuccessLogin");
        given().
                param("username", "user").
                param("password", "123").
                log().everything().
                expect()
                .statusCode(200)
                .cookie("JSESSIONID")
                .log().ifError()
                .when()
                .post("/login").then().log().everything();
    }

    @Test
    public void testWrongLogin() throws Exception {
        log.info("testWrongLogin");
        given().
                param("username", "user").
                param("password", "WRONG").
                expect()
                .statusCode(401)
                .when()
                .post("/login").then().log().everything();
    }

    @Test
    public void testGetRolesUser() throws Exception {
        log.info("testGetRolesUser");
        String response = given()
                .auth().form("user", "123", new FormAuthConfig("/login", "username", "password")).

                        log().everything().
                        expect()
                .statusCode(200)


                .log().ifError()
                .when()
                .get("/current-user-roles").asString();
        log.info("testGetRolesUser {}", response);

        JsonPath json = new JsonPath(response);
        List<String> roles = json.getList("");


        assertThat("Has role USER", roles.contains("ROLE_COMPANYUSER"));
    }

    @Test
    public void testGetRolesUnauthorized() throws Exception {
        log.info("testGetRolesUnauthorized");

        expect()
                .statusCode(401)
                .log().ifError()
                .when()
                .get("/current-user-roles")
                .then().log().everything();

    }
}
