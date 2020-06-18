package com.app.group15.NotificationManagement;

public interface INotifier {
    boolean sendMessage(String email, String subject, String message);

    boolean setCredentials();
}
