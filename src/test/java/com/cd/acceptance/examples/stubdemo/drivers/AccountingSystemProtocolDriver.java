package com.cd.acceptance.examples.stubdemo.drivers;

import java.util.List;

public interface AccountingSystemProtocolDriver {
    void createAuthorisedAccount(String name, String password, String role);

    void submitInvoice(String userName, String invoiceName, String purchaseOrder, String invoiceNumber, List<String> items, String total);

    void confirmInvoiceSubmitted(String userName, String invoice);

    void confirmPendingAuthorisations(String userName, String pendingItem);
}
