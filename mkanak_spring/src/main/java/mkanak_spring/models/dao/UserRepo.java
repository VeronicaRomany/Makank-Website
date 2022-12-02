package mkanak_spring.models.dao;

import mkanak_spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // methods findByID, etc..
    @Query("FROM User WHERE username = ?1")
    User findByUsername(String username);
    @Query("FROM User WHERE email = ?1")
    User findByEmail(String email);
}
