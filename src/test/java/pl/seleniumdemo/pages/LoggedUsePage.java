package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

public class LoggedUsePage   {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;
    private WebDriver driver;
    public LoggedUsePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }


    public String getHeadingText() {
        SeleniumHelper.waitForElementToBeVisible(driver, heading);
        return heading.getText();
    }

}
