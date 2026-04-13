package com.Service;

import com.Entity.Actor;
import com.Repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository repo;

    public ActorService(ActorRepository repo) {
        this.repo = repo;
    }

    public Actor save(Actor a) {
        return repo.save(a);
    }

    public List<Actor> findAll() {
        return repo.findAll();
    }

    public Actor findByName(String name) {
        return repo.findByName(name);
    }

    public Actor update(Actor a) {
        Actor e = findByName(a.getName());
        if (e != null) {
            e.setAge(a.getAge());
            return repo.save(e);
        }
        return null;
    }

    public void delete(String name) {
        Actor e = findByName(name);
        if (e != null) repo.delete(e);
    }
}