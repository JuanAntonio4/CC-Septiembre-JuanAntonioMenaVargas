package acme.features.administrator.bulletin;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletin.Bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBulletinRepository extends AbstractRepository {


	@Query("select c from Bulletin c where c.instationMoment > :maxTime")
	Collection<Bulletin> findLastAnnouncement(Date maxTime);
}