package mkanak_spring.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "apartment")
public class Apartment extends Property {
    @Column(name = "apartment_level")
    private int level;
    @Column(name = "has_elevator")
    private boolean elevator;
    @Column(name = "for_students")
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
