package service;

import DAO.TrainDAO;
import DAO.TrainScheduleDAO;
import model.Train;
import model.TrainSchedule;

import java.time.LocalDateTime;
import java.util.List;

public class TrainService {
    private final TrainDAO trainDao = new TrainDAO();
    private final TrainScheduleDAO scheduleDao = new TrainScheduleDAO();

    //  Train CRUD 

    /** 기차 등록 */
    public void addTrain(Train t) {
        trainDao.insertTrain(t);
    }

    /** 전체 기차 조회 */
    public List<Train> listAllTrains() {
        return trainDao.getAllTrains();
    }

    /** 목적지로 기차 조회 */
    public List<Train> findTrainsByDestination(String dest) {
        return trainDao.findByDestination(dest);
    }

    /** 출발 시간 업데이트 */
    public void updateDepartureTime(String trainId, LocalDateTime newTime) {
        trainDao.updateDepartureTime(trainId, newTime);
    }

    /** 기차 삭제 */
    public void deleteTrain(String trainId) {
        trainDao.deleteTrainById(trainId);
    }

    // ■ TrainSchedule 조회 통합 ■

    /**
     * 기차 이름으로 스케줄 조회
     * @param trainName 조회할 기차 이름 (trim 처리 권장)
     * @return 해당 기차의 스케줄 리스트
     */
    public List<TrainSchedule> findSchedulesByTrainName(String trainName) {
        return scheduleDao.getSchedulesByTrainName(trainName.trim());
    }
}