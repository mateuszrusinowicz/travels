package pl.seleniumdemo.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPages {
    @FindBy(xpath = "//h4[contains (@class,'list_title')]//b")
    private List<WebElement> hotelList;

    @FindBy(xpath = "//*[@id=\"body-section\"]/div[5]/div[1]/div[3]/div/div/h2")
    public WebElement resultHeading;
    //NALEŻY DODAĆ ABY METODA BEZ WPROWADZONYCH DANYCH ZADZIAŁAŁA
    public ResultPages(WebDriver driver){
            PageFactory.initElements(driver, this);
}
    public List<String> getHotelNames() {
        return hotelList.stream().map(el -> el.getAttribute("textContent"))
                //inny sposob pobierania nazw hoteli(tekstu) - getText przy szybkim pobieraniu nie pobierze całości
                .collect(Collectors.toList());
    }

    public String getHeadingText(){
        return resultHeading.getText();
    }
}
