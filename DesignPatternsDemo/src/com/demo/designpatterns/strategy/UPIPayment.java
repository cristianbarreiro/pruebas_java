package com.demo.designpatterns.strategy;

class UPIPayment implements PaymentStrategy {
    public void pay(double monto) {
        System.out.println("Paid using UPI: " + monto);
    }
}
