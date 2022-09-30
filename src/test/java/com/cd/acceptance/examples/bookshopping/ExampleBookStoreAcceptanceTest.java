package com.cd.acceptance.examples.bookshopping;

import com.cd.acceptance.dsl.utils.Channel;
import org.junit.Test;

import static com.cd.acceptance.dsl.utils.Channels.Amazon;
import static com.cd.acceptance.dsl.utils.Channels.BookDepository;

public class ExampleBookStoreAcceptanceTest extends Dsl
{
    @Test
    @Channel({BookDepository, Amazon})
    public void shouldAddBookToShoppingBasket() throws Exception
    {
        //Given
        shopping.searchForBook("title: Continuous Delivery");
        shopping.selectBook("author: David Farley");

        //When
        shopping.addSelectedItemToShoppingBasket();

        //Then
        shopping.assertItemListedInShoppingBasket("item: Continuous Delivery");
    }
}


