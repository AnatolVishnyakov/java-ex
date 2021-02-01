package ru.avishnyakov.javaex.designpattern.templatemethod.imperative;

import ru.avishnyakov.javaex.designpattern.templatemethod.ApplicationDenied;

public class PersonalLoanApplication extends LoanApplication {
    @Override
    protected void checkIdentity() throws ApplicationDenied {

    }

    @Override
    protected void checkIncomeHistory() throws ApplicationDenied {

    }

    @Override
    protected void checkCreditHistory() throws ApplicationDenied {

    }
}
