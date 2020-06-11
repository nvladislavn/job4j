package ru.job4j.foodStores.food;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Food
 *
 * @author Vladislav Nechaev
 * @since 04.06.2020
 */
public abstract class Food {

    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private int price;
    private int discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, int price, int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", expireDate=" + expireDate +
                ", createDate=" + createDate +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return price == food.price
                && discount == food.discount
                && name.equals(food.name)
                && expireDate.equals(food.expireDate)
                && createDate.equals(food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate, price, discount);
    }
}
