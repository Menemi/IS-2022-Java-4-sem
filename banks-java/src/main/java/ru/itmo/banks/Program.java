package ru.itmo.banks;

import ru.itmo.banks.Accounts.Account;
import ru.itmo.banks.Banks.Bank;
import ru.itmo.banks.Banks.CentralBank;
import ru.itmo.banks.Clients.Client;
import ru.itmo.banks.Clients.ClientBuilder;
import ru.itmo.banks.Clients.Passport;
import ru.itmo.banks.Clients.Person;
import ru.itmo.banks.Exceptions.BanksException;
import ru.itmo.banks.Transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws BanksException {
        choosingTypeOfUser();
    }

    static String chooseTypeOfUser;
    static TimeMachine timeMachine = new TimeMachine();
    static CentralBank centralBank = CentralBank.getInstance("Central Bank");
    static List<Person> people = new ArrayList<>();

    // краткий гайд по работе с тестами: когда предлагаются на выбор функции, всегда можно писать либо номер
    // функции, либо её название. В примере я оставил именно названия функций, чтоб можно было легко
    // ориентироваться при проверке
    // также я оставил комментарии в виде туду, расставив их перед каждой функцией с соответсвующим названием,
    // так что при проверке тоже можно легко и быстро свапаться между функциями
    static void choosingTypeOfUser() throws BanksException {
        System.out.println("Type your user type 'bank manager / client' (you can type 'choose again' to " +
                "choose again or 'stop' to end the program):");
        chooseTypeOfUser = new Scanner(System.in).nextLine();
        System.out.println();
        usersTypeLogic();
    }

    // да, я понимаю, что свитч-кейс - круто, но пощадите, пожалуйста...
    // я правда знаю, как с ним работать
    static void bankManager() throws BanksException {
        while (true) {
            // todo: BANK MANAGER
            System.out.println("You can type:");
            System.out.println("1. Create bank");
            System.out.println("2. Banks list");
            System.out.println("3. Rewind time");
            System.out.println("4. Choose again (send you to start menu)");
            String function = new Scanner(System.in).nextLine();

            // todo: create bank
            if (function.equalsIgnoreCase("create bank") || function.equals("1")) {
                System.out.println("Type all the parametrs with Enter:");
                System.out.println("1. bank name");
                String bankName = new Scanner(System.in).nextLine();

                System.out.println(
                        "2. percents for deposit account (count, lower bound, upper bound, percent (count times))");
                List<PercentOfTheAmount> percentsOfTheAmount = new ArrayList<>();
                int times = new Scanner(System.in).nextInt();
                for (int i = 0; i < times; i++) {
                    percentsOfTheAmount.add(new PercentOfTheAmount(
                            new Scanner(System.in).nextDouble(),
                            new Scanner(System.in).nextDouble(),
                            new Scanner(System.in).nextDouble()));
                }

                System.out.println("3. fixed percent");
                double fixedPercent = new Scanner(System.in).nextDouble();
                System.out.println("4. max withdraw amount");
                double maxWithdrawAmount = new Scanner(System.in).nextDouble();
                System.out.println("5. max remittance amount");
                double maxRemittanceAmount = new Scanner(System.in).nextDouble();
                System.out.println("6. credit limit");
                double creditLimit = new Scanner(System.in).nextDouble();
                System.out.println("7. commission");
                double commission = new Scanner(System.in).nextDouble();

                System.out.println("8. account unblocking period (year, month, day)");
                LocalDate accountUnblockingPeriod = LocalDate.of(
                        new Scanner(System.in).nextInt(),
                        new Scanner(System.in).nextInt(),
                        new Scanner(System.in).nextInt());

                centralBank.createBank(
                        bankName,
                        percentsOfTheAmount,
                        fixedPercent,
                        maxWithdrawAmount,
                        maxRemittanceAmount,
                        creditLimit,
                        commission,
                        accountUnblockingPeriod);

                System.out.println();
            }

            // todo: banks list
            else if (function.equalsIgnoreCase("banks list") || function.equals("2")) {
                for (Bank bank : centralBank.getBanks()) {
                    System.out.printf("%d. %s (id: %d)%n", bank.id, bank.name, bank.id);
                }

                System.out.println();
            }

            // todo: rewind time
            else if (function.equalsIgnoreCase("rewind time") || function.equals("3")) {
                System.out.println("Type date to rewind (year, month, day)");
                LocalDate dateToRewind = LocalDate.of(
                        new Scanner(System.in).nextInt(),
                        new Scanner(System.in).nextInt(),
                        new Scanner(System.in).nextInt());
                timeMachine.TimeRewind(centralBank, dateToRewind);

                choosingTypeOfUser();
                return;
            }

            // todo: choose again
            else if (function.equalsIgnoreCase("choose again") || function.equals("4")) {
                System.out.println();
                choosingTypeOfUser();
                return;
            } else {
                return;
            }
        }
    }

    static void client() throws BanksException {
        while (true) {
            // todo: CLIENT
            System.out.println("You can type:");
            System.out.println("1. Register in system");
            System.out.println("2. Create new account");
            System.out.println("3. Accounts list");
            System.out.println("4. Balance");
            System.out.println("5. withdraw");
            System.out.println("6. replenishment");
            System.out.println("7. remittance");
            System.out.println("8. Cancel transaction");
            System.out.println("9. Transaction history");
            System.out.println("10. Choose again");

            String function = new Scanner(System.in).nextLine();

            // todo: register in system
            if (function.equalsIgnoreCase("register in system") || function.equals("1")) {
                System.out.println("Type you name and surname:");
                ClientBuilder clientBuilder = new Client();
                Person person = clientBuilder.createNewClient(new Scanner(System.in).nextLine(), new Scanner(System.in).nextLine());

                System.out.println("Type your address if you want to verify your profile or just tap Enter");

                String address = new Scanner(System.in).nextLine();
                if (!address.equals("")) {
                    clientBuilder.setAddress("str");
                }

                System.out.println(
                        "Type your passport (series ENTER number) if you want to verify your profile or just tap Enter");
                String series = new Scanner(System.in).nextLine();
                String number = new Scanner(System.in).nextLine();
                if (!series.equals("") && !number.equals("")) {
                    clientBuilder.setPassport(new Passport(Integer.parseInt(series), Integer.parseInt(number)));
                }

                people.add(person);

                System.out.println();
            }

            // todo: create new account
            else if (function.equalsIgnoreCase("create new account") || function.equals("2")) {
                System.out.println("Choose the bank (type the number):");
                for (Bank bank : centralBank.getBanks()) {
                    System.out.printf("%d. %s (id: %d)%n", bank.id, bank.name, bank.id);
                }

                int bankNumber = new Scanner(System.in).nextInt();
                for (Bank bank : centralBank.getBanks()) {
                    if (bank.id == bankNumber) {
                        System.out.println("Type your full name:");
                        String name = new Scanner(System.in).nextLine();
                        for (Person person : people) {
                            if ((person.getName() + " " + person.getSurname()).equals(name)) {
                                System.out.println(
                                        "Type the type of your account (credit / debit / deposit) and " +
                                                "then make a deposit (or type 0):");
                                String accountType = new Scanner(System.in).nextLine();
                                switch (accountType) {
                                    case "credit" -> bank.сreateCreditAccount(person, new Scanner(System.in).nextDouble());
                                    case "debit" -> bank.сreateDebitAccount(person, new Scanner(System.in).nextDouble());
                                    case "deposit" -> bank.сreateDepositAccount(person, new Scanner(System.in).nextDouble());
                                }
                            }

                            break;
                        }

                        break;
                    }
                }

                System.out.println();
            }

            // todo: accounts list
            else if (function.equalsIgnoreCase("accounts list") || function.equals("3")) {
                System.out.println("Type your full name:");
                String name = new Scanner(System.in).nextLine();
                for (Person person : people) {
                    if ((person.getName() + " " + person.getSurname()).equals(name)) {
                        int tempCounter = 1;
                        for (Account account : person.getAccounts()) {
                            // естесвенно я понимаю, что можно вывести больше информации,
                            // просто это займёт больше аналогичных строк
                            System.out.printf("%d. id: %d; balance: %f%n", tempCounter++, account.getId(), account.getBalance());
                        }
                    }
                }

                System.out.println();
            }

            // todo: balance
            else if (function.equalsIgnoreCase("balance") || function.equals("4")) {
                System.out.println("Type your full name and then your account id:");
                String name = new Scanner(System.in).nextLine();
                String id = new Scanner(System.in).nextLine();
                for (Person person : people) {
                    if ((person.getName() + " " + person.getSurname()).equals(name)) {
                        for (Account account : person.getAccounts()) {
                            if (String.valueOf(account.getId()).equals(id)) {
                                System.out.printf("Your balance: %f%n", account.getBalance());
                                break;
                            }
                        }
                    }

                    break;
                }

                System.out.println();
            }

            // todo: withdraw
            else if (function.equalsIgnoreCase("withdraw") || function.equals("5")) {
                System.out.println("Type your full name and then your account id:");
                String name = new Scanner(System.in).nextLine();
                String id = new Scanner(System.in).nextLine();
                for (Person person : people) {
                    if ((person.getName() + " " + person.getSurname()).equals(name)) {
                        for (Account account : person.getAccounts()) {
                            if (String.valueOf(account.getId()).equals(id)) {
                                for (Bank bank : centralBank.getBanks()) {
                                    if (bank.getClientsAccounts().contains(account)) {
                                        System.out.println("Type how much money do you want to withdraw:");
                                        bank.withdraw(account, new Scanner(System.in).nextDouble());
                                        System.out.printf("Now your balance on this account: %f%n", account.getBalance());
                                        break;
                                    }
                                }

                                break;
                            }
                        }
                    }

                    break;
                }

                System.out.println();
            }

            // todo: replenishment
            else if (function.equalsIgnoreCase("replenishment") || function.equals("6")) {
                System.out.println("Type your full name and then your account id:");
                String name = new Scanner(System.in).nextLine();
                String id = new Scanner(System.in).nextLine();
                for (Person person : people) {
                    if ((person.getName() + " " + person.getSurname()).equals(name)) {
                        for (Account account : person.getAccounts()) {
                            if (String.valueOf(account.getId()).equals(id)) {
                                for (Bank bank : centralBank.getBanks()) {
                                    if (bank.getClientsAccounts().contains(account)) {
                                        System.out.println("Type how much money do you want to replenish:");
                                        bank.replenishment(account, new Scanner(System.in).nextDouble());
                                        System.out.printf("Now your balance on this account: %f%n", account.getBalance());
                                        break;
                                    }
                                }

                                break;
                            }
                        }
                    }

                    break;
                }

                System.out.println();
            }

            // todo: remmitance
            else if (function.equalsIgnoreCase("remmitance") || function.equals("7")) {
                System.out.println("Type your full name and then your account id:");
                String name = new Scanner(System.in).nextLine();
                String id = new Scanner(System.in).nextLine();
                for (Person person : people) {
                    if ((person.getName() + " " + person.getSurname()).equals(name)) {
                        for (Account sender : person.getAccounts()) {
                            if (String.valueOf(sender.getId()).equals(id)) {
                                for (Bank bank : centralBank.getBanks()) {
                                    if (bank.getClientsAccounts().contains(sender)) {
                                        System.out.println("Type the account id to which you want to transfer");
                                        String recipientAccountId = new Scanner(System.in).nextLine();
                                        for (Bank bank2 : centralBank.getBanks()) {
                                            for (Account recipient : bank2.getClientsAccounts()) {
                                                if (String.valueOf(recipient.getId()).equals(recipientAccountId)) {
                                                    System.out.println(
                                                            "Type how much money do you want to transfer:");
                                                    double amount = new Scanner(System.in).nextDouble();
                                                    bank.remittance(
                                                            sender,
                                                            recipient,
                                                            amount);
                                                    System.out.printf("Now your balance on this account: %f%n", sender.getBalance());
                                                    System.out.printf("And your recipient balance now is: %f%n", recipient.getBalance());
                                                    break;
                                                }
                                            }
                                        }

                                        break;
                                    }
                                }

                                break;
                            }
                        }
                    }

                    break;
                }

                System.out.println();
            }

            // todo: cancel transaction
            else if (function.equalsIgnoreCase("cancel transaction") || function.equals("8")) {
                System.out.println("Type your full name and then your account id:");
                String name = new Scanner(System.in).nextLine();
                String id = new Scanner(System.in).nextLine();
                for (Person person : people) {
                    String tempName = person.getName() + " " + person.getSurname();
                    if (!tempName.equals(name)) {
                        continue;
                    }
                    for (Account account : person.getAccounts()) {
                        if (String.valueOf(account.getId()).equals(id)) {
                            for (Bank bank : centralBank.getBanks()) {
                                if (bank.getClientsAccounts().contains(account)) {
                                    System.out.println("Type the transaction id:");
                                    for (Transaction transaction : account.getTransactions()) {
                                        if (String.valueOf(transaction.getId()).equals(new Scanner(System.in).nextLine())) {
                                            bank.cancellation(account, transaction);
                                            System.out.printf("Now your balance on this account: %f%n", account.getBalance());
                                            break;
                                        }
                                    }

                                    break;
                                }
                            }

                            break;
                        }
                    }

                    break;
                }

                System.out.println();
            }

            // todo: transaction history
            else if (function.equalsIgnoreCase("transaction history") || function.equals("9")) {
                System.out.println("Type your full name and then your account id:");
                String name = new Scanner(System.in).nextLine();
                String id = new Scanner(System.in).nextLine();
                for (Person person : people) {
                    if ((person.getName() + " " + person.getSurname()).equals(name)) {
                        for (Account account : person.getAccounts()) {
                            if (String.valueOf(account.getId()).equals(id)) {
                                if (centralBank.getBanks().stream().anyMatch(bank -> bank.getClientsAccounts().contains(account))) {
                                    for (Transaction transaction : account.getTransactions()) {
                                        System.out.printf("Transaction id: %d%n", transaction.getId());
                                        System.out.printf("Recipient id: %d%n", transaction.getRecipient().getId());
                                        if (transaction.getSender() != null) {
                                            System.out.printf("Sender id: %d%n", transaction.getSender().getId());
                                        }

                                        System.out.printf("Transaction amount: %f%n", transaction.getAmount());
                                        System.out.printf("Transaction time: %s%n", transaction.getTransactionTime());
                                        System.out.println();
                                    }
                                }

                                break;
                            }
                        }
                    }

                    break;
                }

                System.out.println();
            }

            // todo: choose again
            else if (function.equalsIgnoreCase("choose again") || function.equals("10")) {
                System.out.println();
                choosingTypeOfUser();
                return;
            } else {
                return;
            }
        }
    }

    static void usersTypeLogic() throws BanksException {
        if (chooseTypeOfUser.equalsIgnoreCase("bank manager") || chooseTypeOfUser.equals("1")) {
            bankManager();
        } else if (chooseTypeOfUser.equalsIgnoreCase("client") || chooseTypeOfUser.equals("2")) {
            client();
        } else if (chooseTypeOfUser.equalsIgnoreCase("choose again") || chooseTypeOfUser.equals("3")) {
            choosingTypeOfUser();
        } else if (chooseTypeOfUser.equalsIgnoreCase("stop") || chooseTypeOfUser.equals("4")) {
            return;
        } else {
            System.out.println("Wrong command, try again!");
            choosingTypeOfUser();
        }
    }
}
