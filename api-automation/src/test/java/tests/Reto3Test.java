package tests;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//ejecuta el TestSuite
//mvn clean test

//Ejecuta testsuite por tag
//mvn clean test -Denv="USER"

//Ejecuta testsuite y realiza index offline de reporte
//mvn clean test; allure generate target/allure-results --clean -o allure-report

//Realiza servidor de allure para visualizar reporte
//allure serve target/allure-results

@Epic("Reto práctico 3 - Automatización de APIs con RestAssured ")
@Feature("Varios")
@Tag("Api")
@Tag("Reto3")
@Tag("Christian-Sanabria")
public class Reto3Test extends BaseTest {

    /**
     * Nombre del metodo: testGetListaUsuarios
     * Funcionamiento: Prueba el método GET de users sin parámetros, según el reto las validaciones deben incluir:
     *  - Status code = 200
     *  - Lista no vacía
     *  - Primer usuario:
     *  -                   id = 1
     *  -                   email válido
     * Parametros: Ninguno
     * Return: Listado de todos los usuarios en formato JSON
     */
    @Test
    @Order(1)
    @Tag("Reto3")
    @Tag("Caso1-ListaUsuarios")
    @Story("Get Usuarios")
    @Description("Obtener la lista de usuarios")
    public void testGetListaUsuarios() {
        System.out.println("=== INICIO VALIDACIÓN CASO 1 /users (LISTA DE USUARIOS) ===");
        Response response =given()
                .when()
                //.pathParam("id",1)
                .get("/users")
                .then()
                //valida que el statusCode sea 200
                .statusCode(200)
                //valida que la lista no esté vacía
                .body("size()", greaterThan(0))
                //Validaciones del primer usuario id=1 y email válido
                .body("[0].id", equalTo(1))
                .body("[0].email", matchesPattern("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
                .log().ifValidationFails()
                .extract().response();

        System.out.println("=== FIN VALIDACIÓN CASO 1 /users (LISTA DE USUARIOS) ===");
        Allure.step("Validación de GET users completada");
    }

    /**
     * Nombre del metodo: testGetUsuarioPorId
     * Funcionamiento: Prueba el método GET de users por Id, endpoint: /users/1
     * Parametros: {id} = 1
     * Return: (validación del objeto obtenido)
     */
    @Test
    @Order(2)
    @Tag("Reto3")
    @Tag("Caso2-UsuarioPorId")
    @Story("Get Usuario por Id")
    @Description("Obtener un usuario por su id")
    public void testGetUsuarioPorId() {
        System.out.println("=== INICIO VALIDACIÓN CASO 2 /users/{id} (OBTENER USUARIO POR ID) ===");
        Response response =given()
                .when()
                .pathParam("id",1)
                .get("/users/{id}")
                .then()
                //valida que el statusCode sea 200
                .statusCode(200)
                //valida que la lista no esté vacía
                .body("size()", greaterThan(0))
                //Validaciones del primer usuario id=1 y email válido
                .body("id", equalTo(1))
                .body("username", allOf(not(emptyString()), notNullValue()))
                .log().ifValidationFails()
                .extract().response();

        System.out.println("=== FIN VALIDACIÓN CASO 2 /users/{id} (OBTENER USUARIO POR ID) ===");
        Allure.step("Validación de GET users/1 completada");
    }

    /**
     * Nombre del metodo: testValidacionCamposUsuario
     * Funcionamiento: Prueba el que todos los usuarios tengan los campos id, name y email
     * Parametros: ninguno
     * Return: (validación del cada objeto obtenido)
     */
    @Test
    @Order(3)
    @Tag("Reto3")
    @Tag("Caso3-ValidacionCampos")
    @Story("Validación de campos")
    @Description("Hace múltiples validaciones de campos")
    public void testValidacionCamposUsuario() {
        System.out.println("=== INICIO VALIDACIÓN CASO 3 /users (VALIDACION DE CAMPOS) ===");
        Response response =given()
                .when()
                .get("/users")
                .then()
                //valida que el statusCode sea 200
                .statusCode(200)
                //valida que la lista no esté vacía
                .body("size()", greaterThan(0))
                //Validación de que todos los usuarios tengan el campo email
                .body("email", everyItem(not(emptyString())))
                .body("name", everyItem(not(emptyString())))
                .body("id", everyItem(not(emptyString())))
                .log().ifValidationFails()
                .extract().response();

        System.out.println("=== FIN VALIDACIÓN CASO 3 /users (VALIDACION DE CAMPOS EMAIL, NAME Y ID) ===");
        Allure.step("Validación de que todos los users tengan email, name y id");
    }

    /**
     * Nombre del metodo: testValidacionUsuarioInexistente
     * Funcionamiento: Prueba obtener un usuario no existente
     * Parametros: ninguno
     * Return: (validación del statusCode=404 del response obtenido)
     */
    @Test
    @Order(4)
    @Tag("Reto3")
    @Tag("Caso4-ValidacionUsuarioInexistente")
    @Story("UsuarioNoExiste")
    @Description("Hace get de un usuario inexistente y valida el response generado")
    public void testValidacionUsuarioInexistente() {
        System.out.println("=== INICIO VALIDACIÓN CASO 4 /users CON USUARIO INEXISTENTE ===");
        Response response =given()
                .pathParam("id",9999)
                .get("/users/{id}")
                .then()
                //valida que el statusCode sea 404 (Not found)
                .statusCode(404)
                .log().ifValidationFails()
                //.log().all()
                .extract().response();

        System.out.println("=== FIN VALIDACIÓN CASO 4 /users CON USUARIO INEXISTENTE ===");
        Allure.step("Validación del response correcto cuando usuario no existe");
    }


    /**
     * Nombre del metodo: testValidacionGeneral
     * Funcionamiento: Hace validaciones generales en todos los usuarios
     * Parametros: ninguno
     * Return: (validación de varios campos de cada objeto obtenido)
     */
    @Test
    @Order(5)
    @Tag("Reto3")
    @Tag("Caso5-ValidacionGeneral")
    @Story("ValidacionGeneral")
    @Description("Hace get usuarios y valida varias condiciones")
    public void testValidacionGeneral() {
        System.out.println("=== INICIO VALIDACIÓN CASO 5 /users (VALICACION GENERAL) ===");
        Response response =given()
                //.pathParam("id",9999)
                .get("/users")
                .then()
                //valida que el statusCode sea 200
                .statusCode(200)
                //valida que la lista no esté vacía
                .body("size()", greaterThan(0))
                //Validación de que todos los usuarios tengan el campo email
                .body("email", everyItem(not(emptyString())))
                // valida que todos los ids sean números enteros
                .body("id", everyItem(instanceOf(Integer.class)))
                // valida que todos los ids sean mayores que 0
                .body("id", everyItem(greaterThan(0)))
                .log().ifValidationFails()
                .extract().response();

        System.out.println("=== FIN VALIDACIÓN CASO 5 /users (VALIDACION GENERAL) ===");
        Allure.step("Validación general de usuarios");
    }

}