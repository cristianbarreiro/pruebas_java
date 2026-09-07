package com.demo.designpatterns.strategy;

class StrategyDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CardPayment());
        context.executePayment();

        context.setStrategy(new UPIPayment());
        context.executePayment();
    }
}
