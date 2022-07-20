package com.jb.foodOrderPractice.repositories;

import com.jb.foodOrderPractice.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
    boolean existsByEmail(String email);
    Optional<Company> findByEmailAndPassword(String email, String password);
}
