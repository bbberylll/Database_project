package DTO;

public class ReservationCountByTrain {
	private String trainId;
    private int count;
    
 // 생성자
    public ReservationCountByTrain(String trainId, int count) {
        this.trainId = trainId;
        this.count = count;
    }

    // getter / setter
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
