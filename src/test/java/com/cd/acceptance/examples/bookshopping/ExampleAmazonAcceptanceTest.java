package com.cd.acceptance.examples.bookshopping;

import com.cd.acceptance.dsl.utils.Channel;
import org.junit.Test;

import static com.cd.acceptance.dsl.utils.Channels.Amazon;

public class ExampleAmazonAcceptanceTest extends Dsl
{
    @Test
    @Channel(Amazon)
    public void shouldAddBookToShoppingBasket() throws Exception
    {
        shopping.searchForBook("title: Continuous Delivery");
        shopping.selectBook("author: David Farley");

        shopping.addSelectedItemToShoppingBasket();

        shopping.assertItemListedInShoppingBasket("item: Continuous Delivery");
    }

    @Test
    @Channel(Amazon)
    public void shouldBuyBookWithCreditCard()
    {
        shopping.goToStore();

        shopping.searchForBook("title: Continuous Delivery");
        shopping.selectBook("author: David Farley");
        shopping.addSelectedItemToShoppingBasket();

        shopping.checkOut("item: Continuous Delivery");

        shopping.assertItemPurchased("item: Continuous Delivery");
    }
}
