package com.cd.acceptance.examples.accounting.dsl;

import com.cd.acceptance.examples.accounting.ExternalKYCCheck;
import com.cd.acceptance.examples.accounting.drivers.DummyAccountingSystemProtocolDriver;
import com.cd.acceptance.dsl.utils.Params;
import com.cd.acceptance.examples.accounting.DummyAccountingSUT;
import org.junit.Before;

public class Dsl {
    private final Params.DslContext context = new Params.DslContext();

    protected InvoicesDsl invoices;

    @Before
    public void setUp() {
//        In reality the Protocol Driver would connect to the real "System Under Test"
//        For the purpose of this example, we use a simple class to represent the SUT here.
//        A real Protocol Driver would do whatever it takes to translate from DSL to interactions with the SUT
        DummyAccountingSUT sut = new DummyAccountingSUT(new AccountApproval());
        invoices = new InvoicesDsl(context, new DummyAccountingSystemProtocolDriver(sut));
    }

    class AccountApproval implements ExternalKYCCheck {

        @Override
        public boolean verifyAccount(String name) {
            return true;
        }
    }
}
