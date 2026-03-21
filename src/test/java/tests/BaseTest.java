package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;

public class BaseTest {

    /**
     * Nombre del metodo: setup
     * Funcionamiento: Inicializa la configuración base de RestAssured, establece la URL base y agrega el filtro de Allure
     * Parametros: No aplica
     * Return: No retorna valor
     */
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");

        // Filtro automático para Allure (request + response completos)
        RestAssured.filters(
                new AllureRestAssured()
        );
    }
}