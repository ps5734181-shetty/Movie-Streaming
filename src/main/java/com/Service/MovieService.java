package com.Service;

import com.Entity.*;
import com.Repository.*;
import com.Exception.ResourceNotFoundException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepo;
    private final DirectorRepository directorRepo;
    private final StudioRepository studioRepo;
    private final ActorRepository actorRepo;

    public MovieService(MovieRepository movieRepo,
                        DirectorRepository directorRepo,
                        StudioRepository studioRepo,
                        ActorRepository actorRepo) {
        this.movieRepo = movieRepo;
        this.directorRepo = directorRepo;
        this.studioRepo = studioRepo;
        this.actorRepo = actorRepo;
    }

    // ================= SORT (UTILITY) =================

    public Sort buildSort(String fields, String directions) {

        if (fields == null || fields.isBlank()) {
            fields = "title";
        }

        if (directions == null || directions.isBlank()) {
            directions = "asc";
        }

        String[] fieldArray = fields.split(",");
        String[] directionArray = directions.split(",");

        Sort sort = Sort.unsorted();

        for (int i = 0; i < fieldArray.length; i++) {

            String field = fieldArray[i].trim();
            String dir = (i < directionArray.length) ? directionArray[i].trim() : "asc";

            Sort temp = dir.equalsIgnoreCase("desc") ?
                    Sort.by(field).descending() :
                    Sort.by(field).ascending();

            sort = sort.and(temp);
        }

        return sort;
    }

    // ================= MAIN ENTRY =================

    public List<Movie> findAllSorted(String fields, String directions) {
        Sort sort = buildSort(fields, directions);
        return movieRepo.findAll(sort);
    }

    // ================= CRUD =================

    public Movie save(Movie m) {

        Director director = directorRepo.findByName(m.getDirector().getName());
        Studio studio = studioRepo.findByName(m.getStudio().getName());

        if (director == null) {
            throw new ResourceNotFoundException("Director not found");
        }

        if (studio == null) {
            throw new ResourceNotFoundException("Studio not found");
        }

        List<String> names = m.getActors().stream().map(Actor::getName).toList();
        List<Actor> actors = actorRepo.findAllByNameIn(names);

        if (actors.isEmpty()) {
            throw new ResourceNotFoundException("Actors not found");
        }

        m.setDirector(director);
        m.setStudio(studio);
        m.setActors(actors);

        return movieRepo.save(m);
    }

    public List<Movie> findAll() {
        return movieRepo.findAll();
    }

    public Movie findByTitle(String title) {
        List<Movie> list = movieRepo.findByTitle(title);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Movie not found: " + title);
        }

        return list.get(0);
    }

    public Movie update(Movie m) {

        Movie existing = findByTitle(m.getTitle());

        existing.setGenres(m.getGenres());
        existing.setReleaseYear(m.getReleaseYear());
        existing.setRating(m.getRating());

        Director director = directorRepo.findByName(m.getDirector().getName());
        Studio studio = studioRepo.findByName(m.getStudio().getName());

        if (director == null) {
            throw new ResourceNotFoundException("Director not found");
        }

        if (studio == null) {
            throw new ResourceNotFoundException("Studio not found");
        }

        List<String> names = m.getActors().stream().map(Actor::getName).toList();
        List<Actor> actors = actorRepo.findAllByNameIn(names);

        if (actors.isEmpty()) {
            throw new ResourceNotFoundException("Actors not found");
        }

        existing.setDirector(director);
        existing.setStudio(studio);
        existing.setActors(actors);

        return movieRepo.save(existing);
    }

    public void delete(String title) {
        Movie m = findByTitle(title);
        movieRepo.delete(m);
    }

    // ================= FILTER =================

    public List<Movie> findByDirector(String name) {
        List<Movie> list = movieRepo.findByDirectorName(name);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No movies found for director: " + name);
        }

        return list;
    }

    public List<Movie> findByActor(String name) {
        List<Movie> list = movieRepo.findByActorsName(name);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No movies found for actor: " + name);
        }

        return list;
    }

    public List<Movie> findByGenre(String genre) {
        List<Movie> list = movieRepo.findByGenresContaining(genre);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No movies found for genre: " + genre);
        }

        return list;
    }

    public List<Movie> findByStudio(String name) {
        List<Movie> list = movieRepo.findByStudioName(name);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No movies found for studio: " + name);
        }

        return list;
    }

    // ================= SEARCH =================

    public List<Movie> search(Movie input) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Movie> example = Example.of(input, matcher);

        List<Movie> list = movieRepo.findAll(example);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No matching movies found");
        }

        return list;
    }
}