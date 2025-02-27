package com.example.recime_api.data.entity;

import com.example.recime_api.domain.model.Difficulty;
import jakarta.persistence.*;

@Entity
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    protected RecipeEntity() {}

    public RecipeEntity(String title, String imageUrl, Difficulty difficulty) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
    }

    public RecipeEntity(Long id, String title, String imageUrl, Difficulty difficulty) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
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
}
