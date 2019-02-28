package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Management
 *
 * @author Vladislav Nechaev
 * @since 23.01.2019
 */
public class Management {

    private Map<User, List<Account>> customersData;

    public Management() {
        this.customersData = new HashMap<>();
    }

    /**
     * addUser
     * Adds a User to the customersData
     *
     * @param user - the instance of User
     */
    public void addUser(User user) {
        customersData.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * deleteUser
     * Delete a User from the customersData
     *
     * @param user - the instance of User
     */
    public void deleteUser(User user) {
        customersData.remove(user);
    }

    /**
     * addAccountToUser
     * Adds a Account to the User
     *
     * @param passport - user passport
     * @param account  - user account
     */
    public void addAccountToUser(String passport, Account account) {
        User user = findUser(passport);
        if (user != User.EMPTY_USER && customersData.get(user).indexOf(account) < 0) { //TODO ? Здесь может быть NPE ( customersData.get(user).indexOf(account))?
            customersData.get(user).add(account);
        }
    }

    /**
     * deleteAccountFromUser
     * Delete the account from the user
     *
     * @param passport - user passport
     * @param account  - user account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = findUser(passport);
        if (user != User.EMPTY_USER && customersData.get(user).indexOf(account) < 0) {
            customersData.get(user).remove(account);
        }
    }

    /**
     * getUserAccounts
     * Gets user accounts
     *
     * @param passport - user passport
     * @return - the user accounts
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        User user = findUser(passport);
        if (user != User.EMPTY_USER) {
          accounts = this.customersData.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().equals(user))
                    .map(e -> e.getValue())
                    .collect(Collectors.toList())
                    .get(0);
        }
        return accounts;
    }

    /**
     * transferMoney
     * Transfers money from the first account to the second account.
     *
     * @param srcPassport   - first user passport
     * @param srcRequisite  - first account requisites
     * @param destPassport  - second user passport
     * @param destRequisite - second account requisites
     * @param amount        - amount of money
     * @return - transfer result
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        boolean result = false;
        User srcUser = findUser(srcPassport);
        User destUser = findUser(destPassport);
        if (srcUser == User.EMPTY_USER || destUser == User.EMPTY_USER) {
            return false;
        }
        Account srcAccount = findAccount(customersData.get(srcUser), srcRequisite);
        Account destAccount = findAccount(customersData.get(destUser), destRequisite);
        if (srcAccount == Account.EMPTY_ACCOUNT || destAccount == Account.EMPTY_ACCOUNT || srcAccount.getValues() < amount) {
            return false;
        }
        double lastSrcValues = srcAccount.getValues();
        double lastDestValues = destAccount.getValues();
        srcAccount.setValues(srcAccount.getValues() - amount);
        destAccount.setValues(destAccount.getValues() + amount);
        if ((lastSrcValues - srcAccount.getValues()) != amount || (destAccount.getValues() - lastDestValues) != amount) {
            throw new TransferException("Transfer failed!");
        } else {
            result = true;
        }
        return result;
    }

    /**
     * findAccount
     * Finds user account
     *
     * @param accounts   - the list of user accounts
     * @param requisites - account requisites
     * @return - found account
     */
    private Account findAccount(List<Account> accounts, String requisites) {
            return accounts
                    .stream()
                    .filter(account -> account.getRequisites().equals(requisites))
                    .findFirst()
                    .orElse(Account.EMPTY_ACCOUNT);
    }

    /**
     * findUser
     *
     * @param passport - user pasport
     * @return - found user
     */
    private User findUser(String passport) {
            return this.customersData.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().getPassport().equals(passport))
                    .map(e -> e.getKey())
                    .findFirst()
                    .orElse(User.EMPTY_USER);
    }
}
