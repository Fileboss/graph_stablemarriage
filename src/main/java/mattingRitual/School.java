package mattingRitual;

import java.util.List;

public class School extends  Entity{


    public School(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public String toString() {
        return "Sch[" +
                name +
                ']';
    }
}
