package service;

import DAO.TrainDAO;
import model.Train;

import java.time.LocalDateTime;
import java.util.List;

public class TrainService {
	private final TrainDAO dao = new TrainDAO();

    public void addTrain(Train t) {
        dao.insertTrain(t);
    }
    public List<Train> listAllTrains() {
        return dao.getAllTrains();
    }
    public List<Train> findTrainsByDestination(String dest) {
        return dao.findByDestination(dest);
    }
    public void updateDepartureTime(String trainId, LocalDateTime newTime) {
        dao.updateDepartureTime(trainId, newTime);
    }
    public void deleteTrain(String trainId) {
        dao.deleteTrainById(trainId);
    }

}
