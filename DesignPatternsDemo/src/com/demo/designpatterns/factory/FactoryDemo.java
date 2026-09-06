package com.demo.designpatterns.factory;

public class FactoryDemo {
    public static void main(String[] args) {
        Notification notification =
                NotificationFactory.createNotification("EMAIL");

        notification.send();
    }
}
