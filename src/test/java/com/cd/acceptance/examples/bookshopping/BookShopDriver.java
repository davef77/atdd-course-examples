package com.cd.acceptance.examples.bookshopping;

public interface BookShopDriver
{
    void searchForBook(String title);

    void selectBook(String author);

    void addSelectedItemToShoppingBasket();

    void assertListedInShoppingBasket(String item);

    void checkOut(String item, String price, Card card);

    void assertItemPurchased(String item);

    void goToStore();
}
