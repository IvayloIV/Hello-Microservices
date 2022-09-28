package bg.ivaylocode.notification.repositories;

import bg.ivaylocode.notification.entities.Notification;

public interface NotificationRepository {

    boolean save(Notification notification);
}
