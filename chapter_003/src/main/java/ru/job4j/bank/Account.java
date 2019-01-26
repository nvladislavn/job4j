package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Vladislav Nechaev
 * @since 23.01.2019
 */
public class Account {

    public static final Account EMPTY_ACCOUNT = new Account("Empty");

    private double values;
    private String requisites;

    public Account(String requisites) {
        this.requisites = requisites;
    }

    public void setValues(double values) {
        this.values = values;
    }

    public double getValues() {
        return values;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account otherAccount = (Account) o;
        return this.requisites.equals(otherAccount.getRequisites());
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
