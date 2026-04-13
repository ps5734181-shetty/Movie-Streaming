package com.Controller;

import com.Entity.Movie;
import com.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    // ================= CREATE =================

    @PostMapping
    public Movie save(@RequestBody Movie m) {
        return service.save(m);
    }

    // ================= GET ALL + SORT (MERGED 🔥) =================

    @GetMapping
    public List<Movie> getMovies(
            @RequestParam(required = false) String fields,
            @RequestParam(required = false) String directions) {

        if (fields == null && directions == null) {
            return service.findAll(); // normal
        }

        return service.findAllSorted(fields, directions); // sorted
    }

    // ================= GET BY TITLE =================

    @GetMapping("/{title}")
    public Movie findByTitle(@PathVariable String title) {
        return service.findByTitle(title);
    }

    // ================= FILTER =================

    @GetMapping("/actor/{name}")
    public List<Movie> findByActor(@PathVariable String name) {
        return service.findByActor(name);
    }

    @GetMapping("/director/{name}")
    public List<Movie> findByDirector(@PathVariable String name) {
        
    	return service.findByDirector(name);
    }

    @GetMapping("/studio/{name}")
    public List<Movie> findByStudio(@PathVariable String name) {
        return service.findByStudio(name);
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> findByGenre(@PathVariable String genre) {
        return service.findByGenre(genre);
    }

    // ================= UPDATE =================

    @PutMapping
    public Movie update(@RequestBody Movie m) {
        return service.update(m);
    }

    // ================= DELETE =================

    @DeleteMapping("/{title}")
    public void delete(@PathVariable String title) {
        service.delete(title);
    }

    // ================= SEARCH =================

    @PostMapping("/search")
    public List<Movie> search(@RequestBody Movie m) {
        return service.search(m);
    }
}