package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigReader;


//Base url es el que invoca la variable del properties de config reader --> config.properties
public class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");
    }
}