package com.cd.acceptance.examples.accounting.dsl;

import com.cd.acceptance.examples.accounting.drivers.AccountingSystemProtocolDriver;
import com.cd.acceptance.dsl.utils.Params;

import java.util.List;

public class InvoicesDsl {
    private final Params.DslContext context;
    private final AccountingSystemProtocolDriver driver;

    protected InvoicesDsl(Params.DslContext context, AccountingSystemProtocolDriver driver) {
        this.context = context;
        this.driver = driver;
    }

    public void createAuthorisedAccount(String... args) {
        Params params = new Params(context, args);

        String name = params.Alias("name");
        String role = params.Optional("role", "Accountant");
        String password = params.Optional("password", "password123");

        driver.createAuthorisedAccount(name, password, role);
    }

    public void submitInvoice(String... args) {
        Params params = new Params(context, args);

        String userName = params.Alias("name");
        String invoice = params.Optional("invoice", "anInvoice");
        String purchaseOrder = params.Optional("po", "po1");
        String invoiceNumber = params.OptionalSequence("InvoiceNo", 1);
        String total = params.Optional("total", "0");
        List<String> items = params.OptionalList("item", new String[] {"item1"});

        driver.submitInvoice(userName, invoice, purchaseOrder, invoiceNumber, items, total);
    }

    public void confirmInvoiceSubmitted(String... args) {
        Params params = new Params(context, args);

        String userName = params.Alias("name");
        String invoice = params.Optional("invoice", "anInvoice");

        driver.confirmInvoiceSubmitted(userName, invoice);
    }

    public void confirmPendingAuthorisations(String... args) {
        Params params = new Params(context, args);

        String userName = params.Alias("name");
        String pendingItem = params.Optional("pendingItem", "someItem");

        driver.confirmPendingAuthorisations(userName, pendingItem);
    }

    public void confirmInvoiceRejected(String... args) {
    }
}
