package mkanak_spring.model;

public class Villa extends Property {
   boolean hasGarden;
   boolean hasPool;
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
