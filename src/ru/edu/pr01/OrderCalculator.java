package ru.edu.pr01;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
public class OrderCalculator {
    private static final double VAT_RATE = 20;
    private static final int QUANTITY_MAX = 10000;
    private static final double UNIT_PRICE_MAX = 5000000;
    private static final double DISCOUNT_PERCENT_MAX = 45;

    public static boolean isValid(int quantity, double unitPrice, double discountPercent) {
        return (quantity>0 && quantity<=QUANTITY_MAX 
            && unitPrice>0 && unitPrice<=UNIT_PRICE_MAX 
            && discountPercent>=0 && discountPercent<=DISCOUNT_PERCENT_MAX);
    }
    public static double calculateBase(int quantity, double unitPrice) {
        return quantity*unitPrice;
    }
    public static double applyDiscount(double base, double discountPercent) {
        return base * (100-discountPercent)/100;
    }
    public static double calculateVat(double discounted, double vatPercent) {
        return discounted * vatPercent / 100;
    }
    public static double calculateTotal(int quantity, double unitPrice, double discountPercent, double vatPercent) {
        double base = calculateBase(quantity, unitPrice);
        double discounted = applyDiscount(base, discountPercent);
        double vat = calculateVat(discounted, vatPercent);
        return vat+discounted;
    }

    public static void main(String[] args){
        int quantity;
        double unitPrice;
        double discountPercent;
        try{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество: ");
        quantity = scanner.nextInt();
        System.out.print("Введите цену: ");
        unitPrice = scanner.nextDouble();
        System.out.print("Введите скидку: ");
        discountPercent = scanner.nextDouble();
        }
        catch (InputMismatchException e){
            System.out.print("Введенные данные некорректны! Указаны неверные типы данных.");
            return;
        }
        catch (NoSuchElementException e){
            System.out.print("Введенные данные некорректны! Введено пустое значение или оно отсутствует.");
            return;
        }
        if (!isValid(quantity, unitPrice, discountPercent)){
            System.out.println("Введенные данные некорректны! Указанные данные выходят за границы.");
            return;
        }

        double base = calculateBase(quantity, unitPrice);
        double discounted = applyDiscount(base, discountPercent);
        double vat = calculateVat(discounted, VAT_RATE);
        double total = vat+discounted;
        
        System.out.printf("Базовая стоимость: %.2f руб.%nСтоимость со скидкой: %.2f руб.%nРазмер НДС: %.2f руб.%nИтого: %.2f руб.%n", base, discounted, vat, total);
    }
}

