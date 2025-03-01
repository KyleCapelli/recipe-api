package com.example.recime_api.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenRequestForTrendingRecipes_thenReturnAllRecipesInOrder() throws Exception {
        String expectedJson = """
        [
          {
            "id": 1,
            "title": "Pancakes",
            "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
            "difficulty": "EASY"
          },
          {
            "id": 3,
            "title": "Bacon And Eggs",
            "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
            "difficulty": "MEDIUM"
          },
          {
            "id": 2,
            "title": "Spaghetti",
            "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
            "difficulty": "HARD"
          }
        ]
        """;

        mockMvc.perform(get("/api/v1/recipes/trending")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void givenRequestForTrendingRecipesByDifficulty_thenReturnOnlyEasyRecipe() throws Exception {
        String expectedJson = """
        [
          {
            "id": 1,
            "title": "Pancakes",
            "imageUrl": "https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg",
            "difficulty": "EASY"
          }
        ]
        """;

        mockMvc.perform(get("/api/v1/recipes/trending")
                        .param("difficulty", "easy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void givenEmptyDifficulty_whenRequestTrendingRecipes_thenReturnBadRequest(String difficultyParam) throws Exception {
        mockMvc.perform(get("/api/v1/recipes/trending")
                        .param("difficulty", difficultyParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A difficulty is required for filtering trending recipes"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"FOO", "invalid", "123", "EasYy"})
    void givenInvalidDifficulty_whenRequestTrendingRecipes_thenReturnBadRequest(String invalidDifficulty) throws Exception {
        mockMvc.perform(get("/api/v1/recipes/trending")
                        .param("difficulty", invalidDifficulty)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid difficulty value provided"));
    }
}
