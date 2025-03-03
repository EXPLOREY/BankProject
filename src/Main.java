class Account {
    String name;
    int nationalCode;
    String city;
    int bankBranchNum;
    double accBalance;
    AccountType accountType;
    int score;

    enum AccountType {
        SAVING_ACC,
        SALARY_ACC,
        NRI;
    }

    public Account(String name, int nationalCode, String city, int bankBranchNum, double accBalance, String accountTypeString) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.city = city;
        this.bankBranchNum = bankBranchNum;
        this.accBalance = accBalance;
        this.accountType = AccountType.valueOf(accountTypeString.toUpperCase());
        this.score = 0;
    }

    public Account(String name, int nationalCode, int bankBranchNum, double accBalance, String accountTypeString) {
        this(name, nationalCode, "Esfahan", bankBranchNum, accBalance, accountTypeString);
    }

    public Account(String name, int nationalCode, String city, int bankBranchNum, String accountTypeString) {
        this(name, nationalCode, city, bankBranchNum, 2000, accountTypeString);

    }

    public Account(String name, int nationalCode, int bankBranchNum, String accountTypeString) {
        this(name, nationalCode, "Esfahan", bankBranchNum, 2000, accountTypeString);
    }

    public void checkAccountBalance() {
        if (accountType == AccountType.SAVING_ACC && accBalance < 1) {
            System.out.println("you are Very Poor");
        } else if (accountType == AccountType.SAVING_ACC) {
            accBalance -= 1;
            score += 10;
            System.out.println("Your bank account balance : " + accBalance);
        }
        if ((accountType == AccountType.SALARY_ACC || accountType == AccountType.NRI) && accBalance < 1) {
            System.out.println("you are Very Poor");
        } else if (accountType == AccountType.SALARY_ACC || accountType == AccountType.NRI) {
            accBalance -= 2;
            score += 10;
            System.out.println("Your bank account balance : " + accBalance);
        }

    }

    public void withdraw(int pickUp) {
        if (pickUp > 10000) {
            System.out.println("The Withdrawal Limit is 10000$");
            return;
        }
        if (accBalance < pickUp) {
            System.out.println("Your Account Balance is Not Enough");
            return;
        } else {
            accBalance -= pickUp;
            score += 20;
            System.out.println("Withdrawal Completed Successfully");
        }
    }

    public void deposit(int putIn) {
        if (putIn > 1000) {
            System.out.println("The Deposit Limit is 1000$");
            accBalance -= (accBalance * 0.01);
            System.out.println("Your balance after fine :" + accBalance);
            return;
        } else {
            accBalance += putIn;
            score += 30;
            System.out.println("Deposit was Made Successfully");
        }

    }

    public void profitCalculation() {
        if (accountType == AccountType.SAVING_ACC) {
            accBalance += (accBalance * 0.1);
        } else if (accountType == AccountType.SALARY_ACC || accountType == AccountType.NRI) {
            accBalance += (accBalance * 0.02);
        }
    }

    public void loanRequest(String name, int NationalCode, int loan) {

        if (!this.name.equals(name) || this.nationalCode != nationalCode) {
            System.out.println("Account Not Found");
            return;
        }
        if (accountType == AccountType.SAVING_ACC || accountType == AccountType.SALARY_ACC) {
            if (loan > 500) {
                return;
            }
        } else if (accBalance < loan / 2) {
            System.out.println("Your Credit is not Enough for This Loan");
            return;
        } else if (score < 100) {
            System.out.println("You don't Have Enough Score");
            return;
        } else {
            System.out.println("Loan Application Applied Successfully");
            accBalance += loan;
        }


    }

    public void loanRequest(String name, int nationalCode, LoansType loanType) {
        if (!this.name.equals(name) || this.nationalCode != nationalCode) {
            System.out.println("Account Not Found");
            return;
        }

        if (loanType == LoansType.BUSINESS) {
            accBalance += loanType.getAmount();
        } else if (loanType == LoansType.STUDENT) {
            accBalance += loanType.getAmount();
        } else if (loanType == LoansType.MORTAGE) {
            accBalance += loanType.getAmount();
        } else if (loanType == LoansType.MARRIGE) {
            accBalance += loanType.getAmount();
        } else {
            System.out.println("Invalid Loan Type");
            return;
        }

        System.out.println("Loan Application Applied Successfully");
    }

}


enum LoansType {
    BUSINESS(7000),
    STUDENT(2000),
    MORTAGE(5000),
    MARRIGE(4000);

    private int amount;

    LoansType(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

}

public class Main {

    public static void main(String[] args) {
        Account account1 = new Account("Ftm", 123, "Tehran", 11, 50000, "SAVING_ACC");

        account1.checkAccountBalance();
        account1.withdraw(5000);
        account1.deposit(1000);
        account1.deposit(500);
        account1.deposit(7000);
        account1.withdraw(1000);
        account1.deposit(11000);
        account1.checkAccountBalance();

        account1.profitCalculation();

        account1.loanRequest("Ftm", 124, 400);
        account1.loanRequest("Ftm", 123, 400);

        account1.loanRequest("Ftm", 123, LoansType.BUSINESS);
    }
}


