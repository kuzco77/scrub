package com.rochefort.scrub.repository;

import com.rochefort.scrub.entity.CustomLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedQuery;
import java.math.BigInteger;

public interface CustomLogRepository extends JpaRepository<CustomLogEntity, Long> {

}