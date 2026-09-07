package com.demo.designpatterns.strategy;

class CardPayment implements PaymentStrategy {
    public void pay() {
        System.out.println("Paid using card");
    }
}
