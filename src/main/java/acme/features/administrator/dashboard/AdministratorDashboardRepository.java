package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cookingItem.CookingItemType;
import acme.enums.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(cookingitem) FROM CookingItem cookingitem WHERE cookingitem.type = :type")
	Integer getCookingItemTotalsByType(CookingItemType type);
	
	@Query("SELECT COUNT(finedish) FROM FineDish finedish WHERE finedish.status = :status")
	Integer getFineDishTotalsByStatus(Status status);
	
	@Query("SELECT MIN(finedish.budget.amount), MAX(finedish.budget.amount), AVG(finedish.budget.amount), STDDEV(finedish.budget.amount) from FineDish finedish WHERE finedish.status = :status")
	String getFineDishBudgetByStatus(Status status);
	
	@Query("SELECT cookingitem.retailPrice.currency, MIN(cookingitem.retailPrice.amount), MAX(cookingitem.retailPrice.amount), AVG(cookingitem.retailPrice.amount), STDDEV(cookingitem.retailPrice.amount) from CookingItem cookingitem WHERE cookingitem.type = :type GROUP BY cookingitem.retailPrice.currency")
	List<String> getCookingItemsByType(CookingItemType type);
	
	@Query("SELECT cookingitem.retailPrice.currency, cookingitem.description, MIN(cookingitem.retailPrice.amount), MAX(cookingitem.retailPrice.amount), AVG(cookingitem.retailPrice.amount), STDDEV(cookingitem.retailPrice.amount) from CookingItem cookingitem WHERE cookingitem.type = acme.entities.cookingItem.CookingItemType.INGREDIENT GROUP BY cookingitem.retailPrice.currency, cookingitem.description")
	List<String> getingredientsInCurrencies();
	
	
	
	
	
	@Query("SELECT p.income.currency, MIN(p.income.amount), MAX(p.income.amount), AVG(p.income.amount), STDDEV(p.income.amount) from Delor p  GROUP BY p.income.currency")
	List<String> getPimpamBudget();
	
	@Query("SELECT COUNT(c) FROM Delor c")
    Integer getAllPimpam();

  
    @Query("SELECT COUNT(i) FROM CookingItem i where i.type = :type")
    Integer getAllArtefacts(CookingItemType type);
	
}
