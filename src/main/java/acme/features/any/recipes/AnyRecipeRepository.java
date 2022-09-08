package acme.features.any.recipes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository {
	
	@Query("select t from Recipe t where t.status = 'PUBLISHED'")
	Collection<Recipe> findRecipes();
	
	@Query("select t from Recipe t where t.id = :id")
	Recipe findRecipeById(int id);
	

}
