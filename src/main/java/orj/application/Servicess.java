package orj.application;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicess {
	
	@Autowired
	Repositary rep;
	
	 public List<parkingData> getAllData() {
	        return rep.findAll();
	    }

	 
	 
	 public String deleteParkingData(int slotno) {

	        if (rep.existsById(slotno)) {
	            rep.deleteById(slotno);
	            return "Parking data deleted successfully";
	        }

	        return "Parking data not found";
	    }
	 
	 public parkingData vehicleIn(parkingData data) {

		    List<parkingData> parkingList = rep.findAll();

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

		    return rep.save(data);
		}
	 

	     public parkingData vehicleOut(int slotno) {

	         parkingData data = rep.findById(slotno)
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

	         return rep.save(data);
	     }
	 }
	 
