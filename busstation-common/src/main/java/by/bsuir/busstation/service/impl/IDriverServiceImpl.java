package by.bsuir.busstation.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.dao.DaoException;
import by.bsuir.busstation.dao.IDriverDao;
import by.bsuir.busstation.entity.Driver;
import by.bsuir.busstation.service.IDriverService;
import by.bsuir.busstation.service.ServiceException;

public class IDriverServiceImpl implements IDriverService {

	private static final Logger LOG = LoggerFactory.getLogger(IDriverService.class);

	@Autowired
	private IDriverDao driverDao;

	@Override
	public Long create(Driver driver) throws ServiceException {
		try {
			Long key = driverDao.create(driver);
			LOG.info("Driver has created with id: " + key);
			return key;
		} catch (DaoException ex) {
			LOG.error("Error while creating driver: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public Driver read(Long key) throws ServiceException {
		try {
			Driver driver = driverDao.read(key);
			LOG.info("Driver has read with id: " + key);
			return driver;
		} catch (DaoException ex) {
			LOG.error("Error while reading driver: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<Driver> readAll() throws ServiceException {
		try {
			List<Driver> driverList = driverDao.readAll();
			LOG.info("Driver list has read. Size: " + driverList.size());
			return driverList;
		} catch (DaoException ex) {
			LOG.error("Error while reading driver list: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void update(Driver driver) throws ServiceException {
		try {
			driverDao.update(driver);
			LOG.info("Driver has updated with id: " + driver.getId());

		} catch (DaoException ex) {
			LOG.error("Error while updating driver: ", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void delete(Long key) throws ServiceException {
		try {
			driverDao.delete(key);
			LOG.info("Driver has deleted with id: " + key);

		} catch (DaoException ex) {
			LOG.error("Error while deleting driver: ", ex);
			throw new ServiceException(ex);
		}
	}

}
