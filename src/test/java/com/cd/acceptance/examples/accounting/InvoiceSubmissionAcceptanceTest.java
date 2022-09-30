package com.cd.acceptance.examples.accounting;

import com.cd.acceptance.examples.accounting.dsl.Dsl;
import org.junit.Test;

public class InvoiceSubmissionAcceptanceTest extends Dsl {

    @Test
    public void shouldAcknowledgeInvoiceSubmission() {
        invoices.createAuthorisedAccount("name: InvoiceSubmitter1", "role: Submitter");
        invoices.submitInvoice("name: InvoiceSubmitter1", "invoice: invoice1", "Item: Software License");
        invoices.confirmInvoiceSubmitted("name: InvoiceSubmitter1", "invoice: invoice1");
    }

    @Test
    public void shouldSendSubmittedInvoiceToSupervisor() {
        invoices.createAuthorisedAccount("name: InvoiceSubmitter1", "role: Submitter");
        invoices.createAuthorisedAccount("name: Supervisor1", "role: AccountingSupervisor");
        invoices.submitInvoice("name: InvoiceSubmitter1", "invoice: invoice1", "Item: Software License");
        invoices.confirmPendingAuthorisations("name: Supervisor1", "pendingItem: invoice1");
    }
}