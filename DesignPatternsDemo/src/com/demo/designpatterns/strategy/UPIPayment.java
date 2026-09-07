package com.demo.designpatterns.strategy;

class UPIPayment implements PaymentStrategy {
    public void pay() {
        System.out.println("Paid using UPI");
    }
}
