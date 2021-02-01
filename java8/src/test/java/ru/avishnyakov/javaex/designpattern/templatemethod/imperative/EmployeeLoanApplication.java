package ru.avishnyakov.javaex.designpattern.templatemethod.imperative;

import ru.avishnyakov.javaex.designpattern.templatemethod.ApplicationDenied;

public class EmployeeLoanApplication extends PersonalLoanApplication {
    @Override
    protected void checkIncomeHistory() throws ApplicationDenied {

    }
}
