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

public class SigUpTest {
    @Test
    public void searchHotel()  {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
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
}
