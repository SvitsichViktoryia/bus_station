package by.bsuir.busstation.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IUserDao;
import by.bsuir.busstation.entity.User;
import by.bsuir.busstation.utils.HibernateUtils;

import java.util.Collections;
import java.util.List;

public class IUserDaoImpl implements IUserDao {

	private static final String LOGIN_PARAM = "login";

	@Autowired
	private HibernateUtils manager;

	@Override
	public Long create(User user) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			Long key = (Long) session.save(user);
			session.getTransaction().commit();
			return key;

		} catch (HibernateException ex) {
			throw new DaoException("Error while creating new User instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public User read(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			User user = (User) session.get(User.class, key);
			return user;

		} catch (HibernateException ex) {
			throw new DaoException("error while reading User instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	public List<User> readAll() throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			List<User> userList = session.createCriteria(User.class).list();
			return Collections.unmodifiableList(userList);

		} catch (HibernateException ex) {
			throw new DaoException("error while reading all Driver instances. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}


	@Override
	public User loadUserByUsername(String name) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq(LOGIN_PARAM, name));
			User user = (User) criteria.uniqueResult();
			return user;

		} catch (HibernateException ex) {
			throw new DaoException("error while reading User instance by login. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}

	@Override
	public void update(User user) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while updating User instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}

	}

	@Override
	public void delete(Long key) throws DaoException {
		Session session = null;
		try {
			session = manager.getSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, key);
			session.delete(user);
			session.getTransaction().commit();

		} catch (HibernateException ex) {
			throw new DaoException("error while deleting User instance. See Details: ", ex);
		} finally {
			manager.closeSession(session);
		}
	}
}
