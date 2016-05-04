package by.bsuir.busstation.dao;

import java.util.List;

import by.bsuir.busstation.entity.Route;

public interface IRouteDao {
	Long create(Route route) throws DaoException;

	Route read(Long key) throws DaoException;

	List<Route> readAll() throws DaoException;

	void update(Route route) throws DaoException;

	void delete(Long key) throws DaoException;
}
