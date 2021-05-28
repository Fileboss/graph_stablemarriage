package mattingRitual;

import java.util.List;

public class Student extends Entity {


    public Student(String name) {
        super(name, 1);
    }

    public Entity popPreferedSchool() {
        return this.preferenceList.remove(0);
    }




}
