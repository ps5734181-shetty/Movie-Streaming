package com.Service;

import com.Entity.Studio;
import com.Repository.StudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioService {

    private final StudioRepository repo;

    public StudioService(StudioRepository repo) {
        this.repo = repo;
    }

    public Studio save(Studio s) {
        return repo.save(s);
    }

    public List<Studio> findAll() {
        return repo.findAll();
    }

    public Studio findByName(String name) {
        return repo.findByName(name);
    }

    public Studio update(Studio s) {
        Studio e = findByName(s.getName());
        if (e != null) {
            e.setLocation(s.getLocation());
            return repo.save(e);
        }
        return null;
    }

    public void delete(String name) {
        Studio e = findByName(name);
        if (e != null) repo.delete(e);
    }
}