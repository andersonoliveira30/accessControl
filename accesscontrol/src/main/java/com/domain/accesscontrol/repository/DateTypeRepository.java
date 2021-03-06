package com.domain.accesscontrol.repository;

import com.domain.accesscontrol.entity.DateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTypeRepository extends JpaRepository<DateType, Long> {
}
