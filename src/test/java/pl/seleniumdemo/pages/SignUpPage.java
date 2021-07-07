package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {

    @FindBy(name = "firstname")
    private WebElement firstNameInptut;

    @FindBy(name = "lastname")
    private WebElement lastNameInptut;

    @FindBy(name = "phone")
    private WebElement phoneInptut;

    @FindBy(name = "email")
    private WebElement emailInptut;

    @FindBy(name = "password")
    private WebElement passwordInptut;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInptut;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[contains (@class,'alert alert-danger')]//p")
    private List<WebElement> errors;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public void setFirstName(String firstName) {
        firstNameInptut.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameInptut.sendKeys(lastName);
    }

    public void setPhone(String phone) {
        phoneInptut.sendKeys(phone);
    }

    public void setEmail(String email) {
        emailInptut.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInptut.sendKeys(password);
    }

    public void confirmPassword(String confirmPassword) {
        confirmPasswordInptut.sendKeys(confirmPassword);
    }

    public void signUp() {
        signUpButton.click();
    }

    public List<String> getErrors() {
        //CZESC PIERWSZA PRACY DOMOWEJ - WYSZUKIWANIE BRAKU KOMUNIKATOW
        return errors.stream()
                .map(WebElement::getText)
                //inny sposob pobierania nazw hoteli(tekstu) - getText przy szybkim pobieraniu nie pobierze całości
                .collect(Collectors.toList());
    }

}
