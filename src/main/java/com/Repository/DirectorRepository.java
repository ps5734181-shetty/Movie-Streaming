package com.Repository;

import com.Entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    Director findByName(String name);
}