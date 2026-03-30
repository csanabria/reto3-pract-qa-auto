package utils;

import io.qameta.allure.Attachment;

public class AllureUtils {

    /**
     * Nombre del metodo: attachResponse
     * Funcionamiento: Adjunta el response body al reporte de Allure con nombre por defecto
     * Parametros: response (String) - Body de la respuesta
     * Return: No retorna valor
     */
    public static void attachResponse(String response) {
        attachResponse("Body extraído", response);
    }

    /**
     * Nombre del metodo: attachResponse
     * Funcionamiento: Adjunta el response body al reporte de Allure con nombre personalizado
     * Parametros:
     *      name (String) - Nombre del attachment
     *      response (String) - Body de la respuesta
     * Return: String
     */
    @Attachment(value = "{name}", type = "application/json")
    public static String attachResponse(String name, String response) {
        return response;
    }

    /**
     * Nombre del metodo: attachText
     * Funcionamiento: Adjunta texto plano al reporte de Allure
     * Parametros:
     *      name (String) - Nombre del attachment
     *      content (String) - Contenido
     * Return: String
     */
    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String content) {
        return content;
    }
}