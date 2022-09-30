package com.cd.acceptance.examples.stubdemo.dsl;

import com.cd.acceptance.dsl.utils.Params;
import com.cd.acceptance.examples.stubdemo.drivers.KYCCheckProtocolDriver;

public class AccountRegistrationDsl {
    private final Params.DslContext context;
    private final KYCCheckProtocolDriver protocolDriver;

    public AccountRegistrationDsl(Params.DslContext context, KYCCheckProtocolDriver protocolDriver) {
        this.context = context;
        this.protocolDriver = protocolDriver;
    }

    public void approveAccount(String... args) {
        Params params = new Params(context, args);
        String account = params.Alias("name");

        protocolDriver.approveAccount(account);
    }

    public void rejectAccount(String... args) {
        Params params = new Params(context, args);
        String account = params.Alias("name");

        protocolDriver.rejectAccount(account);
    }
}
