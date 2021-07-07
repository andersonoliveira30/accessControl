package com.domain.accesscontrol.repository;

import com.domain.accesscontrol.entity.AccessLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {
    void deleteAllById(Long id);
}
