package by.bsuir.busstation.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.entity.Driver;
import by.bsuir.busstation.service.IDriverService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class DriverCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String NAME_PARAM = "name";
	private static final String ID_PARAM = "id";

	private static final String EXPERIENCE_PARAM = "experience";
	private static final String DRIVERS_PARAM = "drivers";
	private static final String MESSAGE_PARAM = "message";

	private static final String DRIVE_PARAM = "driver";

	@Autowired
	private IDriverService driverService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		if (request.getMethod().equals(METHOD_POST)) {
			if (request.getParameter(DRIVE_PARAM) != null) {
				return deleteDriver(request);}
			String id = request.getParameter(ID_PARAM);
			String name = request.getParameter(NAME_PARAM);
			int experience = Integer.valueOf(request.getParameter(EXPERIENCE_PARAM));
			
			if (id != null) {
				return updateDriver(Long.valueOf(id), name, experience);
			} else {
				return createDriver(name, experience);
			}

		} else {
			List<Driver> drivers = new ArrayList<>(new HashSet<>(driverService.readAll()));
			request.setAttribute(DRIVERS_PARAM, drivers);
			return PagesUtil.VIEW_DRIVERS;
		}
	}
	private String deleteDriver(HttpServletRequest request) throws ServiceException{
		Long driverId = Long.valueOf(request.getParameter(DRIVE_PARAM));
		try {
			Driver driver = driverService.read(driverId);
			driverService.delete(driverId);

		} catch (Exception e) {
			List<Driver> drivers = new ArrayList<>(new HashSet<>(driverService.readAll()));
			request.setAttribute(DRIVERS_PARAM, drivers);
			request.setAttribute(MESSAGE_PARAM, "Невозможно удалить водителя");
			return PagesUtil.VIEW_DRIVERS;
		}
		return PagesUtil.REDIRECT_DRIVERS;
	}
	private String createDriver(String name, int experience) throws ServiceException {
		Driver driver = new Driver();
		driver.setName(name);
		driver.setExperience(experience);
		driverService.create(driver);
		return PagesUtil.REDIRECT_DRIVERS;
	}

	private String updateDriver(Long id, String name, int experience) throws ServiceException {
		Driver driver = driverService.read(id);
		driver.setName(name);
		driver.setExperience(experience);
		driverService.update(driver);
		return PagesUtil.REDIRECT_DRIVERS;
	}
}
