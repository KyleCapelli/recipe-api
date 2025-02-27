package com.example.recime_api.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "trending_recipe")
public class TrendingRecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Column(nullable = false)
    private int position;

    protected TrendingRecipeEntity() {
    }

    public TrendingRecipeEntity(Long recipeId, int position) {
        this.recipeId = recipeId;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "TrendingRecipeEntity{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", position=" + position +
                '}';
    }
}
