package acme.features.epicure.fineDish;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.memorandum.Memorandum;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;


@Repository
public interface EpicureFineDishRepository extends AbstractRepository {

	
	@Query("select p from FineDish p where p.id = :id")
	FineDish findFineDishById(int id);

	
	@Query("select p from FineDish p where p.epicure.id = :epicureId")
	Collection<FineDish> findAllFineDishByEpicureId(int epicureId);
	
	@Query("select p from Memorandum p where p.fineDish.id = :id ")
	List<Memorandum> findMemorandumByfinedishId(int id);
	
	@Query("select e from Epicure e WHERE e.id=:id")
    Epicure findEpicureById(int id);
	
	@Query("select c from Chef c WHERE c.userAccount.username=:un")
    Chef findChefByUsername(String un);
	
	@Query("select i from Chef i")
    Collection<Chef> findChefs();
	
	@Query("select p from FineDish p WHERE p.code = :code")
	FineDish findFineDishByCode(String code);
	
	@Query("select s.acceptedCurr from Configuration s")
	String findAvailableCurrencies();
	
}
