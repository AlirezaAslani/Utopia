package utopia.controller;

import javax.ejb.EJB;
import java.util.List;
import utopia.entities.TblUser;
import utopia.service.UserService;
import utopia.service.IUserService;
import javax.faces.bean.ManagedBean;
import utopia.session.IUserImplLocal;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "userController")
@SessionScoped
public class UserController {

	private TblUser tblUser = new TblUser();
	private String error = "";

	@EJB(name = "user")
	private IUserImplLocal userFacade;

	IUserService userService;

	@PostConstruct
	public void init() {
		try {

			userService = new UserService(userFacade);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TblUser getTblUser() {
		return tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<TblUser> findAll() {
		return this.userFacade.findAll();

	}

	public String processAdd(TblUser TblUser) {
		try {
			this.userFacade.create(tblUser);
			return "User Added Successfully?faces-redirect=true";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "Unexpected Error?faces-redirect=true";
		}

	}

	public String processDelete(TblUser TblUser) {
		try {
			this.userFacade.remove(TblUser);
			return "User Deleted Successfully?faces-redirect=true";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "Unexpected Error?faces-redirect=true";

		}

	}

	public String processEdit() {
		try {
			this.userFacade.edit(tblUser);
			return "User Edited Successfully?faces-redirect=true";
		} catch (Exception e) {
			this.error = e.getMessage();
			return "Unexpected Error?faces-redirect=true";
		}

	}
}