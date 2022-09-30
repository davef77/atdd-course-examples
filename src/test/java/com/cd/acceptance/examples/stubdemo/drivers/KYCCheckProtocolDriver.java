package com.cd.acceptance.examples.stubdemo.drivers;

public class KYCCheckProtocolDriver {
    private StubExternalKYCCheck kycCheck;

    public KYCCheckProtocolDriver(StubExternalKYCCheck kycCheck) {
        this.kycCheck = kycCheck;
    }

    public void approveAccount(String account) {
        kycCheck.onVerifyApproveAccount(account);
    }

    public void rejectAccount(String account) {
        kycCheck.onVerifyRejectAccount(account);
    }
}
