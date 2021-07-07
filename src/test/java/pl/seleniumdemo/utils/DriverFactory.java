package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriverFactory {
    public static WebDriver getDriver() throws IOException {
        String name = PropertiesLoader.loadProperties("browser.name");
        if(name.equals("firefox")){
WebDriverManager.firefoxdriver().setup();
return new FirefoxDriver();
        }else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }

    }
}
