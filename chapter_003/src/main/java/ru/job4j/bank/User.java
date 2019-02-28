package ru.job4j.bank;

import java.util.Date;
import java.util.Objects;

/**
 * @author Vladislav Nechaev
 * @since 22.01.2019
 */
public class User {

    public static final User EMPTY_USER = new User("EmptyUser", "EmptyPassport");
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.getName()) && Objects.equals(passport, user.getPassport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

    private class Passport {
       private final Date issueDate;
       private final String series;

        public Date getIssueDate() {
            return issueDate;
        }

        public String getSeries() {
            return series;
        }

        public String getNumber() {
            return number;
        }

        private final String number;

        public Passport(Date issueDate, String series, String number) {
            this.issueDate = issueDate;
            this.series = series;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Passport otherPassport = (Passport) o;
            return this.series.equals(otherPassport.getSeries())
                    && this.number.equals(otherPassport.getNumber())
                    && this.issueDate.equals(otherPassport.getIssueDate());
        }

        @Override
        public int hashCode() {
            return Objects.hash(issueDate, series, number);
        }
    }
}
