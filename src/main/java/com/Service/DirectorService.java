package com.Service;

import com.Entity.Director;
import com.Repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final DirectorRepository repo;

    public DirectorService(DirectorRepository repo) {
        this.repo = repo;
    }

    public Director save(Director d) {
        return repo.save(d);
    }

    public List<Director> findAll() {
        return repo.findAll();
    }

    public Director findByName(String name) {
        return repo.findByName(name);
    }

    public Director update(Director d) {
        Director e = findByName(d.getName());
        if (e != null) {
            return repo.save(e);
        }
        return null;
    }

    public void delete(String name) {
        Director e = findByName(name);
        if (e != null) repo.delete(e);
    }
}