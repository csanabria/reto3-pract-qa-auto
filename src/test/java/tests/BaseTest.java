package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigReader;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");
    }

}