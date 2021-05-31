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

    /** Contains the names of each Entity of the lines from the csv file. */
    private final List<String> linesLabels;
    /** One capacity for each Entity in the line of Entity from the csv. **/
    private int linesCapacity;

    /** Contains the names of each Entity of the columns from the csv file. **/
    private final List<String> columnsLabels;
    /** One capacity for each Entity in the column of Entity from the csv file. **/
    private int columnsCapacity;

    /** Contains the preferences of the Entities from the lines toward
     * the Entities from the columns and vice versa.
     * For instance Couple[0][1] contains the preferences of the Entities of name
     */
    private Couple[][] preferences ;

    /** Create a MattingPreferences with the following data.
     * 1,1 Alpha Beta Gamma
     * A   2,2   1,1  3,3
     * B   1,1   2,2  3,1
     * C   1,3   2,3  3,2
     */
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

    /** Create a MattingPreferences from a csv file of name fileName.
     * Each element is separated with a tab.
     * Pleased edit tab with a text editor such as sublime text
     * (doesn't works with intellij)
     *
     * @param fileName the name of the csv file
     */
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

    /** Return the list of labels of the line elements
     *
     * @return lineLabels
     */
    public List<String> getLinesLabels() {
        return linesLabels;
    }

    /** Return the list of labels of the column elements
     *
     * @return columnsLabels
     */
    public List<String> getColumnsLabels() {
        return columnsLabels;
    }

    /** Return linesCapacity
     *
     * @return linesCapacity
     */
    public int getLinesCapacity() {
        return linesCapacity;
    }

    /** Return columnsCapacity
     *
     * @return columnsCapacity
     */
    public int getColumnsCapacity() {
        return columnsCapacity;
    }

    /** Return the preferences
     *
     * @return preferences
     */
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

    /** Return the line of index indice in the preferences array.
     * It corresponds to the Entity of name linesLabel[indice]
     *
     * @param indice the index of the Entity
     * @return the list of preferences of the Entity
     */
    public List<Integer> getLine(int indice) {
        List<Integer> retour = new ArrayList<>();
        for (int j = 0; j < preferences[0].length; j++) {
            retour.add(preferences[indice][j].getLeftMember());
        }
        return retour;
    }

    /** Return the column of index indice in the preferences array.
     * It corresponds to the Entity of name linesLabel[indice]
     *
     * @param indice the index of the Entity
     * @return the list of preferences of the Entity
     */
    public List<Integer> getColumn(int indice) {
        List<Integer> retour = new ArrayList<>();
        for (int i = 0; i < preferences.length; i++) {
            retour.add(preferences[i][indice].getRightMember());
        }
        return retour;
    }
}
