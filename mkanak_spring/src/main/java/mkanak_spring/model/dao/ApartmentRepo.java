package mkanak_spring.model.dao;

import mkanak_spring.model.Apartment;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Long> {

}
