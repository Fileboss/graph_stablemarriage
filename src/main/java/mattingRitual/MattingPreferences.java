package mattingRitual;

import java.util.ArrayList;
import java.util.Arrays;
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
        preferences = preferencesStock.clone();
    }

    public List<String> getLines() {
        return lines;
    }

    public List<String> getColumns() {
        return columns;
    }

    public Couple[][] getPreferences() {
        return preferences;
    }

    @Override
    public String toString() {
        StringBuilder buildPreferences = new StringBuilder();

        for (String c: columns) {
            buildPreferences.append("                  " + c + "              ");
        }
        buildPreferences.append("\n");

        for (int i = 0; i < lines.size(); i++){
            buildPreferences.append(lines.get(i) + "  ");
            for (int j = 0; j < columns.size(); j++){
                buildPreferences.append(preferences[i][j] + " ");
            }
            buildPreferences.append("\n");
        }
        return buildPreferences.toString();
    }

    private List<Integer> getLine(int indice) {
        List<Integer> retour = new ArrayList<>();
        for (int j = 0; j < preferences[0].length; j++) {
            retour.add(preferences[indice][j].getLeftMember());
        }
        return retour;
    }

    private List<Integer> getColumn(int indice) {
        List<Integer> retour = new ArrayList<>();
        for (int i = 0; i < preferences.length; i++) {
            retour.add(preferences[i][indice].getRightMember());
        }
        return retour;

    }

}
