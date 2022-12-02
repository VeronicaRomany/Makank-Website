package mkanak_spring.models.dao;

import mkanak_spring.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Long> {
    @Query("FROM UserCredentials WHERE username = ?1")
    UserCredentials findByUsername(String username);
}
