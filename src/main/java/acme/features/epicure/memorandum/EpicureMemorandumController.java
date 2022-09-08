package acme.features.epicure.memorandum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.memorandum.Memorandum;
import acme.features.chef.memorandum.ChefMemorandumCreateService;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureMemorandumController extends AbstractController<Epicure, Memorandum>{
		

	@Autowired
	protected EpicureMemorandumListService listService;
	
	@Autowired
	protected EpicureMemorandumShowService showService;
	
	@Autowired
	protected EpicureMemorandumCreateService createService;
	
	
	
	@PostConstruct
	protected void initialise() {
		
		super.addCommand("list-by-fineDish", "list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}
