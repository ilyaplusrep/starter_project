package ru.edu.pr01;

import java.util.jar.Attributes.Name;

public class SelfCheck {
    public static void main(String[] args) {

        // количество: (0; 10000]
        check("количество 0 запрещено",      OrderCalculator.isValid(0, 100.0, 10.0) == false);
        check("количество 1 разрешено",      OrderCalculator.isValid(1, 100.0, 10.0) == true);
        check("количество 10000 разрешено",  OrderCalculator.isValid(10000, 100.0, 10.0) == true);
        check("количество 10001 запрещено",  OrderCalculator.isValid(10001, 100.0, 10.0) == false);

        // цена: (0; 5 000 000]
        check("цена 0 запрещена",            OrderCalculator.isValid(1, 0.0, 10.0) == false);
        check("цена 5000000 разрешена",      OrderCalculator.isValid(1, 5000000.0, 10.0) == true);
        check("цена 5000000.01 запрещена",   OrderCalculator.isValid(1, 5000000.01, 10.0) == false);

        // скидка: [0; 45] (вариант 8)
        check("скидка 0 разрешена",          OrderCalculator.isValid(1, 100.0, 0.0) == true);
        check("скидка 45 разрешена",         OrderCalculator.isValid(1, 100.0, 45.0) == true);
        check("скидка 46 запрещена",         OrderCalculator.isValid(1, 100.0, 46.0) == false);
        check("скидка -1 запрещена",         OrderCalculator.isValid(1, 100.0, -1.0) == false);


        boolean ok = false;
        try { ok = (OrderCalculator.calculateBase(2, 100.0) == 200.0 && Math.abs(OrderCalculator.calculateTotal(2,100.0,10.0,20.0)-216.0)<0.001); } catch (Exception e) { System.out.println("FAIL exception " + e.getMessage()); }
        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
    private static void check(String name, boolean passed) {
        if (passed) {
            System.out.println("PASS: " + name);
        } else {
            System.out.println("FAIL: " + name);
        }
    }
    
}
