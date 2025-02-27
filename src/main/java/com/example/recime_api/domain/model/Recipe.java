package com.example.recime_api.domain.model;

public class Recipe {
    private Long id;
    private String title;
    private String imageUrl;
    private Difficulty difficulty;

    public Recipe(Long id, String title, String imageUrl, Difficulty difficulty) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
    }

    public Recipe(String title, String imageUrl, Difficulty difficulty) {
        this(null, title, imageUrl, difficulty);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }

    // equals and hashCode based on business keys or id
}
