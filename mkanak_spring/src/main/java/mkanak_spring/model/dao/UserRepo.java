package mkanak_spring.model.dao;

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
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = ?1")
    Boolean findByEmail(String email);


    @Query("FROM User u WHERE u.userID = ?1")
    User findUserInfoByUseId(long userID);
    @Query("select P.phoneNumber FROM PhoneNumber P WHERE P.userID = ?1")
    String findUserPhoneByUseId(long userID);
}