package ru.avishnyakov.javaex.designpattern.templatemethod.imperative;

import ru.avishnyakov.javaex.designpattern.templatemethod.ApplicationDenied;

public abstract class LoanApplication {
    public void checkLoanApplication() throws ApplicationDenied {
        checkIdentity();
        checkCreditHistory();
        checkIncomeHistory();
        reportFindings();
    }

    protected abstract void checkIdentity() throws ApplicationDenied;

    protected abstract void checkIncomeHistory() throws ApplicationDenied;

    protected abstract void checkCreditHistory() throws ApplicationDenied;

    protected void reportFindings() {

    }
}
