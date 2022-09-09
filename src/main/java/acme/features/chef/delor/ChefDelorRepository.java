package acme.features.chef.delor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cookingItem.CookingItem;
import acme.entities.delor.Delor;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;


@Repository
public interface ChefDelorRepository extends AbstractRepository {
	
	@Query("select t from Delor t where t.cookingItem.id = :id")
	Collection<Delor> findOwnPimpams(int id);
	
	@Query("select q from Delor q where q.id = :id")
	Delor findOnePimpamById(int id);
	
	@Query("select distinct(q) from Delor q where q.cookingItem.id = :masterId")
	Collection<Delor> findPimpamByCookingItemId(int masterId);
	
	@Query("select s.acceptedCurr from Configuration s")
	String findAvailableCurrencies();
	
	@Query("select p from Delor p WHERE p.keylet = :keylet")
	Delor findPimpamByCode(String keylet);
	
	@Query("select i from CookingItem i where i.id = :id")
	CookingItem findOneCookingItemById(int id);
	
	@Query("select i from Chef i where i.userAccount.id = :id")
	Chef findChefByUserId(int id);
	
	@Query("select t from CookingItem t where t.id = :id")
	CookingItem findCookingItemById(int id);
	
	
	@Query("select i from Chef i where i.userAccount.id = :id")
	Chef findChefByUserAccountId(int id);
	
	@Query("select t from Delor t where t.id = :id")
	Delor findPimpamById(int id);
	

	



}
