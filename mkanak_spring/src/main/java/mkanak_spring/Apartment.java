package mkanak_spring;

public class Apartment extends Property{
    private int level;
    private boolean elevator;
    private boolean studentHousing;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    public boolean isStudentHousing() {
        return studentHousing;
    }

    public void setStudentHousing(boolean studentHousing) {
        this.studentHousing = studentHousing;
    }
}
