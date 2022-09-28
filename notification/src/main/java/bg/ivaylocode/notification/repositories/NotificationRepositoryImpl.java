package bg.ivaylocode.notification.repositories;

import bg.ivaylocode.notification.entities.Notification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private List<Notification> notifications = new ArrayList<>();

    @Override
    public boolean save(Notification notification) {
        notifications.add(notification);
        return true;
    }
}
