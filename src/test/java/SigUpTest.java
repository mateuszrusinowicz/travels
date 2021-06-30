import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SigUpTest extends BaseTest {


    @Test
    public void SignUpTest ()  {

        //Lekcja 230 - klikanie w suwak i w sing up
        driver.findElements(By.xpath("//*[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        //lekcja 231 - uzupełnianie formularza rejestracji 1/2
        String lastName = "Rusinowicz";
        int randomNumber = (int) (Math.random()*1000);
        String email = "mateusz"+randomNumber+"@gmail.com";
        driver.findElement(By.name("firstname")).sendKeys("Mateusz");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("+48 733 766 607");
        driver.findElement(By.name("email")).sendKeys(email);
        //lekcja 232 - uzupełnianie formularza rejestracji 2/2
        driver.findElement(By.name("password")).sendKeys("lolek888");
        driver.findElement(By.name("confirmpassword")).sendKeys("lolek888");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Mateusz Rusinowicz");



    }
    @Test
    public void EmailErrorTest()  {

        driver.findElements(By.xpath("//*[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        driver.findElement(By.name("firstname")).sendKeys("Mateusz");
        driver.findElement(By.name("lastname")).sendKeys("Rusinowicz");
        driver.findElement(By.name("phone")).sendKeys("+48 733 766 607");
        driver.findElement(By.name("email")).sendKeys("mati.pl");
        driver.findElement(By.name("password")).sendKeys("lolek888");
        driver.findElement(By.name("confirmpassword")).sendKeys("lolek888");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        WebElement heading = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[2]/div/p"));
        Assert.assertEquals(heading.getText(), "The Email field must contain a valid email address.");



    }
    @Test
    public void NoElementsSingInTest()  {

        //Lekcja 230 - klikanie w suwak i w sing up
        driver.findElements(By.xpath("//*[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();


       //CZESC PIERWSZA PRACY DOMOWEJ - WYSZUKIWANIE BRAKU KOMUNIKATOW
 List<String> findElement = driver.findElements(By.xpath("//div[contains (@class,'alert alert-danger')]//p")).stream()
                .map(el -> el.getAttribute("textContent"))
                //inny sposob pobierania nazw hoteli(tekstu) - getText przy szybkim pobieraniu nie pobierze całości
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("The Email field is required.", findElement.get(0));
        softAssert.assertEquals("The Password field is required.", findElement.get(1));
        softAssert.assertEquals("The Password field is required.", findElement.get(2));
        softAssert.assertEquals("The First name field is required.", findElement.get(3));
        softAssert.assertEquals("The Last Name field is required.", findElement.get(4));

    }
}
