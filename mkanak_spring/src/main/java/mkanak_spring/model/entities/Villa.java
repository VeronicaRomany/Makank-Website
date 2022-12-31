package mkanak_spring.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "villa")
public class Villa extends Property {
   @Column(name = "has_garden")
   boolean hasGarden;
   @Column(name = "has_pool")
   boolean hasPool;
   @Column(name = "villa_levels")
   int numberOfLevels;

   public boolean isHasGarden() {
      return hasGarden;
   }

   public void setHasGarden(boolean hasGarden) {
      this.hasGarden = hasGarden;
   }

   public boolean isHasPool() {
      return hasPool;
   }

   public void setHasPool(boolean hasPool) {
      this.hasPool = hasPool;
   }

   public int getNumberOfLevels() {
      return numberOfLevels;
   }

   public void setNumberOfLevels(int numberOfLevels) {
      this.numberOfLevels = numberOfLevels;
   }

}
