package com.cd.acceptance.dsl;

import com.cd.acceptance.dsl.drivers.BookShopDrivers;
import org.junit.Before;

/**
 * Copyright (c) Continuous Delivery Ltd. 2016
 */
public class Dsl
{
    public BookShoppingDsl shopping;

    @Before
    public void setUp()
    {
        shopping = new BookShoppingDsl(new BookShopDrivers());
    }

}
