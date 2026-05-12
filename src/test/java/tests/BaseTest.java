package tests; // Asegúrate de que coincida con tu carpeta

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Captura el navegador desde la terminal. Si no envían nada, usa chrome por defecto.
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        if (browser.equals("safari")) {
            driver = new SafariDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            // Si el test falló, saca captura automáticamente en la carpeta FAILED
            if (ITestResult.FAILURE == result.getStatus()) {
                takeScreenshot(result.getName(), "FAILED");
            }
            // Si el test pasó, sacamos captura en la carpeta PASSED
            else if (ITestResult.SUCCESS == result.getStatus()) {
                takeScreenshot(result.getName(), "PASSED");
            }
            driver.quit();
        }
    }

    public void takeScreenshot(String name, String failed) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());

        // Definimos la ruta: screenshots/PASSED o screenshots/FAILED
        String path = "./screenshots/" + failed + "/";

        try {
            // Esto crea la carpeta si no existe (importante para que no de error)
            Files.createDirectories(Paths.get(path));

            FileUtils.copyFile(scrFile, new File(path + name + "_" + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}