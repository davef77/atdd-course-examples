package com.cd.acceptance.examples.stubdemo;

import com.cd.acceptance.examples.stubdemo.dsl.Dsl;
import org.junit.Test;
import junit.framework.AssertionFailedError;
import static junit.framework.TestCase.assertTrue;

public class StubDemonstrationAcceptanceTest extends Dsl {

    @Test
    public void shouldAllowInvoiceSubmissionForApprovedAccounts() {
        accounts.approveAccount("name: InvoiceSubmitter1");
        invoices.createAuthorisedAccount("name: InvoiceSubmitter1", "role: Submitter");
        invoices.submitInvoice("name: InvoiceSubmitter1", "invoice: invoice1", "Item: Software License");
        invoices.confirmInvoiceSubmitted("name: InvoiceSubmitter1", "invoice: invoice1");
    }

    @Test
    public void shouldRejectInvoiceSubmissionForRejectedAccounts() {
        try {
            accounts.rejectAccount("name: InvoiceSubmitter1");
            invoices.createAuthorisedAccount("name: InvoiceSubmitter1", "role: Submitter");
        } catch (AssertionFailedError e) {
            assertTrue(e.getMessage().startsWith("Unable to create authorised account for: "));
        }
    }
}