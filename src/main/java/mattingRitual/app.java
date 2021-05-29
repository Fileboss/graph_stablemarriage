package mattingRitual;

import java.util.ArrayList;
import java.util.List;

public class app {

    public static void main(String[] args) {
        /*MattingPreferences mt = new MattingPreferences();
        System.out.println(mt);
        List<Integer> test = new ArrayList<>();
        for (int i = 1; i < 10000; i++){
            test.add(i);
        }

        long startTime = System.nanoTime();
        List<Integer> result2 = stableMarriageApplication.valueAsIndice2(test);
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime) / 100000);

        startTime = System.nanoTime();
        List<Integer> result1 = stableMarriageApplication.valueAsIndice2(test);
        stopTime = System.nanoTime();
        System.out.println((stopTime - startTime) / 100000);

        System.out.println(result2);
        System.out.println(result1);*/

        MattingPreferences mp = new MattingPreferences("example1.csv");
        System.out.println(mp.toString());
    }
}
