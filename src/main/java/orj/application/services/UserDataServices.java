package orj.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import orj.application.entity.userData;
import orj.application.repositary.UserRepositary;

@Service
public class UserDataServices {
	@Autowired
	private UserRepositary User;
	
	
	public List<userData> getUser() {
		return User.findAll();
	}
	
	public userData getUserById(int id) {

        return User.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("User not found with id: " + id)
                );
    }
	
	public String CreatUser(userData data) {
		User.save(data);
		return "New User Created";
	}
	
	
	public String deletUser(int id) {
		if (User.existsById(id)) {
			User.deleteById(id);
			
			return "User Data Deleted Successfully";
		}
		return "User data not Fount";
	}
	
	// Using Parking Data's before Login first 
	public String login(HttpSession session, userData login) {

		 Optional<userData> Log = User.findByNameAndPassword(
	                login.getName(),
	                login.getPassword()
	        ); 
		 if (Log.isPresent()) {

		        session.setAttribute("user", login.getName());

		        return "Login Successful";
		    }
		 return "Invalid Username or Password";
		    
		}

	
}
