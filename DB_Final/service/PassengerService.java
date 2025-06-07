package service;
import DAO.PassengerDAO;
import model.Passenger;

import java.util.List;

public class PassengerService {
	private final PassengerDAO dao = new PassengerDAO();

    public void addPassenger(Passenger p) {
        dao.insertPassenger(p);
    }
    public List<Passenger> listAllPassengers() {
        return dao.getAllPassengers();
    }
    public void updatePassengerEmail(String id, String email) {
        dao.updatePassengerEmail(id, email);
    }
    public void deletePassenger(String id) {
        dao.deletePassengerById(id);
    }

}
