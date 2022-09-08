package acme.features.any.cookingItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cookingItem.CookingItem;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyCookingItemRepository extends AbstractRepository {
	
	@Query( "select t from CookingItem t where t.type = acme.entities.cookingItem.CookingItemType.INGREDIENT and t.status = acme.enums.PublishedStatus.PUBLISHED" )
	Collection<CookingItem> findPublishedIngredients();
	
	@Query( "select t from CookingItem t where t.type = acme.entities.cookingItem.CookingItemType.KITCHEN_UTENSIL and t.status = acme.enums.PublishedStatus.PUBLISHED" )
	Collection<CookingItem> findPublishedKitchenUtensils();

	@Query("select t from CookingItem t where t.id= :id")
	CookingItem findCookingItemById(int id);
	
	@Query("select q.cookingitem from Quantity q where q.recipe.id= :id")
	Collection<CookingItem> findCookingItemByRecipeId(int id);
}
