package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cookingItem.CookingItem;
import acme.entities.pimpam.Pimpam;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;


@Repository
public interface ChefPimpamRepository extends AbstractRepository {
	
	@Query("select t from Pimpam t where t.cookingItem.id = :id")
	Collection<Pimpam> findOwnPimpams(int id);
	
	@Query("select q from Pimpam q where q.id = :id")
	Pimpam findOnePimpamById(int id);
	
	@Query("select distinct(q) from Pimpam q where q.cookingItem.id = :masterId")
	Collection<Pimpam> findPimpamByCookingItemId(int masterId);
	
	@Query("select s.acceptedCurr from Configuration s")
	String findAvailableCurrencies();
	
	@Query("select p from Pimpam p WHERE p.code = :code")
	Pimpam findPimpamByCode(String code);
	
	@Query("select i from CookingItem i where i.id = :id")
	CookingItem findOneCookingItemById(int id);
	
	@Query("select i from Chef i where i.userAccount.id = :id")
	Chef findChefByUserId(int id);
	
	@Query("select t from CookingItem t where t.id = :id")
	CookingItem findCookingItemById(int id);
	
	
	@Query("select i from Chef i where i.userAccount.id = :id")
	Chef findChefByUserAccountId(int id);
	
	@Query("select t from Pimpam t where t.id = :id")
	Pimpam findPimpamById(int id);
	

	



}
