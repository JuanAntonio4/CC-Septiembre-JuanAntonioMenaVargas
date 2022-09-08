package acme.features.epicure.memorandum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureMemorandumRepository extends AbstractRepository {
	
	
	@Query("select p from Memorandum p where p.id = :id")
	Memorandum findMemorandumById(int id);
	
	@Query("select p from Memorandum p where p.fineDish.id = :fineDishId")
	Collection<Memorandum> findMemorandumByFineDishId(int fineDishId);
	
	@Query("select p from Memorandum p where p.fineDish.epicure.id = :epicureId")
	Collection<Memorandum> findMemorandumByEpicureId(int epicureId);
	
	@Query("select p from FineDish p where p.id = :id")
	FineDish findFineDishById(int id);
	
	@Query("select p from Memorandum p")
	List<Memorandum> findMemorandum();
	


}