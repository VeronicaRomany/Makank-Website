package mkanak_spring.model.repositories;

import jakarta.transaction.Transactional;
import mkanak_spring.model.entities.Villa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillaRepo extends JpaRepository<Villa, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete from villa v where v.post_id = ?1", nativeQuery = true)
    void deleteVilla(Long postID);
    @Transactional
    @Modifying
    @Query(value = "insert into villa (post_id, villa_levels, has_garden, has_pool) " +
            "select :id,:level,:garden,:pool", nativeQuery = true)
    void insertVilla(@Param("id") Long postID, @Param("garden") boolean hasGarden,
                         @Param("level") int level, @Param("pool") boolean hasPool);
}
