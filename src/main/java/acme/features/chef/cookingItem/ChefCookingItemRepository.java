package acme.features.chef.cookingItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cookingItem.CookingItem;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefCookingItemRepository extends AbstractRepository{
	
	@Query("select i from CookingItem i where i.type = acme.entities.cookingItem.CookingItemType.INGREDIENT and i.chef.id = :id")
	Collection<CookingItem> findIngredientsByChefId(int id);
	
	@Query("select i from CookingItem i where i.type = acme.entities.cookingItem.CookingItemType.KITCHEN_UTENSIL and i.chef.id = :id")
	Collection<CookingItem> findKitchenUtensilsByChefId(int id);
	
	@Query("select i from CookingItem i where i.id = :id")
	CookingItem findCookingItemById(int id);
	
	@Query(value="select i from Chef i where i.userAccount.id = :id")
	Chef findChefByUserAccountId(int id);
	
	@Query("select i from CookingItem i WHERE i.code = :code")
	CookingItem findCookingItemByCode(String code);
	
	@Query("select i from CookingItem i where i.id = :id")
	CookingItem findOneCookingItemById(int id);
	
	@Query("select s.acceptedCurr from Configuration s")
	String findAvailableCurrencies();

}
