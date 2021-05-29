package mattingRitual;

import java.util.List;

public class Student extends Entity {


    public Student(String name) {
        super(name, 1);
    }



    @Override
    public String toString() {
        return "Stu[" + name  +
                ']';
    }
}
