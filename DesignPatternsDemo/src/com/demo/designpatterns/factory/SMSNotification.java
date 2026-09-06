package com.demo.designpatterns.factory;

class SMSNotification implements Notification {
    public void send() {
        System.out.println("SMS sent");
    }
}
