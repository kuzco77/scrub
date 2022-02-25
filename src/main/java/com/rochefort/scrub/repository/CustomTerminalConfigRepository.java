package com.rochefort.scrub.repository;

import com.rochefort.scrub.entity.CustomTerminalConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomTerminalConfigRepository extends JpaRepository<CustomTerminalConfigEntity, Long> {
    Optional<CustomTerminalConfigEntity> findFirstByCode(String code);
}