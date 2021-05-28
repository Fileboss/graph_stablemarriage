package mattingRitual;

import java.util.ArrayList;
import java.util.List;

public class stableMarriageApplication {

    public static void main(String[] args) {
        //Get the data
        // first not from a file
        MattingPreferences mp = new MattingPreferences();

        Boolean studentFirst = true;


        List<String> lines = mp.getLines();
        List<String> columns = mp.getColumns();

        List<String> studentNotAssigned = new ArrayList<>(lines);
        List<String> schoolNotAssigned = new ArrayList<>(columns);
        int cpt = 0;

        while (!studentNotAssigned.isEmpty()) {





        }




    }
}
