package mattingRitual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class stableMarriageApplication {

    public static void main(String[] args) {

        // Test if there are exactly 2 arguments
        if (args.length != 2) {
            System.out.println("ERREUR FORMAT ARGUMENTS");
            System.exit(2);
        }
        final String FILE = args[0];
        final String STARTER = args[1];
        boolean startWithLines = true;

        // Determine if the Entities on the lines or the columns do the biding
        switch (STARTER) {
            case "lines" : startWithLines = true; break;
            case "columns" : startWithLines = false; break;
            default:
                System.out.println("ERREUR FORMAT ARGUMENTS");
                System.exit(2);
        }

        //Load the data from the csv file into mp
        MattingPreferences mp = new MattingPreferences(FILE);
        System.out.println(mp);

        //create all the linesEntities
        Entity[] linesEntities = new Entity[mp.getLinesLabels().size()];
        for (int i = 0; i < linesEntities.length; i++) {
            //Each entity has a different label but a same capacity
            linesEntities[i] = new Entity(mp.getLinesLabels().get(i), mp.getLinesCapacity());
        }

        // create all columnsEntities
        Entity[] columnsEntities = new Entity[mp.getColumnsLabels().size()];
        for (int i = 0; i < columnsEntities.length; i++) {
            //Each entity has a different label but a same capacity
            columnsEntities[i] = new Entity(mp.getColumnsLabels().get(i), mp.getColumnsCapacity());
        }

        // Assign the Entities from the lines
        // (in our case the Students) their respective preference list
        for (int i = 0; i < linesEntities.length; i++) {
            List<Integer> preferences = valueAsIndex(mp.getLine(i));
            for (Integer ind : preferences) {
                linesEntities[i].addToPreferenceList(columnsEntities[ind]);
            }
        }

        // Assign the Entities from the columns
        // (in our case the Schools) their respective preference list
        for (int i = 0; i < columnsEntities.length; i++) {
            List<Integer> preferences = valueAsIndex(mp.getColumn(i));
            for (Integer ind : preferences) {
                columnsEntities[i].addToPreferenceList(linesEntities[ind]);
            }
        }

        // The list of Entities that are not assigned to any other Entities
        // or that are not assigned to their full capacity
        List<Entity> notAssignedChoosers;

        // The list of Entities that can't be assigned
        List<Entity> impossibleToAssignChoosers = new ArrayList<>();

        // The list of Entities that do the biding
        List<Entity> Choosers;

        // The list of Entities that receive the biding and choose whom the keep
        List<Entity> chosens;

        // Assign the correct list depending on which Entity starts
        if (startWithLines) {
            // The Entities on the lines start
            Choosers =  new LinkedList<>(Arrays.asList(linesEntities));
            chosens = new LinkedList<>(Arrays.asList(columnsEntities));
            notAssignedChoosers =Choosers;
        } else {
            // The Entities on the columns start
            Choosers = new LinkedList<>(Arrays.asList(columnsEntities));
            chosens = new LinkedList<>(Arrays.asList(linesEntities));
            notAssignedChoosers =Choosers;
        }


        // Number of rounds needed to reach a stable marriage
        int cptTour = 0;

        // While the Choosers still have entities in their preference list
        // or can still be assigned a number of time
        // then we do one more round
        while ( ! notAssignedChoosers.isEmpty()) {
            // Biding

            // the list of Choosers that reached full capacity
            // or don't have anymore Entity in their preference list
            List<Entity> toRemove = new LinkedList<>();

            // We loop over each Entity that can still be assigned
            for (Entity chooser : notAssignedChoosers) {
                // chooser.popPrefered() -> return the next most prefered Entity
                // .addToWaitingList(chooser) -> add to the waiting list of this Entity the chooser
                // For instance if chooser is a Students then
                //      chooser.popPrefered() return the next prefered school
                //      and .addToWaitingLIst(chooser) add the studens to the waiting list of the school
                chooser.popPrefered().addToWaitingList(chooser);

                // The chooser can be assigned at most to capacity number of Entities
                // Therefore we decrease the capacity
                chooser.decreaseCapacity();

                // If a chooser is assigned to its max capacity
                // or doesn't have anymore Entity it can be assigned to
                if (chooser.getCapacity() == 0 || !chooser.isAssignable()) {
                    toRemove.add(chooser);
                }
            }
            notAssignedChoosers.removeAll(toRemove);

            // After assigning each chooser with each chosen we need to remove
            // the least preferred chooser from each chosen until it does not exceed
            // the capacity of the chosens
            for (Entity chosen : chosens) {
                while(chosen.getWaitingList().size() > chosen.getCapacity()) {
                    Entity chooser = chosen.popWorseFromWaitingList();

                    // Because we removed a chooser from the waiting list of a chosen
                    // we need to increase the number of time it can still be chosen
                    chooser.increaseCapacity();

                    // chooser can still be assigned and is not in the notAssignedChoosers list
                    if (!notAssignedChoosers.contains(chooser)) {
                        if (chooser.isAssignable()) {
                            notAssignedChoosers.add(chooser);
                        }
                    }
                }
            }
            cptTour++;
        }

        // Show results
        System.out.println("<><><><><><><><><><><><><><><><><>\n\nNB TOURS : "+cptTour+"\n<><><><><><><><><><><><><><><><><>");
        for (Entity chooser : chosens) {
            System.out.println(chooser.toStringWaitingList());
        }
        //System.out.println("<><><><><><><><><><><><><><><><><>\nASSIGNATION INCOMPLETE : "+impossibleToAssignChoosers);

    }

    // This method invert the values with the indexes
    // For instance if we have the list [1, 3, 2] then the result will be [0, 2, 1]
    public static List<Integer> valueAsIndex(List<Integer> inputList){
        // Use an array to optimize writing
        int[] tabTemp = new int[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            // -1 because the values goes from 1 to n will the indexes goes from 0 to n-1
            // We write the index i at the position = value to optimize
            tabTemp[inputList.get(i)-1] = i;
        }
        // Return a linked list to optimize remove() methode
        List<Integer> retour = new LinkedList<>();
        for (Integer I: tabTemp) {
            retour.add(I);
        }
        return retour;
    }
}
