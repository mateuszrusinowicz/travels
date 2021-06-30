import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PracaDomowa_234 {

    @Test
    public void searchHotel()  {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        //WYSZUKUJEMY OKIENKA DO MIEJSCOWOŚCI 224

        //WYBIERAMY DATY
        driver.findElement(By.name("checkin")).sendKeys("17/04/2021");
         driver.findElement(By.name("checkout")).sendKeys("30/04/2021");


        // WYBIERANIE ILOŚCI OSÓB - MÓJ SPOSÓB - BARTEK ROBI NA ID 227
        driver.findElement(By.xpath("//*[@id=\"travellersInput\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"adultMinusBtn\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"childPlusBtn\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"hotels\"]/form/div[5]/button")).click();

     WebElement noneHotels = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[5]/div[1]/div[3]/div/div/h2"));

     Assert.assertTrue(noneHotels.isDisplayed());





    }
}


