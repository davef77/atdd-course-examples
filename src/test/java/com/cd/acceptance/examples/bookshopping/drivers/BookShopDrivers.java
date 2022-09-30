package com.cd.acceptance.examples.bookshopping.drivers;


import com.cd.acceptance.examples.bookshopping.Card;
import com.cd.acceptance.dsl.utils.ChannelFinder;
import com.cd.acceptance.examples.bookshopping.BookShopDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookShopDrivers implements BookShopDriver
{
    private final Map<String, BookShopDriver> drivers = new HashMap<String, BookShopDriver>();

    public BookShopDrivers()
    {
        drivers.put("Amazon", new AmazonBookShopDriver());
        drivers.put("BookDepository", new BookDepositoryBookShopDriver());
        drivers.put("MyLocalBookStore", new MyLocalBookStoreBookShopDriver());
        drivers.put("default", new MyLocalBookStoreBookShopDriver());
    }

    private BookShopDriver driver()
    {
        List<String> channels = ChannelFinder.listChannels();

        return drivers.get(channels.get(0));
    }

    @Override
    public void searchForBook(String title)
    {
        driver().searchForBook(title);
    }

    @Override
    public void selectBook(String author)
    {
        driver().selectBook(author);
    }

    @Override
    public void addSelectedItemToShoppingBasket()
    {
        driver().addSelectedItemToShoppingBasket();
    }

    @Override
    public void assertListedInShoppingBasket(String item)
    {
        driver().assertListedInShoppingBasket(item);
    }

    @Override
    public void checkOut(String item, String price, Card card)
    {
        driver().checkOut(item, price, card);
    }

    @Override
    public void assertItemPurchased(String item)
    {
        driver().assertItemPurchased(item);
    }

    @Override
    public void goToStore()
    {

    }
}
