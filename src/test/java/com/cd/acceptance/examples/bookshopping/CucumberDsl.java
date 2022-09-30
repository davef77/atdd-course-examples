package com.cd.acceptance.examples.bookshopping;

public class CucumberDsl
{
    public BookShoppingDsl shopping;

    public CucumberDsl()
    {
        shopping = new BookShoppingDsl(new BookShopDrivers());
    }
}
