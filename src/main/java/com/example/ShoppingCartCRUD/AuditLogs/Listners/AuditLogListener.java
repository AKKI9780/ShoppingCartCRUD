package com.example.ShoppingCartCRUD.AuditLogs.Listners;

import com.example.ShoppingCartCRUD.AuditLogs.Entity.AuditLog;
import com.example.ShoppingCartCRUD.AuditLogs.Repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuditLogListener {
    private final AuditLogRepository auditLogRepository;

    @RabbitListener(queues = "cart_queue")
    public void consumeMessage(String message) {
        AuditLog log = new AuditLog();
        log.setEntityName("ShoppingCart");
        log.setOperationType(message.split(":")[0]);
        log.setDetails(message);
        log.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(log);
    }
}
