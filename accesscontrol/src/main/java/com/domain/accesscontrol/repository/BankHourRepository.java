package com.domain.accesscontrol.repository;

import com.domain.accesscontrol.entity.BankHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankHourRepository extends JpaRepository<BankHour, Long> {
}
