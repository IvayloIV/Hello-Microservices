package bg.ivaylocode.notification.services;

import bg.ivaylocode.clients.notification.NotificationRequest;

public interface NotificationService {

    public void send(NotificationRequest notificationRequest);
}
