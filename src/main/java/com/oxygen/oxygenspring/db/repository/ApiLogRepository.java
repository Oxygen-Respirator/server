package com.oxygen.oxygenspring.db.repository;

import com.oxygen.oxygenspring.db.entity.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}