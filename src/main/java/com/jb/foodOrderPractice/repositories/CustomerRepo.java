package com.jb.foodOrderPractice.repositories;

import com.jb.foodOrderPractice.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);
    Optional<Customer> findByEmailAndPassword(String email, String password);

}
