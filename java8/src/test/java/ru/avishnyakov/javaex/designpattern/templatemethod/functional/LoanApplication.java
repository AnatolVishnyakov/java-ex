package ru.avishnyakov.javaex.designpattern.templatemethod.functional;

import ru.avishnyakov.javaex.designpattern.templatemethod.ApplicationDenied;

public class LoanApplication {
    private final Criteria identity;
    private final Criteria  creditHistory;
    private final Criteria incomeHistory;

    public LoanApplication(Criteria identity, Criteria creditHistory, Criteria incomeHistory) {
        this.identity = identity;
        this.creditHistory = creditHistory;
        this.incomeHistory = incomeHistory;
    }

    public void checkLoanApplication() throws ApplicationDenied {
        identity.check();
        creditHistory.check();
        incomeHistory.check();
        reportFindings();
    }

    private void reportFindings() {

    }
}
