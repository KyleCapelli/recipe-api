package com.example.recime_api.data.entity;

import com.example.recime_api.domain.model.Difficulty;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Entity
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(nullable = false)
    private Difficulty difficulty;

    protected RecipeEntity() {}

    public RecipeEntity(String title, String imageUrl, Difficulty difficulty) {
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
