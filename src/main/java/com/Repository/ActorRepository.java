package com.Repository;

import com.Entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    Actor findByName(String name);

    List<Actor> findAllByNameIn(List<String> names);
    
    
}