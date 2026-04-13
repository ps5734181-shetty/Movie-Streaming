package com.Repository;

import com.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

    List<Movie> findByActorsName(String name);

    List<Movie> findByDirectorName(String name);

    List<Movie> findByStudioName(String name);

    List<Movie> findByGenresContaining(String genre);

    List<Movie> findAllByOrderByReleaseYearAsc();

    List<Movie> findAllByOrderByReleaseYearDesc();
}