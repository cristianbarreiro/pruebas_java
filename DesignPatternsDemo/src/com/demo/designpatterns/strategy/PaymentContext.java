package com.demo.designpatterns.strategy;

class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy paymentStrategy) {
        this.strategy = paymentStrategy;
    }

    public void executePayment() {
        strategy.pay();
    }
}
