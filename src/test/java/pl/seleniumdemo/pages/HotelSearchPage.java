package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li/ul/li/div")
    private WebElement hotelMatch;

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

public HotelSearchPage(WebDriver driver){
    PageFactory.initElements(driver, this);
}

public void setCity(String cityName){
    searchHotelSpan.click();
    searchHotelInput.sendKeys(cityName);
    hotelMatch.click();
}

public void setDates(String checkin,String checkout){
    checkInInput.sendKeys(checkin);
    checkoutInput.sendKeys(checkout);
}

public void setTravellers(){
    travellersInput.click();
    adultPlusBtn.click();
    childPlusBtn.click();
}

public void performSearch(){
    searchButton.click();
}




}
