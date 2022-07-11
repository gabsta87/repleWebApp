package dao;

public abstract class DAO <T>{
	
//	protected java.sql.Connection connect = ConnectionSQL.getInstance();
//	protected Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
	public abstract T find(int id);
	
	public abstract void saveOrUpdate(T obj);
	
	public abstract void create(T obj);
	
	public abstract void update(T obj);
	
	public abstract void delete(T obj);
	
}
