package com.Controller;

import com.Entity.Studio;
import com.Service.StudioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studios")
public class StudioController {

    private final StudioService service;

    public StudioController(StudioService service) {
        this.service = service;
    }

    @PostMapping
    public Studio save(@RequestBody Studio s) {
        return service.save(s);
    }

    @GetMapping
    public List<Studio> findAll() {
        return service.findAll();
    }

    @GetMapping("/{name}")
    public Studio findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PutMapping
    public Studio update(@RequestBody Studio s) {
        return service.update(s);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        service.delete(name);
    }
}