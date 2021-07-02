package pl.seleniumdemo.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPages;
import java.util.List;

public class HotelSearchTest extends BaseTest {


    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("27/04/2021", "29/05/2021");
        hotelSearchPage.setTravellers(1,2);
        hotelSearchPage.performSearch();

        ResultPages resultPages = new ResultPages(driver);
        List<String> hotelNames = resultPages.getHotelNames();

        //DODAJEMY ASERCJE 229

        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

    }

    @Test
    public void NoSearchHotelTest()  {

        //WYSZUKUJEMY OKIENKA DO MIEJSCOWOŚCI 224
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        //WYBIERAMY DATY
        hotelSearchPage.setDates("17/04/2021", "30/04/2021");

        hotelSearchPage.setTravellers(0, 1);

        hotelSearchPage.performSearch();
        ResultPages resultPage = new ResultPages(driver);

Assert.assertTrue(resultPage.resultHeading.isDisplayed());
Assert.assertEquals(resultPage.getHeadingText(), "No Results Found");
    }
}
