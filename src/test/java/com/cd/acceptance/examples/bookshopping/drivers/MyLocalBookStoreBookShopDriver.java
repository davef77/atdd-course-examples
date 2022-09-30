package com.cd.acceptance.examples.bookshopping.drivers;

import com.cd.acceptance.examples.bookshopping.Card;
import com.cd.acceptance.examples.bookshopping.Book;
import com.cd.acceptance.examples.bookshopping.MyLocalBookStore;
import com.cd.acceptance.examples.bookshopping.BookShopDriver;
import org.junit.Assert;

import java.util.List;

public class MyLocalBookStoreBookShopDriver implements BookShopDriver
{
    private MyLocalBookStore store = new MyLocalBookStore();
    private List<Book> booksByTitle;
    private Book selectedBook;

    public MyLocalBookStoreBookShopDriver()
    {
        store.addBook(new Book("Continuous Delivery", "David Farley & Jez Humble"));
        store.addBook(new Book("Continuous Delivery for Dummies", "Someone Else"));
        store.addBook(new Book("Continuous Delivery of Insulin", "Another Person"));
    }


    @Override
    public void searchForBook(String title)
    {
        booksByTitle = store.findBooksByTitle(title);
    }

    @Override
    public void selectBook(String author)
    {
        for (Book book : booksByTitle)
        {
            if (book.isWrittenBy(author))
            {
                selectedBook = book;
            }
        }
    }

    @Override
    public void addSelectedItemToShoppingBasket()
    {
        store.addToBasket(selectedBook);
    }

    @Override
    public void assertListedInShoppingBasket(String item)
    {
        List<Book> basket = store.listBasketItems();

        for (Book book : basket)
        {
            if (item.equals(book.title()))
            {
                return;
            }
        }

        Assert.fail(String.format("Item '%s' not found in shopping basket", item));
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

    }
}
