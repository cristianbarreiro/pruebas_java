package com.demo.designpatterns.strategy;

class PaypalPayment implements PaymentStrategy {
    public void pay(double monto) {
        System.out.println("Paid using PayPal: " + monto);
    }
}
