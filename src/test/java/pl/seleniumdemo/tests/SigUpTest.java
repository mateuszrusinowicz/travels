package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUsePage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SigUpTest extends BaseTest {


    @Test
    public void SignUpTest ()  {
//lekcja 231 - uzupełnianie formularza rejestracji 1/2
        String lastName = "Rusinowicz";
        int randomNumber = (int) (Math.random()*1000);
        String email = "mateusz"+randomNumber+"@gmail.com";
        //Lekcja 230 - klikanie w suwak i w sing up
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpFrom();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Mateusz");
        signUpPage.setLastName(lastName);
        signUpPage.setPhone("733766607");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Mateusz123");
        signUpPage.confirmPassword("Mateusz123");
        signUpPage.signUp();

        LoggedUsePage loggedUsePage = new LoggedUsePage(driver);
        loggedUsePage.getHeadingText();


        Assert.assertTrue(loggedUsePage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUsePage.getHeadingText(), "Hi, Mateusz Rusinowicz");


    }

    @Test
    public void SignUpTest2 ()  {
//lekcja 231 - uzupełnianie formularza rejestracji 1/2
        int randomNumber = (int) (Math.random()*1000);
        String email = "mateusz"+randomNumber+"@gmail.com";
        //Lekcja 230 - klikanie w suwak i w sing up

        User user = new User();
        user.setFirstName("Mateusz");
        user.setLastName("Rusinowicz");
        user.setPhone("123123123");
        user.setEmail(email);
        user.setPassword("123123123123");


        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpFrom();

        SignUpPage signUpPage = new SignUpPage(driver);
        //signUpPage.fillSignUpForm("Mateusz", lastName, "733766607", email, "Mateusz123");
        signUpPage.fillSignUpForm2(user);

        LoggedUsePage loggedUsePage = new LoggedUsePage(driver);
        loggedUsePage.getHeadingText();


        Assert.assertTrue(loggedUsePage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUsePage.getHeadingText(), "Hi, Mateusz Rusinowicz");


    }
    @Test
    public void signUpEmptyFromTest()  {

        //Lekcja 230 - klikanie w suwak i w sing up
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpFrom();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();


        //CZESC PIERWSZA PRACY DOMOWEJ - WYSZUKIWANIE BRAKU KOMUNIKATOW
        List<String> findElement = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("The Email field is required.", findElement.get(0));
        softAssert.assertEquals("The Password field is required.", findElement.get(1));
        softAssert.assertEquals("The Password field is required.", findElement.get(2));
        softAssert.assertEquals("The First name field is required.", findElement.get(3));
        softAssert.assertEquals("The Last Name field is required.", findElement.get(4));

    }
    @Test
    public void signUpInvalidEmail()  {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpFrom();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Mateusz");
        signUpPage.setLastName("Rusinowicz");
        signUpPage.setPhone("733766607");
        signUpPage.setEmail("matek");
        signUpPage.setPassword("Mateusz123");
        signUpPage.confirmPassword("Mateusz123");
        signUpPage.signUp();


        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));



    }

}
