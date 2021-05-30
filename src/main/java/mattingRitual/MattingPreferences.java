package mattingRitual;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MattingPreferences {

    private final List<String> linesLabels;
    private int linesCapacity;

    private final List<String> columnsLabels;
    private int columnsCapacity;

    private /*final*/ Couple[][] preferences ;

    public MattingPreferences(){
        // Line
        List<String> linesStock = new ArrayList<>();
        linesStock.add("A");
        linesStock.add("B");
        linesStock.add("C");
        linesLabels = new ArrayList<>(linesStock);
        linesCapacity = 1;

        // Column
        List<String> columnsStock = new ArrayList<>();
        columnsStock.add("Alpha");
        columnsStock.add("Beta");
        columnsStock.add("Gamma");
        columnsLabels = new ArrayList<>(columnsStock);
        columnsCapacity = 1;

        // Preferences
        Couple[][] preferencesStock = new Couple[][]{
                {new Couple(2,2), new Couple(1,1), new Couple(3,3)},
                {new Couple(1,1), new Couple(2,2), new Couple(3,1)},
                {new Couple(1,3), new Couple(2,3), new Couple(3,2)}};
        preferences = preferencesStock.clone();
    }

    public MattingPreferences(String fileName) {
        final CSVParser parser = new CSVParserBuilder().withSeparator('\t').withIgnoreQuotations(true).build();
        List<String[]> result = new ArrayList<>();
        String path = "src/main/resources/"+fileName;
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(path))
            .withCSVParser(parser).build()) {
            result = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        List<String> firstLine = new ArrayList<>(Arrays.asList(result.get(0)));
        Couple capacities = new Couple(firstLine.remove(0));
        linesCapacity = capacities.getLeftMember();
        columnsCapacity = capacities.getRightMember();
        this.columnsLabels = firstLine;
        this.linesLabels = new ArrayList<>();
        preferences = new Couple[result.size()-1][columnsLabels.size()];

        for (int i = 1; i < result.size(); i++) {
            linesLabels.add(result.get(i)[0]);
            for (int j = 1; j < result.get(i).length; j++) {
                preferences[i-1][j-1] = new Couple(result.get(i)[j]);
            }

        }


    }

    public List<String> getLinesLabels() {
        return linesLabels;
    }

    public List<String> getColumnsLabels() {
        return columnsLabels;
    }

    public int getLinesCapacity() {
        return linesCapacity;
    }

    public int getColumnsCapacity() {
        return columnsCapacity;
    }

    public Couple[][] getPreferences() {
        return preferences;
    }

    @Override
    public String toString() {
        StringBuilder buildPreferences = new StringBuilder();

        buildPreferences.append("\t\t\t\t\t");
        for (String c: columnsLabels) {
            buildPreferences.append("" + c + "\t\t\t");
        }
        buildPreferences.append("\n");

        for (int i = 0; i < linesLabels.size(); i++){
            buildPreferences.append(linesLabels.get(i) + "\t\t\t\t");
            for (int j = 0; j < columnsLabels.size(); j++){
                buildPreferences.append(preferences[i][j] + "\t\t\t\t");
            }
            buildPreferences.append("\n");
        }
        return buildPreferences.toString();
    }

    public List<Integer> getLine(int indice) {
        List<Integer> retour = new ArrayList<>();
        for (int j = 0; j < preferences[0].length; j++) {
            retour.add(preferences[indice][j].getLeftMember());
        }
        return retour;
    }

    public List<Integer> getColumn(int indice) {
        List<Integer> retour = new ArrayList<>();
        for (int i = 0; i < preferences.length; i++) {
            retour.add(preferences[i][indice].getRightMember());
        }
        return retour;

    }

}
