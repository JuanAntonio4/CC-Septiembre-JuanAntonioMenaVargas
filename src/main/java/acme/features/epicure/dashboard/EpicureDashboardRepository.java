package acme.features.epicure.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.enums.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureDashboardRepository extends AbstractRepository {


	
	@Query("SELECT COUNT(finedish) FROM FineDish finedish WHERE finedish.status = :status AND finedish.epicure.id = :id")
	Integer getFinedishTotalsByStatus(Status status, int id);
	
	@Query("SELECT finedish.budget.currency, MIN(finedish.budget.amount), MAX(finedish.budget.amount), AVG(finedish.budget.amount), STDDEV(finedish.budget.amount) from FineDish finedish WHERE finedish.status = :status AND finedish.epicure.id = :id GROUP BY finedish.budget.currency")
	List<String> getFinedishBudgetByStatus(Status status, int id);
}
