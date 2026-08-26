package orj.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orj.application.entity.ParkingHistoryData;
import orj.application.repositary.ParkingHistoryRepositary;

@Service
public class ParkingHistoryServices {
	
	@Autowired
	ParkingHistoryRepositary history;

	public List<ParkingHistoryData> getAllHistory() {
        return history.findAll();
    }
	
	public ParkingHistoryData getByCarNumber(String carNumber) {
		return history.findByCarNumber(carNumber)
				.orElseThrow(()->new RuntimeException(carNumber +" : Car is Not Found "));
		
	}
	
}
