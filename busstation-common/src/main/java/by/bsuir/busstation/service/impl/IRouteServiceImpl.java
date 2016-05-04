package by.bsuir.busstation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IRouteDao;
import by.bsuir.busstation.entity.Route;
import by.bsuir.busstation.service.IRouteService;
import by.bsuir.busstation.service.ServiceException;

public class IRouteServiceImpl implements IRouteService {

	private static final Logger LOG = LoggerFactory.getLogger(IRouteService.class);

	@Autowired
	private IRouteDao routeDao;

	@Override
	public Long create(Route route) throws ServiceException {
		try {
			Long key = routeDao.create(route);
			LOG.info("Route has created with id: " + key);
			return key;
		} catch (DaoException ex) {
			LOG.error("Error while creating route: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public Route read(Long key) throws ServiceException {
		try {
			Route route = routeDao.read(key);
			LOG.info("Route has read with id: " + key);
			return route;
		} catch (DaoException ex) {
			LOG.error("Error while reading route: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<Route> readAll() throws ServiceException {
		try {
			List<Route> routeList = routeDao.readAll();
			LOG.info("Route list has read. Size: " + routeList.size());
			return routeList;
		} catch (DaoException ex) {
			LOG.error("Error while reading route list: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void update(Route route) throws ServiceException {
		try {
			routeDao.update(route);
			LOG.info("Route has updated with id: " + route.getId());

		} catch (DaoException ex) {
			LOG.error("Error while updating route: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void delete(Long key) throws ServiceException {
		try {
			routeDao.delete(key);
			LOG.info("Route has deleted with id: " + key);

		} catch (DaoException ex) {
			LOG.error("Error while deleting routes: ", ex);
			throw new ServiceException(ex);
		}
	}

}
