package acme.features.authenticated.bulletin;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.Bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBulletinRepository extends AbstractRepository {
	
	@Query("select t from Bulletin t where t.instationMoment > :fecha")
	List<Bulletin> findBulletins(Date fecha);
	
	@Query("select t from Bulletin t where t.id = :id")
	Bulletin findBulletinById(int id);
	


}