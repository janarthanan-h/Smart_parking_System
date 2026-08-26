package orj.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orj.application.entity.ParkingHistoryData;
import orj.application.services.ParkingHistoryServices;

@RestController
@RequestMapping("/jpa/smps/history")
public class ParkingHistoryController {
	
	@Autowired
	ParkingHistoryServices parkHistory;
	
	@GetMapping("/parking-history")
    public List<ParkingHistoryData> getAllHistory() {
        return parkHistory.getAllHistory();
    }
	
	@GetMapping("/Serch-Carnumber/{carNumber}")
	private ParkingHistoryData getByCarnunber(@PathVariable ("carNumber") String carNumber) {
		return parkHistory.getByCarNumber(carNumber);
	}

}
