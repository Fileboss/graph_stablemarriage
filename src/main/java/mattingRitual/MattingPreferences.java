package mattingRitual;

import java.util.ArrayList;
import java.util.List;

public class MattingPreferences {

    private final List<String> lines;

    private final List<String> columns;

    private final Couple[][] preferences;

    public MattingPreferences(){
        // Line
        List<String> linesStock = new ArrayList<>();
        linesStock.add("A");
        linesStock.add("B");
        linesStock.add("C");
        lines = new ArrayList<>(linesStock);

        // Column
        List<String> columnsStock = new ArrayList<>();
        columnsStock.add("Alpha");
        columnsStock.add("Beta");
        columnsStock.add("Gamma");
        columns = new ArrayList<>(columnsStock);

        // Preferences
        Couple[][] preferencesStock = new Couple[][]{
                {new Couple(1,2), new Couple(2,1), new Couple(3,3)},
                {new Couple(2,1), new Couple(1,2), new Couple(3,1)},
                {new Couple(1,3), new Couple(2,3), new Couple(3,2)}};
    }
}
