package com.demo.designpatterns.strategy;

class CardPayment implements PaymentStrategy {
    public void pay(double monto) {
        System.out.println("Paid using card: " + monto);
    }
}
