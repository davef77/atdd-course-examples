package com.cd.acceptance.examples.bookshopping;

import com.cd.acceptance.dsl.utils.Params;

public class BookShoppingDsl
{
    private final Params.DslContext context = new Params.DslContext();
    private final BookShopDrivers driver;

    public BookShoppingDsl(BookShopDrivers drivers)
    {
        this.driver = drivers;
    }

    public void searchForBook(String... args)
    {
        Params params = new Params(context, args);
        String title = params.Optional("title", "Continuous Delivery");

        driver.searchForBook(title);
    }

    public void selectBook(String... args)
    {
        Params params = new Params(context, args);
        String author = params.Optional("author", "David Farley");

        driver.selectBook(author);
    }

    public void addSelectedItemToShoppingBasket()
    {
        driver.addSelectedItemToShoppingBasket();
    }

    public void assertItemListedInShoppingBasket(String... args)
    {
        Params params = new Params(context, args);
        String item = params.Optional("item", "Continuous Delivery");

        driver.assertListedInShoppingBasket(item);
    }

    public void checkOut(String... args)
    {
        Params params = new Params(context, args);
        String item = params.Optional("item", "Continuous Delivery");
        String price = params.Optional("price", "£10.00");
        Card card = parseCard(params.Optional("card", "1234 5678 9101 0001 12/23 007"));

        driver.checkOut(item, price, card);
    }

    public void assertItemPurchased(String... args)
    {
        Params params = new Params(context, args);
        String item = params.Optional("item", "Continuous Delivery");

        driver.assertItemPurchased(item);
    }

    private Card parseCard(String card)
    {
        String cardDetails = card;
        return new Card(cardDetails.substring(19),
                cardDetails.substring(20, 25),
                cardDetails.substring(26, 29));
    }

    public void goToStore()
    {
        driver.goToStore();
    }
}
