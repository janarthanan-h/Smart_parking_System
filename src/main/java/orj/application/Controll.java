package orj.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controll {
	
	@Autowired
	Servicess ser;
	
	 @GetMapping("/get")
	    public List<parkingData> getParkingData() {
	        return ser.getAllData();
	    }

	    @PostMapping("/in")
	    public parkingData vehicleIn(@RequestBody parkingData data) {
	        return ser.vehicleIn(data);
	    }

	    @PutMapping("/out/{slotno}")
	    public parkingData vehicleOut(@PathVariable("slotno") int slotno) {

	        return ser.vehicleOut(slotno);
	    }
	  
	    @DeleteMapping("/delete/{slotno}")
	    public String deleteParkingData(@PathVariable("slotno") int slotno) {

	        return ser.deleteParkingData(slotno);
	    }
	    
}
