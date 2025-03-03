class Account {
    String name;
    int nationalCode;
    String city;
    int bankBranchNum;
    float accBalance;
    AccountType accountType;
    int score;

    enum AccountType {
        SAVING_ACC,
        SALARY_ACC,
        NRI;
    }

    public Account(String name, int nationalCode, String city, int bankBranchNum, float accBalance, String accountTypeString) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.city = city;
        this.bankBranchNum = bankBranchNum;
        this.accBalance = accBalance;
        this.accountType = AccountType.valueOf(accountTypeString);
        this.score = 0;
    }

    public Account(String name, int nationalCode, int bankBranchNum, float accBalance, String accountTypeString) {
        this(name, nationalCode, "Esfahan", bankBranchNum, accBalance, accountTypeString);
    }

    public Account(String name, int nationalCode, String city, int bankBranchNum, String accountTypeString) {
        this(name, nationalCode, city, bankBranchNum, 2000, accountTypeString);

    }

    public Account(String name, int nationalCode, int bankBranchNum, String accountTypeString) {
        this(name, nationalCode, "Esfahan", bankBranchNum, 2000, accountTypeString);
    }

    public String checkAccountBalance() {
        if (accountType == AccountType.SAVING_ACC && accBalance < 1) {
            return "you are Very Poor";
        } else if (accountType == AccountType.SAVING_ACC) {
            accBalance -= 1;
            score += 10;
            return "Your bank account balance : " + accBalance;
        }
        if ((accountType == AccountType.SALARY_ACC || accountType == AccountType.NRI) && accBalance < 1) {
            return "you are Very Poor";
        } else if (accountType == AccountType.SALARY_ACC || accountType == AccountType.NRI) {
            accBalance -= 2;
            score += 10;
            return "Your bank account balance : " + accBalance;
        }
        return "";

    }

    public String withdraw(int pickUp) {
        if (pickUp > 10000) {
            return "The Withdrawal Limit is 10000$";
        }
        if (accBalance < pickUp) {
            return "Your Account Balance is Not Enough";
        } else {
            accBalance -= pickUp;
            score += 20;
            return "Withdrawal Completed Successfully";
        }
    }

    public String deposit(int putIn) {
        if (putIn > 1000) {
            accBalance -= (accBalance * 0.01);
            return "The Deposit Limit is 1000$ \n" + "Your balance after fine :" + accBalance;
        } else {
            accBalance += putIn;
            score += 30;
            return "Deposit was Made Successfully";
        }

    }

    public double profitCalculation() {
        double profit = 0;
        if (accountType == AccountType.SAVING_ACC) {
            profit = (accBalance * 0.1);
        } else if (accountType == AccountType.SALARY_ACC || accountType == AccountType.NRI) {
            profit = (accBalance * 0.02);
        }
        return profit;
    }

    public String loanRequest(String name, int nationalCode, int loan) {
        if (!this.name.equals(name) || this.nationalCode != nationalCode) {
            return "Account Not Found";
        } else {
            if (accountType == AccountType.SAVING_ACC || accountType == AccountType.SALARY_ACC) {
                if (loan > 500) {
                    return "Loan Application Applied UnSuccessfully";
                }
                if (accBalance / 2 < loan) {
                    return "Your Credit is not Enough for This Loan";
                } else if (score < 100) {
                    return "You don't Have Enough Score";
                } else {
                    accBalance += loan;
                    return "Loan Application Applied Successfully";

                }
            }
            if (accountType == AccountType.NRI) {
                if (accBalance / 2 < loan) {
                    return "Your Credit is not Enough for This Loan";
                } else if (score < 100) {
                    return "You don't Have Enough Score";

                } else {
                    accBalance += loan;
                    return "Loan Application Applied Successfully";
                }
            }


            return "";
        }
    }


    public String loanRequest(String name, int nationalCode, String loanType) {
        LoansType loansType = LoansType.valueOf(loanType);
        if (!this.name.equals(name) || this.nationalCode != nationalCode) {
            return "Account Not Found";
        } else {
            this.accBalance += loansType.getAmount();
            return "Loan Application Applied Successfully";
        }
    }

    public double credit = 0;

    public double credit() {
        return this.accBalance;
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
        System.out.println(account1.checkAccountBalance());
        System.out.println(account1.withdraw(5000));
        System.out.println(account1.deposit(1000));
        System.out.println(account1.deposit(500));
        System.out.println(account1.deposit(7000));
        System.out.println(account1.withdraw(1000));
        System.out.println(account1.deposit(11000));
        System.out.println(account1.checkAccountBalance());
        System.out.println(account1.profitCalculation());
        System.out.println(account1.loanRequest("Ftm", 124, 400));
        System.out.println(account1.loanRequest("Ftm", 123, 400));
        System.out.println(account1.loanRequest("Ftm", 123, "BUSINESS"));
        System.out.println(account1.credit());
    }
}


