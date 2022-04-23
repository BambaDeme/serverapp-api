package com.learning.serverapp.repo;

import com.learning.serverapp.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server,Long> {
    String findByIpAddress(String ipAddress);
}
