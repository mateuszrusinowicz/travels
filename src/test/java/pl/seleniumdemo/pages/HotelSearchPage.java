package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;


public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement searchHotelInput;

    @FindBy(name = "checkin")
    private WebElement checkInInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='li_myaccount']")
    private List<WebElement> myAccountLink;
    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;


    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void setCity(String cityName) {
        logger.info("setting city" + cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        SeleniumHelper.waitForElementToExist(driver, By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        logger.info("setting city done");


    }

    public void setDates(String checkin, String checkout) {
logger.info("Setting dates checkin: " + checkin + "checkout: " + checkout);
        checkInInput.sendKeys(checkin);
        checkoutInput.sendKeys(checkout);
        logger.info("checing dates done");
    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        logger.info("addinf adults: " + adultsToAdd + "and kids: " + childToAdd);
        travellersInput.click();
        addTraveler(adultPlusBtn, adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
        logger.info("adding travelers done");
    }

    private void addTraveler(WebElement travelerBtn, int numberOfTravelers) {
        for (int i = 0; i < numberOfTravelers; i++) {
            SeleniumHelper.waitForElementToBeVisible(driver, travelerBtn);
            travelerBtn.click();
        }

    }

    public void performSearch() {
        logger.info("performing search");
        searchButton.click();
        logger.info("performing search done");

    }

    public void openSignUpFrom() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }


}
