package com.example.recime_api.domain;

import com.example.recime_api.domain.model.Difficulty;
import com.example.recime_api.domain.model.Recipe;
import com.example.recime_api.domain.service.QueryHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class QueryHandlerTest {

    @Autowired
    private QueryHandler queryHandler;

    @Test
    void givenQuery_whenGetTrendingRecipes_thenReturnAllRecipesAndAssertFieldsAreCorrect() {
        List<Recipe> recipes = queryHandler.getTrendingRecipes();

        assertNotNull(recipes, "The recipes list should not be null");
        assertEquals(3, recipes.size(), "Should return 3 recipes from data.sql");

        List<Recipe> expectedRecipes = List.of(
                new Recipe("Pancakes", "http://example.com/", Difficulty.EASY),
                new Recipe("Spaghetti", "http://example.com/", Difficulty.HARD),
                new Recipe("Bacon And Eggs", "http://example.com/", Difficulty.MEDIUM)
        );

        for (Recipe expected : expectedRecipes) {
            boolean found = recipes.stream().anyMatch(actual ->
                    expected.getTitle().equals(actual.getTitle()) &&
                            expected.getImageUrl().equals(actual.getImageUrl()) &&
                            expected.getDifficulty().equals(actual.getDifficulty())
            );
            assertTrue(found, "Expected recipe " + expected + " not found.");
        }
    }

    @Test
    void givenQuery_whenGetTrendingRecipesByDifficulty_thenReturnOnlyMatchingRecipes() {
        List<Recipe> easyRecipes = queryHandler.getTrendingRecipesByDifficulty(Difficulty.EASY);

        assertNotNull(easyRecipes, "The easy recipes list should not be null");
        assertEquals(1, easyRecipes.size(), "There should be exactly one easy recipe");

        Recipe expected = new Recipe("Pancakes", "http://example.com/", Difficulty.EASY);
        Recipe actual = easyRecipes.getFirst();

        assertEquals(expected.getTitle(), actual.getTitle(), "Title should be 'Pancakes'");
        assertEquals(expected.getImageUrl(), actual.getImageUrl(), "Image URL should be 'http://example.com/'");
        assertEquals(expected.getDifficulty(), actual.getDifficulty(), "Difficulty should be EASY");
    }
}