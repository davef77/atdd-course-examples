package com.cd.acceptance.examples.stubdemo.drivers;

import com.cd.acceptance.examples.accounting.ExternalKYCCheck;

import java.util.HashSet;
import java.util.Set;

public class StubExternalKYCCheck implements ExternalKYCCheck {
    private Set<String> validUsers = new HashSet<>();

    public void onVerifyApproveAccount(String account) {
        validUsers.add(account);
    }

    public void onVerifyRejectAccount(String account) {
        validUsers.remove(account);
    }

    @Override
    public boolean verifyAccount(String name) {
        return validUsers.contains(name);
    }
}
