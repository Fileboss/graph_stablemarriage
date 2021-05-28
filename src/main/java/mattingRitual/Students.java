package mattingRitual;

import java.util.LinkedList;
import java.util.List;

public class Students {
    private String name;

    public Students(List<Integer> preferences, List<String> schools) {

    }

    private List<Integer> valueAsIndice(List<Integer> inputList) {
        int[] tabTemp = new int[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            tabTemp[inputList.get(i) - 1] = i;
        }
        List<Integer> retour = new LinkedList<>();
        for (Integer I : tabTemp) {
            retour.add(I);
        }
        return retour;
    }
}
