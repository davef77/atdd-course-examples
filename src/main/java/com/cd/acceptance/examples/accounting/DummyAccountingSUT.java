package com.cd.acceptance.examples.accounting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyAccountingSUT {
    private final Map<String, User> registeredUsers = new HashMap<>();
    private final Map<String, User> currentUsers = new HashMap<>();
    private final List<SubmittedInvoice> submittedInvoices = new ArrayList<>();
    private final List<SubmittedInvoice> pendingReview = new ArrayList<>();
    private ExternalKYCCheck kycCheck;

    public DummyAccountingSUT(ExternalKYCCheck acctReg) {
        this.kycCheck = acctReg;
    }

    public void registerUser(String name, String password, UserRole role) {
        if (kycCheck.verifyAccount(name)) {
            registeredUsers.put(name, new User(name, password, role));
        }
    }

    public void loginUser(String name, String pasword) throws UserAccessDeniedException {
        User user = registeredUsers.get(name);
        if (user == null || !user.validate(pasword))
            throw new UserAccessDeniedException();

        currentUsers.put(name, user);
    }

    public void submitInvoice(String userName, Invoice invoice) throws UserAccessDeniedException {
        SubmittedInvoice submittedInvoice = new SubmittedInvoice(verifyAccessForUser(userName, UserRole.Submitter), invoice);
        submittedInvoices.add(submittedInvoice);
        pendingReview.add(submittedInvoice);
    }

    public List<SubmittedInvoice> listSubmittedInvoices() {
        return submittedInvoices;
    }

    public List<SubmittedInvoice> listInvoicesPendingReview(String userName) throws UserAccessDeniedException {
        verifyAccessForUser(userName, UserRole.AccountingSupervisor);

        return pendingReview;
    }

    private User verifyAccessForUser(String userName, UserRole permissionRequired) throws UserAccessDeniedException {
        User user = currentUsers.get(userName);
        if (user == null || !user.hasPermission(permissionRequired))
            throw new UserAccessDeniedException();
        return user;
    }
}
