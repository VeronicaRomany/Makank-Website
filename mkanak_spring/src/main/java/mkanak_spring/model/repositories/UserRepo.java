package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.User;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // methods findByID, etc..
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.username = ?1")
    Boolean findByUsername(String username);

    @Query("FROM User u WHERE u.userID = ?1")
    User findUserInfoByUseId(long userID);

}
