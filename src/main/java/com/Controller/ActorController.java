package com.Controller;

import com.Entity.Actor;
import com.Service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    @PostMapping
    public Actor save(@RequestBody Actor a) {
        return service.save(a);
    }

    @GetMapping
    public List<Actor> findAll() {
        return service.findAll();
    }

  
    @GetMapping("/{name}")
    public Actor findByName(@PathVariable String name) {
        return service.findByName(name);
    }

   
    @PutMapping
    public Actor update(@RequestBody Actor a) {
        return service.update(a);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        service.delete(name);
    }
}