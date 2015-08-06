package me.kordun.test.integration;

import com.jayway.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
public class BaseIT extends DbUnitTest{

    @BeforeClass
    public static void setPath(){
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.port = 8989;
        RestAssured.basePath = "/office-front";
    }

    @Before
    public void setUpDatabase() {
        super.setUpDatabase();
    }

    @After
    public void cleanDatabase(){
        super.tearDown();
    }
}
