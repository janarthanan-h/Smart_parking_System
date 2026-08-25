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
	
	public String CreatUser(userData data) {
		User.save(data);
		return "New User Created";
	}
	
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
