package com.example.recime_api.api.dto;


import com.example.recime_api.domain.model.Difficulty;

public class RecipeDto {
    private Long id;
    private String title;
    private String imageUrl;
    private Difficulty difficulty;

    public RecipeDto() {}

    public RecipeDto(Long id, String title, String imageUrl, Difficulty difficulty) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}

