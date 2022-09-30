package com.cd.acceptance.examples.bookshopping;

import org.junit.Before;

public class Dsl
{
    public BookShoppingDsl shopping;

    @Before
    public void setUp()
    {
        shopping = new BookShoppingDsl(new BookShopDrivers());
    }
}
