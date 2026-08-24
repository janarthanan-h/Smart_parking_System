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
import orj.application.entity.user;
import orj.application.services.Servicess;

@RestController
@RequestMapping("/BJP")
public class Controll {
	
	@Autowired
	Servicess ser;
	
	 @GetMapping("/get")
	    public List<parkingData> getParkingData() {
	        return ser.getAllData();
	    }
	    
	    @PostMapping("/login")
	    public String Login(@RequestBody user login,HttpSession session) {
	    	return ser.login(session,login);
	    }

	    @PostMapping("/in")
	    public String vehicleIn(@RequestBody parkingData data,HttpSession session) {
	        return ser.vehicleIn(data,session);
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
