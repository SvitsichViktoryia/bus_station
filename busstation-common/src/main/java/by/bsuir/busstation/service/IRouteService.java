package by.bsuir.busstation.service;

import java.util.List;

import by.bsuir.busstation.entity.Route;

public interface IRouteService {
	Long create(Route route) throws ServiceException;

	Route read(Long key) throws ServiceException;

	List<Route> readAll() throws ServiceException;

	void update(Route route) throws ServiceException;

	void delete(Long key) throws ServiceException;
}
