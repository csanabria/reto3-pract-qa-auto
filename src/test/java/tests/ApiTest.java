package tests;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import io.qameta.allure.*;
import utils.AllureUtils;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsEqual.equalTo;

//ejecuta el TestSuite
//mvn clean test

//Ejecuta testsuite por tag
//mvn clean test -Denv="USER"

//Ejecuta testsuite y realiza index offline de reporte
//mvn clean test; allure generate target/allure-results --clean -o allure-report

//Realiza servidor de allure para visualizar reporte
//allure serve target/allure-results

@Epic("API Tests")
@Feature("Usuarios")
@Tag("API")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTest extends BaseTest {

    /**
     * Caso de prueba : Obtener lista de usuarios
     * Funcionamiento: Valida el endpoint /users y su schema
     * Precondiciones: Servicio disponible
     */
    @Test
    @Order(3)
    public void testGetUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/users.json"));

        Allure.step("Validación de schema completada");
    }

    /**
     * Caso de prueba : Obtener usuario por ID
     * Funcionamiento: Valida respuesta y adjunta evidencia completa
     * Precondiciones: Existe registro con id=1
     */
    @Test
    @Order(2)
    @Tag("USER")
    public void testGetUserById() {

        Response response =
                given()
                        .pathParam("id",1)
                        .when()
                        .get("/posts/{id}");

        // 🔥 Validaciones
        response.then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("userId", equalTo(1));

        // 🔥 Extraer datos
        String responseBody = response.getBody().asString();
        String headers = response.getHeaders().toString();
        int statusCode = response.getStatusCode();

        // 🔥 Attachments manuales (extra evidencia)
        Allure.step("Adjuntando evidencia adicional");

        AllureUtils.attachText("Endpoint", "/posts/{id}");
        AllureUtils.attachText("Método", "GET");
        AllureUtils.attachText("Status Code", String.valueOf(statusCode));
        AllureUtils.attachText("Headers", headers);
        AllureUtils.attachResponse("Response Body", responseBody);
    }

    /**
     * Caso de prueba : Validar usuario por objeto
     * Funcionamiento: Extrae response y lo adjunta a Allure
     * Precondiciones: Endpoint disponible
     */
    @Test
    @Order(1)
    @Tag("USER")
    public void testGetUserByIdValidateByObject() {

        String response =
                given()
                        .when()
                        .pathParam("id",1)
                        .get("/posts/{id}")
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .extract()
                        .asString();

        AllureUtils.attachResponse("Body extraído", response);
    }

    /**
     * Caso de prueba : Crear post
     * Funcionamiento: Valida creación de recurso
     * Precondiciones: Endpoint disponible
     */
    @Test
    public void testCreatePost() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "foo");
        requestBody.put("body", "bar");
        requestBody.put("userId", 1);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody.toString())
                        .when()
                        .post("/posts");

        response.then()
                .log().ifValidationFails()
                .statusCode(201)
                .body("title", equalTo("foo"));

        // 🔥 Attach request/response
        AllureUtils.attachResponse("Request Body", requestBody.toString());
        AllureUtils.attachResponse("Response Body", response.getBody().asString());
    }
}