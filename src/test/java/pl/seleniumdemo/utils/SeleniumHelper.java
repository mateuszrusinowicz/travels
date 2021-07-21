package pl.seleniumdemo.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class SeleniumHelper {
    public static void waitForElementToExist(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver,10l);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }
    public static void waitForElementToBeVisible(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,10l);
        wait.until(ExpectedConditions.visibilityOf(element));}

    public static void waitForEmptyList(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver,10l);
        wait.until(browser -> browser.findElements(locator).size()>0);}

        public static MediaEntityModelProvider getScreenshot(WebDriver driver) throws  IOException{
        String path = takeScreenshot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
        }

        private static String takeScreenshot(WebDriver driver) throws IOException {
        int randomNumber = (int) (Math.random()*1000);
            TakesScreenshot screenshot = (TakesScreenshot)  driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String patch = "src/test/resources/screenshot/screenshot" + randomNumber + ".png";
            FileUtils.copyFile(file, new File(patch));
            return patch;
        }

}
