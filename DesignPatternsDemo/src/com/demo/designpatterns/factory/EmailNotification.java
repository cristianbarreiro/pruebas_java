package com.demo.designpatterns.factory;

class EmailNotification implements Notification {
    public void send() {
        System.out.println("Email sent");
    }
}
