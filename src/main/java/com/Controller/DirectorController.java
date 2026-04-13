package com.Controller;

import com.Entity.Director;
import com.Service.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService service;

    public DirectorController(DirectorService service) {
        this.service = service;
    }

    @PostMapping
    public Director save(@RequestBody Director d) {
        return service.save(d);
    }

    @GetMapping
    public List<Director> findAll() {
        return service.findAll();
    }

  
    @GetMapping("/{name}")
    public Director findByName(@PathVariable String name) {
        return service.findByName(name);
    }

  
    @PutMapping
    public Director update(@RequestBody Director d) {
        return service.update(d);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        service.delete(name);
    }
}