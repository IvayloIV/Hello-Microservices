package bg.ivaylocode.notification.services;

import bg.ivaylocode.clients.notification.NotificationRequest;
import bg.ivaylocode.notification.entities.Notification;
import bg.ivaylocode.notification.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger logger;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public void send(NotificationRequest notificationRequest) {
        long id = ThreadLocalRandom.current().nextLong(1,Long.MAX_VALUE);

        Notification notification = Notification.builder()
            .id(id)
            .receiver(notificationRequest.getReceiver())
            .message(notificationRequest.getMessage())
            .date(LocalDateTime.now())
            .build();

        //TODO: Send notification
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        notificationRepository.save(notification);
        logger.info("Notification - {}.", notification);
    }
}
