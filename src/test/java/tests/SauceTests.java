package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceTests extends BaseTest{

    @Test
    public void loginExitoso() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void agregarProductoAlCarrito() {
        // 1. Login previo (puedes usar el método que ya tienes)
        loginExitoso();

        // 2. Definir el nombre del producto que queremos validar
        String nombreProductoEsperado = "Sauce Labs Backpack";

        // 3. Agregar el producto a la cesta
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // 4. Click en el icono del carrito para entrar a la vista de compra
        driver.findElement(By.className("shopping_cart_link")).click();

        // 5. VALIDACIÓN REAL: Verificar que el nombre del producto esté en la lista
        String nombreEnCarrito = driver.findElement(By.className("inventory_item_name")).getText();

        Assert.assertEquals(nombreEnCarrito, nombreProductoEsperado,
                "El producto en el carrito no coincide con el que intentamos agregar.");
    }

    @Test
    public void testFallaIntencional() {
        driver.get("https://www.saucedemo.com/");
        // Forzamos un error de aserción para disparar la captura de pantalla
        Assert.assertEquals(driver.getTitle(), "Título Incorrecto para Fallar");
    }
}
