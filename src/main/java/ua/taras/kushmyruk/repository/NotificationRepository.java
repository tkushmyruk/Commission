package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taras.kushmyruk.model.Notification;

public interface NotificationRepository  extends JpaRepository<Notification, Long> {
}
