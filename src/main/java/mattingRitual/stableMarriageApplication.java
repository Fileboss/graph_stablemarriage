package mattingRitual;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class stableMarriageApplication {

    public static void main(String[] args) {
        //Get the data
        // first not from a file
        MattingPreferences mp = new MattingPreferences();

        List<Integer>[] listeAttente = new List[mp.getColumns().size()];
        for (int i = 0; i < listeAttente.length; i++) {
            listeAttente[i] = new LinkedList<>();
        }

        List<Integer>[] listePreferenceEleve = new List[mp.getLines().size()];
        for (int i = 0; i < listePreferenceEleve.length; i++) {
            listePreferenceEleve[i] = valueAsIndice(mp.getLine(i));
        }


        Boolean studentFirst = true;


        List<String> lines = mp.getLines();
        List<String> columns = mp.getColumns();

        List<String> studentNotAssigned = new ArrayList<>(lines);
        List<String> schoolNotAssigned = new ArrayList<>(columns);
        int cpt = 0;

        while (!studentNotAssigned.isEmpty()) {
        }
    }

    public static List<Integer> valueAsIndice(List<Integer> inputList) {
        List<Integer> retour = new LinkedList<>();
        for (int i = 0; i < inputList.size(); i++) {
            int val = i+1;
            retour.add(inputList.lastIndexOf(val));
        }
        return retour;
    }

    public static List<Integer> valueAsIndice2(List<Integer> inputList){
        int[] tabTemp = new int[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            tabTemp[inputList.get(i)-1] = i;
        }
        List<Integer> retour = new LinkedList<>();
        for (Integer I: tabTemp) {
            retour.add(I);
        }
        return retour;
    }
}
