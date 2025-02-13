package com.CRMSystem.CRMSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OperatorRepository  extends JpaRepository<Operator, Long> {
}
