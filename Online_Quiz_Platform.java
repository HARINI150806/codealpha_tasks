import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Online_Quiz_Platform 
{
    public static class BankingApp
    {
        private static double balance = 0.0;
        private static String accountHolderName;
        private static String accountNumber;
        private static List<String> transactionHistory = new ArrayList<>();

        public static void displayMenu()
        {
            System.out.println("\nWelcome to the Simple Banking Application");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Please choose an option: ");
        }

        public static void deposit(Scanner scanner) 
        {
            double amount = getValidAmount(scanner, "Enter the amount to deposit: ");
            if (amount > 0) 
            {
                balance += amount;
                System.out.println("Successfully deposited: $" + amount);
                transactionHistory.add("Deposited: $" + amount);
            } 
            else
            {
                System.out.println("Invalid deposit amount! Amount must be positive.");
            }
        }

        public static void withdraw(Scanner scanner) 
        {
            double amount = getValidAmount(scanner, "Enter the amount to withdraw: ");
            if (amount > 0 && amount <= balance) 
            {
                balance -= amount;
                System.out.println("Successfully withdrawn: $" + amount);
                transactionHistory.add("Withdrew: $" + amount);
            } 
            else if (amount > balance) 
            {
                System.out.println("Insufficient balance for the withdrawal!");
            } 
            else
            {
                System.out.println("Invalid withdrawal amount! Amount must be positive.");
            }
        }

        public static void checkBalance() 
        {
            System.out.println("Your current balance is: $" + balance);
        }

        public static void viewTransactionHistory()
        {
            System.out.println("\n***** Transaction History *****");
            if (transactionHistory.isEmpty()) 
            {
                System.out.println("No transactions yet.");
            } 
            else
            {
                for (String transaction : transactionHistory) {
                    System.out.println(transaction);
                }
            }
        }

        private static double getValidAmount(Scanner scanner, String prompt)
        {
            double amount = -1;
            while (amount <= 0) 
            {
                System.out.print(prompt);
                while (!scanner.hasNextDouble())
                {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.next(); // Discard invalid input
                }
                amount = scanner.nextDouble();
                if (amount <= 0) 
                {
                    System.out.println("Amount must be positive. Please try again.");
                }
            }
            return amount;
        }

        public static boolean login(Scanner scanner) 
        {
            System.out.println("Please log in to access your account.");
            System.out.print("Enter account number: ");
            String enteredAccountNumber = scanner.nextLine();

            if (!enteredAccountNumber.equals(accountNumber))
            {
                System.out.println("Invalid account number! Please try again.");
                return false;
            }

            System.out.print("Enter your name: ");
            String enteredName = scanner.nextLine();

            if (!enteredName.equals(accountHolderName))
            {
                System.out.println("Name does not match the account number. Please try again.");
                return false;
            }

            return true;
        }

        public static void initializeAccount(Scanner scanner) 
        {
            System.out.println("Welcome to the Simple Banking Application!");
            System.out.print("Enter your account holder name: ");
            accountHolderName = scanner.nextLine();

            System.out.print("Enter your account number: ");
            accountNumber = scanner.nextLine();

            System.out.println("Account created successfully!");
        }
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        BankingApp.initializeAccount(scanner);
        if (!BankingApp.login(scanner))
        {
            return; 
        }
        int choice;

        do 
        {
            BankingApp.displayMenu();  
            while (!scanner.hasNextInt()) 
            {
                System.out.println("Invalid input! Please choose a valid option.");
                scanner.next();  
            }
            choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice)
            {
                case 1:
                    BankingApp.deposit(scanner); 
                    break;
                case 2:
                    BankingApp.withdraw(scanner);  
                    break;
                case 3:
                    BankingApp.checkBalance(); 
                    break;
                case 4:
                    BankingApp.viewTransactionHistory(); 
                    break;
                case 5:
                    System.out.println("Thank you for using the banking application! Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        } while (choice != 5); 

        scanner.close();  
    }
}
