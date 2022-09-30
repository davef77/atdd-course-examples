package com.cd.acceptance.examples.bookshopping.drivers;


import com.cd.acceptance.examples.bookshopping.Card;
import com.cd.acceptance.examples.bookshopping.BookShopDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AmazonBookShopDriver implements BookShopDriver
{

    private final static String BOOKSHOP_URL = "http://www.amazon.co.uk";

    private WebDriver driver;

    public AmazonBookShopDriver()
    {
    }

    @Override
    public void searchForBook(String title)
    {
        WebElement searchBox = driver().findElement(By.id("twotabsearchtextbox"));

        searchBox.sendKeys(title + "\n");
    }

    @Override
    public void selectBook(String author)
    {
        WebElement book =  driver().findElement(By.xpath(String.format(
                "//div[@class=\"a-row a-spacing-none\"]/span/a[text()='%s']/../../../div/a[contains(@class, 's-access-detail-page')]", author)));

        book.click();
    }

    @Override
    public void addSelectedItemToShoppingBasket()
    {
        WebElement buyButton = driver().findElement(By.id("add-to-cart-button"));

        buyButton.click();
    }

    @Override
    public void assertListedInShoppingBasket(String item)
    {
        gotoPage("https://www.amazon.co.uk/gp/cart/view.html/ref=nav_cart",
                "Amazon.co.uk Shopping Basket");

        List<WebElement> found = driver().findElements(
                By.xpath("//span[@class=\"a-list-item\"]/*[contains(., \"Continuous Delivery\")]"));

        assertEquals(String.format("Item '%s' not found in shopping basket", item),
                1, found.size());
    }

    @Override
    public void checkOut(String item, String price, Card card)
    {

    }

    @Override
    public void assertItemPurchased(String item)
    {

    }

    @Override
    public void goToStore()
    {
        gotoPage(BOOKSHOP_URL, "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more");
    }

    private void gotoPage(String page, String expectedTitle)
    {
        driver().get(page);

        assertEquals(expectedTitle, driver().getTitle());
    }

    private WebDriver driver()
    {
        if (driver == null)
        {
            try
            {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), DesiredCapabilities.chrome());
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
