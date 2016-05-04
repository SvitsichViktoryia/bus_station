package by.bsuir.busstation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IUserDao;
import by.bsuir.busstation.entity.User;
import by.bsuir.busstation.service.IUserService;
import by.bsuir.busstation.service.ServiceException;

import java.util.List;

public class IUserServiceImpl implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(IUserServiceImpl.class);

	@Autowired(required = true)
	private IUserDao userDao;

	@Override
	public User loadUserByUsername(String username) throws ServiceException {
		try {
			User user = userDao.loadUserByUsername(username);
			return user;
		} catch (DaoException ex) {
			LOG.error("Error while reading user by login: ", ex);
			throw new ServiceException(ex.getMessage());
		}
	}

	public Long create(User user) throws ServiceException {
		try {
			Long key = userDao.create(user);
			LOG.info("User have created with id: " + key);
			return key;
		} catch (DaoException ex) {
			LOG.error("Error while creating user: ", ex);
			throw new ServiceException(ex);
		}
	}

	public User read(Long key) throws ServiceException {
		try {
			User user = userDao.read(key);
			LOG.info("User have read: " + user.getId());
			return user;
		} catch (DaoException ex) {
			LOG.error("Error while reading user: ", ex);
			throw new ServiceException(ex);
		}
	}

	public List<User> readAll() throws ServiceException {
		try {
			List<User> userList = userDao.readAll();
			LOG.info("User list has read. Size: " + userList.size());
			return userList;
		} catch (DaoException ex) {
			LOG.error("Error while reading user list: ", ex);
			throw new ServiceException(ex);
		}
	}

	public User read(String login) throws ServiceException {
		try {
			User user = userDao.loadUserByUsername(login);
			LOG.info((user == null) ? "No such user. " : "User have read: " + user.getId());
			return user;
		} catch (DaoException ex) {
			LOG.error("Error while reading user: ", ex);
			throw new ServiceException(ex);
		}
	}

	public void update(User user) throws ServiceException {
		try {
			userDao.update(user);
			LOG.info("User have updated: " + user.getId());
		} catch (DaoException ex) {
			LOG.error("Error while updating user: ", ex);
			throw new ServiceException(ex);
		}
	}

	public void delete(Long key) throws ServiceException {
		try {
			userDao.delete(key);
			LOG.info("User have deleted: " + key);
		} catch (DaoException ex) {
			LOG.error("Error while deleting user: ", ex);
			throw new ServiceException(ex);
		}
	}

}
