package by.bsuir.busstation.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsuir.busstation.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import by.bsuir.busstation.entity.Bus;
import by.bsuir.busstation.service.IBusService;
import by.bsuir.busstation.service.ServiceException;
import by.bsuir.busstation.utils.PagesUtil;

public class BusCommand implements Command {

	private static final String METHOD_POST = "POST";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String REG_NUMBER_PARAM = "regNumber";
	private static final String DATE_PARAM = "date";
	private static final String ID_PARAM = "id";
	private static final String SEATS_PARAM = "seats";
	private static final String BUSES_PARAM = "buses";
	private static final String BUS_PARAM = "bus";
	private static final String MESSAGE_PARAM = "message";
	private static final String MESSAGE1_PARAM = "message1";

	@Autowired
	private IBusService busService;

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		if (request.getMethod().equals(METHOD_POST)) {
			try{
				String seatss = request.getParameter(SEATS_PARAM);
				Pattern p = Pattern.compile("^[0-9]{1,15}$");
				Matcher m = p.matcher(seatss);
				if (!m.matches()){
					request.setAttribute(MESSAGE1_PARAM, "проверьте введённые данные.");
					List<Bus> buses = new ArrayList<>(new HashSet<>(busService.readAll()));
					request.setAttribute(BUSES_PARAM, buses);
					return PagesUtil.VIEW_BUSES;
				}
			}
			catch(Exception e){
			}
			if (request.getParameter(BUS_PARAM) != null) {
				return deleteBus(request);}
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			try {
				String regNumber = request.getParameter(REG_NUMBER_PARAM);
				String id = request.getParameter(ID_PARAM);
				Date inspectionDate = format.parse(request.getParameter(DATE_PARAM));
				int seats = Integer.valueOf(request.getParameter(SEATS_PARAM));

				if (id != null) {
					return updateBus(Long.valueOf(id), regNumber, seats, inspectionDate);
				} else {
					return createBus(regNumber, seats, inspectionDate);
				}
				
				
			} catch (ParseException ex) {
				throw new ServiceException(ex);
			}
			
		} else {
			List<Bus> buses = new ArrayList<>(new HashSet<>(busService.readAll()));
			request.setAttribute(BUSES_PARAM, buses);
			return PagesUtil.VIEW_BUSES;
		}
	}
	private String deleteBus(HttpServletRequest request) throws ServiceException{
		Long busId = Long.valueOf(request.getParameter(BUS_PARAM));
		try {
			Bus bus = busService.read(busId);
			busService.delete(busId);

		} catch (Exception e) {
			request.setAttribute(MESSAGE_PARAM, "Невозможно удалить автобус");
			List<Bus> buses = new ArrayList<>(new HashSet<>(busService.readAll()));
			request.setAttribute(BUSES_PARAM, buses);
			return PagesUtil.VIEW_BUSES;
		}
		return PagesUtil.REDIRECT_BUSES;
	}

	private String createBus(String regNumber, int seats, Date inspectionDate) throws ServiceException {
		Bus bus = new Bus();
		bus.setRegNumber(regNumber);
		bus.setSeats(seats);
		bus.setInspectionDate(inspectionDate);
		busService.create(bus);
		
		return PagesUtil.REDIRECT_BUSES;
	}
	
	private String updateBus(Long id, String regNumber, int seats, Date inspectionDate) throws ServiceException {
		Bus bus = busService.read(id);
		bus.setRegNumber(regNumber);
		bus.setSeats(seats);
		bus.setInspectionDate(inspectionDate);
		busService.update(bus);

		return PagesUtil.REDIRECT_BUSES;
	}
}
