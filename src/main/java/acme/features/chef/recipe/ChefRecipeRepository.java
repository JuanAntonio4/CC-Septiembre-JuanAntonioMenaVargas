package acme.features.chef.recipe;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cookingItem.CookingItem;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefRecipeRepository extends AbstractRepository{
	
	@Query("select t from Recipe t where t.chef.id = :id")
	Collection<Recipe> findOwnRecipes(int id);
	
	@Query("select t from Recipe t where t.id = :id")
	Recipe findRecipeById(int id);
	
	@Query("select i from Chef i where i.userAccount.id = :id")
	Chef findChefByUserAccountId(int id);
	
	@Query("select i from CookingItem i")
	List<CookingItem> findManyCookingItem();
	
	@Query("select t from Recipe t WHERE t.code = :code")
	Recipe findRecipeByCode(String code);

}
