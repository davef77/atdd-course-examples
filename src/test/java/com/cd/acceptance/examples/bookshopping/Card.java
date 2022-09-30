package com.cd.acceptance.examples.bookshopping;

public class Card
{
    public final String cardNo;
    public final String date;
    public final String cvc;

    public Card(String cardNo, String date, String cvc)
    {
        this.cardNo = cardNo;
        this.date = date;
        this.cvc = cvc;
    }
}
