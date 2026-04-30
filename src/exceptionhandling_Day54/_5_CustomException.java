package exceptionhandling_Day54;

public class _5_CustomException {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Towhid", 1000.0);

        // Test InsufficientFundsException (checked)
        try {
            account.withdraw(500);   // Succeeds
            account.withdraw(800);   // Throws InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Shortfall: " + e.getAmount()); // Extra field!
        }

        // Test InvalidAgeException (unchecked)
        try {
            account.setOwnerAge(25);   // Valid
            account.setOwnerAge(-5);   // Throws InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Invalid age was: " + e.getAge());
        }
    }
}
    // Custom CHECKED exception (extends Exception)
    class InsufficientFundsException extends Exception {
        private double amount;  // Extra field to carry info

        public InsufficientFundsException(double amount) {
            super("Insufficient funds. Short by: " + amount);
            this.amount = amount;
        }

        public InsufficientFundsException(String message, double amount) {
            super(message);
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }
    }

    // Custom UNCHECKED exception (extends RuntimeException)
    class InvalidAgeException extends RuntimeException {
        private int age;

        public InvalidAgeException(int age) {
            super("Invalid age: " + age + ". Age must be between 0 and 150.");
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }

    // Using custom exceptions
     class BankAccount {
        private double balance;
        private String owner;

        public BankAccount(String owner, double initialBalance) {
            this.owner = owner;
            this.balance = initialBalance;
        }

        public void withdraw(double amount) throws InsufficientFundsException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive");
            }
            if (amount > balance) {
                double shortfall = amount - balance;
                throw new InsufficientFundsException(shortfall); // Custom exception
            }
            balance -= amount;
            System.out.println("Withdrew " + amount + ". Balance: " + balance);
        }

        public void setOwnerAge(int age) {
            if (age < 0 || age > 150) {
                throw new InvalidAgeException(age); // Unchecked — no throws needed
            }
            System.out.println("Age set to: " + age);
        }
    }


