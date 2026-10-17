package com.demo.designpatterns.strategy;

class StrategyDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CardPayment());
        context.executePayment(100.0);

        context.setStrategy(new UPIPayment());
        context.executePayment(250.5);

        context.setStrategy(new PaypalPayment());
        context.executePayment(99.99);
    }
}
