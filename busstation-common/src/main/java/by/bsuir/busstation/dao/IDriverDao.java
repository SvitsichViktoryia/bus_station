package by.bsuir.busstation.dao;

import java.util.List;

import by.bsuir.busstation.entity.Driver;

public interface IDriverDao {
	Long create(Driver driver) throws DaoException;

	Driver read(Long key) throws DaoException;

	List<Driver> readAll() throws DaoException;

	void update(Driver driver) throws DaoException;

	void delete(Long key) throws DaoException;
}
