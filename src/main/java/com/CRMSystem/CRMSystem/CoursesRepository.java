package com.CRMSystem.CRMSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Repository
@Transactional
public interface CoursesRepository extends JpaRepository<Courses, Long> {
}
