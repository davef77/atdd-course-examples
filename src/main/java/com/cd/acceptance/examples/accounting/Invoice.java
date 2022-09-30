package com.cd.acceptance.examples.accounting;

import java.util.List;

public class Invoice {
    private String invoiceName;
    private String purchaseOrder;
    private String invoiceNumber;
    private List<String> items;
    private String total;

    public Invoice(String invoiceName, String purchaseOrder, String invoiceNumber, List<String> items, String total) {

        this.invoiceName = invoiceName;
        this.purchaseOrder = purchaseOrder;
        this.invoiceNumber = invoiceNumber;
        this.items = items;
        this.total = total;
    }

    public String getInvoiceName() {
        return invoiceName;
    }
}
