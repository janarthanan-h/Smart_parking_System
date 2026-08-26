package orj.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import orj.application.entity.parkingData;
import orj.application.entity.userData;
import orj.application.services.ParkDataServicess;
import orj.application.services.UserDataServices;

@RestController
@RequestMapping("/jpa/smps/Parking")
public class ParkDataControll {
	
	@Autowired
	private ParkDataServicess ser;
	
	@Autowired
	private UserDataServices User;
	
	 @GetMapping("/get")
	    public List<parkingData> getParkingData() {
	        return ser.getAllData();
	    }
	    
	    @PostMapping("/login")
	    public String Login(@RequestBody userData login,HttpSession session) {
	    	return User.login(session,login);
	    }
	    
	    @PostMapping("/in")
	    public String vehicleIn(@RequestBody parkingData data,HttpSession session) {
	    	
	        return ser.vehicleIn(data,session);
	    }

	    @PutMapping("/out/{slotno}")
	    public String vehicleOut(@PathVariable("slotno") int slotno ,HttpSession session) {
	    	
	        return ser.vehicleOut(slotno,session);
	    }
	  
	    @DeleteMapping("/delete/{slotno}")
	    public String deleteParkingData(@PathVariable("slotno") int slotno) {
	    	
	        return ser.deleteParkingData(slotno);
	    }
	    
}
