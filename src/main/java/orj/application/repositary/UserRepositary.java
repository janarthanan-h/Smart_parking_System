package orj.application.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import orj.application.entity.userData;

public interface UserRepositary extends JpaRepository<userData,Integer> {
	
	Optional<userData> findByNameAndPassword(String name,String password);

}
