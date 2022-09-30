package com.cd.acceptance.examples.stubdemo.dsl;

import com.cd.acceptance.dsl.utils.Params;
import com.cd.acceptance.examples.accounting.DummyAccountingSUT;
import com.cd.acceptance.examples.stubdemo.drivers.DummyAccountingSystemProtocolDriver;
import com.cd.acceptance.examples.stubdemo.drivers.KYCCheckProtocolDriver;
import com.cd.acceptance.examples.stubdemo.drivers.StubExternalKYCCheck;
import org.junit.Before;

public class Dsl {
    private final Params.DslContext context = new Params.DslContext();

    protected InvoicesDsl invoices;
    protected AccountRegistrationDsl accounts;

    @Before
    public void setUp() {
//        In reality the Protocol Driver would connect to the real "System Under Test"
//        For the purpose of this example, we use a simple class to represent the SUT here.
//        A real Protocol Driver would do whatever it takes to translate from DSL to interactions with the SUT
        StubExternalKYCCheck kycCheck = new StubExternalKYCCheck();
        DummyAccountingSUT sut = new DummyAccountingSUT(kycCheck);

        invoices = new InvoicesDsl(context, new DummyAccountingSystemProtocolDriver(sut));
        accounts = new AccountRegistrationDsl(context, new KYCCheckProtocolDriver(kycCheck));
    }
}
