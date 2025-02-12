package com.CRMSystem.CRMSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ApplicationRepository extends JpaRepository<ApplicationRequest, Long> {

    @Query("SELECT a FROM ApplicationRequest a LEFT JOIN FETCH a.course")
    List<ApplicationRequest> findAllWithCourses();

}
