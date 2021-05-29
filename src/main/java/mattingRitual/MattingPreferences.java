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

    private /*final*/ List<String> lines = new ArrayList<>();

    private /*final*/ List<String> columns = new ArrayList<>();

    private /*final*/ Couple[][] preferences = new Couple[][]{
            {new Couple(2,2), new Couple(1,1), new Couple(3,3)},
            {new Couple(1,1), new Couple(2,2), new Couple(3,1)},
            {new Couple(1,3), new Couple(2,3), new Couple(3,2)}};

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
            while (reader.iterator().hasNext()) {
                result.add(reader.readNext());
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        System.out.println(result);


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
