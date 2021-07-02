package pl.seleniumdemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {


    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("27/04/2021", "29/05/2021");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains (@class,'list_title')]//b")).stream()
                                                                                .map(el -> el.getAttribute("textContent"))
                //inny sposob pobierania nazw hoteli(tekstu) - getText przy szybkim pobieraniu nie pobierze całości
                                                                                .collect(Collectors.toList());
       /* MOŻEMY WYPISAĆ SOBIE NAZWY HOTELI DLA UŁATWIENIA
        System.out.println(hotelNames.size());
        hotelNames.forEach(el -> System.out.println(el));*/
        //DODAJEMY ASERCJE 229
        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }

    @Test
    public void NoSearchHotelTest()  {

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
