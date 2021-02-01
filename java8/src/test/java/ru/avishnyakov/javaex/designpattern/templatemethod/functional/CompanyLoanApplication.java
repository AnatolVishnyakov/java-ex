package ru.avishnyakov.javaex.designpattern.templatemethod.functional;

public class CompanyLoanApplication extends LoanApplication {
    public CompanyLoanApplication(Company company) {
        super(company::checkIdentity,
                company::checkHistoricalDebt,
                company::checkProfitAndLoss);
    }
}
