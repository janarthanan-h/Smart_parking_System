package orj.application.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import orj.application.entity.parkingData;
import orj.application.entity.user;
import orj.application.repositary.ParkingRepositary;
import orj.application.repositary.UserRepositary;

@Service
public class Servicess {
	
	@Autowired
	private ParkingRepositary park;
	
	@Autowired
	private UserRepositary User;
	
	 public List<parkingData> getAllData() {
	        return park.findAll();
	    }

	 
	 public String deleteParkingData(int slotno) {

	        if (park.existsById(slotno)) {
	            park.deleteById(slotno);
	            return "Parking data deleted successfully";
	        }

	        return "Parking data not found";
	    }
	 
	 public String vehicleIn(parkingData data ,HttpSession session) {
		 
		 Object attribute = session.getAttribute("user");
		 if (attribute == null) {
				return "Please Login First";
			}

		    List<parkingData> parkingList = park.findAll();
		    

		    int slot = 1;

		    while (true) {

		        boolean found = false;

		        for (parkingData p : parkingList) {

		            if (p.getSlotno() == slot) {
		                found = true;
		                break;
		            }
		        }

		        if (!found) {
		            break;
		        }

		        slot++;
		    }

		    data.setSlotno(slot);
		    data.setInTime(LocalDateTime.now());
		    data.setOutTime(null);
		    data.setDuration(0);
		    data.setAmount(0);
		    
		    park.save(data);
		    
		    return "Parking Data Saved Successfully";
		}
	 

	 public parkingData vehicleOut(int slotno) {

	         parkingData data = park.findById(slotno)
	                 .orElseThrow(() ->
	                         new RuntimeException("Slot not found"));

	         LocalDateTime outTime = LocalDateTime.now();

	         data.setOutTime(outTime);

	         Duration duration = Duration.between(
	                 data.getInTime(),
	                 outTime
	         );

	         long hours = duration.toHours();

	         if (hours == 0) {
	             hours = 1;
	         }

	         data.setDuration(hours);

	         double amount = hours * 40;

	         data.setAmount(amount);

	         return park.save(data);
	   }
	 
	 public String login(HttpSession session, user login) {

		 Optional<user> Log = User.findByUserNameAndPassword(
	                login.getUserName(),
	                login.getPassword()
	        ); 
		 if (Log.isPresent()) {

		        session.setAttribute("user", login.getUserName());

		        return "Login Successful";
		    }
		 return "Invalid Username or Password";
		    
		}
	     
	     
	 }
	 
