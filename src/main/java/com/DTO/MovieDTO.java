package com.DTO;

import java.util.List;

public class MovieDTO {

    private Long id;
    private String title;
    private List<String> genres;
    private int releaseYear;
    private double rating;

    private Long directorId;
    private Long studioId;
    private List<Long> actorIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}
	public Long getStudioId() {
		return studioId;
	}
	public void setStudioId(Long studioId) {
		this.studioId = studioId;
	}
	public List<Long> getActorIds() {
		return actorIds;
	}
	public void setActorIds(List<Long> actorIds) {
		this.actorIds = actorIds;
	}

    // getters and setters
}