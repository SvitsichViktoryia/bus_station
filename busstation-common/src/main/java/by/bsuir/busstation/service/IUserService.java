package by.bsuir.busstation.service;

import by.bsuir.busstation.entity.User;

import java.util.List;

public interface IUserService {
	Long create(User user) throws ServiceException;

	User read(Long key) throws ServiceException;

	List<User> readAll() throws ServiceException;

	User loadUserByUsername(String name) throws ServiceException;

	void update(User user) throws ServiceException;

	void delete(Long key) throws ServiceException;
}
