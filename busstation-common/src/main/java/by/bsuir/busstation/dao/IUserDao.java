package by.bsuir.busstation.dao;

import by.bsuir.busstation.entity.User;

import java.util.List;

public interface IUserDao {
	Long create(User user) throws DaoException;

	User read(Long key) throws DaoException;

	List<User> readAll() throws DaoException;

	User loadUserByUsername(String name) throws DaoException;

	void update(User user) throws DaoException;

	void delete(Long key) throws DaoException;
}
