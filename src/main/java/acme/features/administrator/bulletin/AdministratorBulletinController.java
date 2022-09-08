package acme.features.administrator.bulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.bulletin.Bulletin;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorBulletinController extends AbstractController<Administrator, Bulletin> {

	@Autowired
	protected AdministratorBulletinListService	listService;

	@Autowired
	protected AdministratorBulletinCreateService	createService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("create", this.createService);

	}

}