package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SandraTest extends BaseTest {

    @Test
    @Order(2)
    @Tag("USER")
    @Story("Obtener usuario por ID")
    @Description("Validar que el endpoint /posts/{id} devuelve el usuario correcto")
    public void testGetUserById() {
        given()
                .when()
                .pathParam("id",1)
                .get("/posts/{id}")
                .then()
                .statusCode(200)
                .body("userId",equalTo(1))
                .log();
        Allure.step("Validación de userId=1 completada");
    }
}
