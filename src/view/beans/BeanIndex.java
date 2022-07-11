package view.beans;

import javax.annotation.ManagedBean;
import javax.persistence.Basic;

import accountManagement.User;
import dao.UsersDAO;

@ManagedBean
public class BeanIndex {
	private String userName, password;
	private User user;
	private UsersDAO userDao ;
	
	public BeanIndex() {
		this.user = null;
		this.userName = "";
		this.password = "";
		this.userDao = new UsersDAO();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isUserLogged() {
		return user != null;
	}
	
	@Basic
	public void login() {
		user = userDao.checkUser(userName, password);
//		if(user == null) {
//			return "Login failed : No user found";
//		}else {
//			return user.getName();
//		}
	}
	
	@Basic
	public void logout() {
		user = null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
