package pe.bsanchez.dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pe.bsanchez.dao.entity.User;

@Singleton
public class UserDao {

	private EntityManagerFactory factory = null;
	private EntityManager entityManager = null;

	public User getAccess(User user) {

		try {
			factory = Persistence.createEntityManagerFactory("primary");
			entityManager = factory.createEntityManager();

			return this.queryUser(entityManager, user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (factory != null) {
				factory.close();
			}
		}

		return null;
	}

	private User queryUser(EntityManager entityManager, User user) {
		TypedQuery<User> query = entityManager
				.createQuery("from User u where u.username = :username and u.password = :password", User.class)
				.setParameter("username", user.getUsername()).setParameter("password", user.getPassword());
		for (User actualUser : query.getResultList()) {
			if (actualUser != null) {
				return actualUser;
			}
		}

		return null;
	}

}
