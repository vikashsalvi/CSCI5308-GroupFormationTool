package com.app.group15.notifications;

public interface Notifier {
    boolean sendMessage(String email, String subject, String message);
    boolean setCredentials();
}
