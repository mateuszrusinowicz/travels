package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPages;
import pl.seleniumdemo.utils.ExcelReader;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {


    @Test
    public void searchHotelTest() throws IOException {
        ExtentTest test = extentReports.createTest("Sreach hotel test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("27/04/2021", "29/05/2021");
        test.log(Status.PASS, "Setting date done", SeleniumHelper.getScreenshot(driver));

        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "Setting travellers done", SeleniumHelper.getScreenshot(driver));

        hotelSearchPage.performSearch();
        test.log(Status.PASS, "performing search done", SeleniumHelper.getScreenshot(driver));
        test.log(Status.PASS, "screenshot", SeleniumHelper.getScreenshot(driver));

        ResultPages resultPages = new ResultPages(driver);
        List<String> hotelNames = resultPages.getHotelNames();

        //DODAJEMY ASERCJE 229

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
        test.log(Status.PASS, "Assertions Passed"   , SeleniumHelper.getScreenshot(driver));

    }


    @Test
    public void NoSearchHotelTest() throws IOException {
        ExtentTest test = extentReports.createTest("Sreach hotel test without city");

        //WYSZUKUJEMY OKIENKA DO MIEJSCOWOŚCI 224
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));

        //WYBIERAMY DATY
        hotelSearchPage.setDates("17/04/2021", "30/04/2021");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));

        hotelSearchPage.setTravellers(0, 1);
        test.log(Status.PASS, "add travellers done", SeleniumHelper.getScreenshot(driver));

        hotelSearchPage.performSearch();
        ResultPages resultPage = new ResultPages(driver);
        test.log(Status.PASS, "Perform setting done", SeleniumHelper.getScreenshot(driver));

        Assert.assertTrue(resultPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultPage.getHeadingText(), "No Results Found");
        test.log(Status.PASS, "Assercions passed", SeleniumHelper.getScreenshot(driver));

    }
    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) throws IOException {
        ExtentTest test = extentReports.createTest("Sreach hotel test without data provider" + city);

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));

        hotelSearchPage.setDates("27/04/2021", "29/05/2021");
        test.log(Status.PASS, "Setting date done", SeleniumHelper.getScreenshot(driver));

        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "Setting travellers done", SeleniumHelper.getScreenshot(driver));

        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));

        ResultPages resultPages = new ResultPages(driver);
        List<String> hotelNames = resultPages.getHotelNames();

        //DODAJEMY ASERCJE 229

        Assert.assertEquals(hotelNames.get(0), hotel);
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));


    }
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }
}
