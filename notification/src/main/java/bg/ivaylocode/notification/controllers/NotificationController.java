package bg.ivaylocode.notification.controllers;

import bg.ivaylocode.clients.notification.NotificationRequest;
import bg.ivaylocode.notification.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public void send(@RequestBody NotificationRequest notificationRequest) {
        notificationService.send(notificationRequest);
    }
}
