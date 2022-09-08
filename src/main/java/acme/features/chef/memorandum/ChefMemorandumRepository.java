
package acme.features.chef.memorandum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefMemorandumRepository extends AbstractRepository {

	@Query("select p from Memorandum p where p.fineDish.chef.id = :id")
	Collection<Memorandum> findMemorandumByChefId(int id);

	@Query("select p from Memorandum p where p.fineDish.id = :id")
	List<Memorandum> findByFineDishId(int id);

	@Query("select p from FineDish p where p.id = :id")
	FineDish findFineDishById(int id);

	@Query("select p from Memorandum p where p.id = :id")
	Memorandum findOneMemorandumById(int id);

	@Query("select p from Memorandum p where p.fineDish.id = :id")
	Collection<Memorandum> findMemorandumById2(int id);

	@Query("select p from Memorandum p")
	List<Memorandum> findMemorandum();
	


}
