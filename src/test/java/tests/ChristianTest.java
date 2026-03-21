package tests;

//import Model.Posts;
//import io.restassured.http.ContentType;
//import org.json.JSONObject;
//import static io.restassured.RestAssured.*;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//import io.qameta.allure.*;
import static org.hamcrest.core.IsEqual.equalTo;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;

//ejecuta el TestSuite
//mvn clean test

//Ejecuta testsuite por tag
//mvn clean test -Denv="USER"

//Ejecuta testsuite y realiza index offline de reporte
//mvn clean test; allure generate target/allure-results --clean -o allure-report

//Realiza servidor de allure para visualizar reporte
//allure serve target/allure-results

@Epic("API Tests")              // Agrupa todas las pruebas en un "Epic"
@Feature("Usuarios")            // Define la funcionalidad principal
@Tag("API")                     //mvn test -Dgroups=API
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChristianTest extends BaseTest {


    @Test
    @Order(1)
    @Tag("USER")
    @Tag("Street")
    @Story("Validar street de usuario 1")
    @Description("Obtener usuario 1 y validar que el street del address sea Kulas Light")
    public void testCompareUserStreetWithKulasById() {
        given()
                .when()
                .pathParam("id",1)
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .body("address.street", equalTo("Kulas Light"))
                .log();
        Allure.step("Validación de street de users/id=1 completada");
    }


}