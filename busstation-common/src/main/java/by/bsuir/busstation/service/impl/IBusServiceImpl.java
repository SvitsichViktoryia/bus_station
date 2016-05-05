package by.bsuir.busstation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IBusDao;
import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.service.IBusService;
import by.bsuir.busstation.service.ServiceException;

public class IBusServiceImpl implements IBusService {

	private static final Logger LOG = LoggerFactory.getLogger(IBusService.class);

	@Autowired(required = true)
	private IBusDao busDao;

	@Override
	public Long create(Bus bus) throws ServiceException {
		try {
			Long key = busDao.create(bus);
			LOG.info("Bus has created with id: " + key);
			return key;
			
		} catch (DaoException ex) {
			LOG.error("Error while creating bus: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public Bus read(Long key) throws ServiceException {
		try {
			Bus bus = busDao.read(key);
			LOG.info("Bus has read with id: " + key);
			return bus;
			
		} catch (DaoException ex) {
			LOG.error("Error while reading bus: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<Bus> readAll() throws ServiceException {
		try {
			List<Bus> busList = busDao.readAll();
			LOG.info("Bus list has read. Size: " + busList.size());
			return busList;
			
		} catch (DaoException ex) {
			LOG.error("Error while reading bus list: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void update(Bus bus) throws ServiceException {
		try {
			busDao.update(bus);
			LOG.info("Bus has updated with id: " + bus.getId());

		} catch (DaoException ex) {
			LOG.error("Error while updating bus: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void delete(Long key) throws ServiceException {
		try {
			busDao.delete(key);
			LOG.info("Bus has deleted with id: " + key);

		} catch (DaoException ex) {
			LOG.error("Error while deleting bus: ", ex);
			throw new ServiceException(ex);
		}
	}

}
