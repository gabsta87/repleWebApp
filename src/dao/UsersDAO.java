package dao;

import org.hibernate.Transaction;

import accountManagement.User;
import dao.Connection.SessionManager;

public class UsersDAO extends DAO<User>{

	public UsersDAO() {	}

	@Override
	public User find(int id) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		User result = SessionManager.getSessionFactory().getCurrentSession().get(User.class, id);
		t.commit();
		return result;
	}

	@Override
	public void create(User obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().save(obj);
		t.commit();
	}

	@Override
	public void update(User obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().update(obj);
		t.commit();
	}

	@Override
	public void delete(User obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().delete(obj);
		t.commit();
	}
	
	public User checkUser(String name, String password) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		User result = SessionManager.getSessionFactory().getCurrentSession().get(User.class,1);
		t.commit();
		return result;
	}

	@Override
	public void saveOrUpdate(User obj) {
		Transaction t = SessionManager.getSessionFactory().getCurrentSession().beginTransaction();
		SessionManager.getSessionFactory().getCurrentSession().saveOrUpdate(obj);
		t.commit();
	}
	
}
