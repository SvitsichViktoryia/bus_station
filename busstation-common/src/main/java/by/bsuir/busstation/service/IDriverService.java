package by.bsuir.busstation.service;

import java.util.List;

import by.bsuir.busstation.entity.Driver;

public interface IDriverService {
	Long create(Driver driver) throws ServiceException;

	Driver read(Long key) throws ServiceException;

	List<Driver> readAll() throws ServiceException;

	void update(Driver driver) throws ServiceException;

	void delete(Long key) throws ServiceException;
}
