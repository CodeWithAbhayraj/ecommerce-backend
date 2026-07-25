package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    boolean existsByEmail(String email);
}

