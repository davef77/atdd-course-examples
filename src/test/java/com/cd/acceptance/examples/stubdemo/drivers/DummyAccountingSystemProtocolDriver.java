package com.cd.acceptance.examples.stubdemo.drivers;

import com.cd.acceptance.examples.accounting.*;

import java.util.List;

import static junit.framework.TestCase.fail;

// Pretend System Under Test
public class DummyAccountingSystemProtocolDriver implements AccountingSystemProtocolDriver {
    private final DummyAccountingSUT accountingSystem;

    public DummyAccountingSystemProtocolDriver(DummyAccountingSUT accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    @Override
    public void createAuthorisedAccount(String name, String password, String role) {
        try {
            accountingSystem.registerUser(name, password, UserRole.valueOf(role));
            accountingSystem.loginUser(name, password);
        } catch (UserAccessDeniedException e)
        {
            fail("Unable to create authorised account for: " + name);
        }
    }

    @Override
    public void submitInvoice(String userName, String invoiceName, String purchaseOrder, String invoiceNumber, List<String> items, String total) {
        try
        {
            Invoice invoice = new Invoice(invoiceName, purchaseOrder, invoiceNumber, items, total);
            accountingSystem.submitInvoice(userName, invoice);
        } catch (UserAccessDeniedException e) {
            fail("User '" + userName + "' does not have permission to submit invoices");
        }
    }

    @Override
    public void confirmInvoiceSubmitted(String userName, String invoice) {
        List<SubmittedInvoice> invoices = accountingSystem.listSubmittedInvoices();
        for (SubmittedInvoice item : invoices) {
            if (item.getUser().getName().equals(userName) && item.getInvoice().getInvoiceName().equals(invoice)) {
                return;
            }
        }
        fail("Invoice '" + invoice + "' does not exist!");
    }

    @Override
    public void confirmPendingAuthorisations(String userName, String pendingItem) {
        try {
            List<SubmittedInvoice> invoices = accountingSystem.listInvoicesPendingReview(userName);
            for (SubmittedInvoice item : invoices) {
                if (item.getInvoice().getInvoiceName().equals(pendingItem)) {
                    return;
                }
                fail("'" + pendingItem + "' Is not pending review!");
            }
        } catch (UserAccessDeniedException e)
            {
                fail("Access Denied for User '" + userName + "'");
            }
    }
}
