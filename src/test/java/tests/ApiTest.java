package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    public void validarDepartamentosMercadoLibre() {
        given()
                // Este header es clave para evitar el error 403
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .header("Accept", "text/html,application/json")
                .baseUri("https://www.mercadolibre.com.ar")
                .when()
                .get("/menu/departments")
                .then()
                .log().ifValidationFails() // Esto te ayudará a ver el error en consola si falla
                .statusCode(200)
                .body(notNullValue());
    }
}