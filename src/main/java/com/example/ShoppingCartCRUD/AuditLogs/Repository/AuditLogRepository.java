package com.example.ShoppingCartCRUD.AuditLogs.Repository;



import com.example.ShoppingCartCRUD.AuditLogs.Entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
