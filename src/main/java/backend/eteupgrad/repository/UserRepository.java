package backend.eteupgrad.repository;

import backend.eteupgrad.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserRepository {

	@PersistenceUnit(unitName = "Backendtest")
	private EntityManagerFactory entityManagerFactory;

	public boolean register(User newUser) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.persist(newUser);
			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		}
	}
	public User checkCredentials(String username) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<User> getAllUsers(Integer userID){
		EntityManager em= entityManagerFactory.createEntityManager();
		TypedQuery<User> query= em.createQuery("SELECT * from User u join fetch u.user u where u.id= :userid", User.class);
		query.setParameter("userid",userID);
		List<User> result=query.getResultList();

		return result;
	}
}
