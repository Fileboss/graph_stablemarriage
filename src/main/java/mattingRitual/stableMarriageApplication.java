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
            List<Integer> preferences = valueAsIndice(mp.getLine(i));
            for (Integer ind : preferences) {
                linesEntities[i].addToPreferenceList(columnsEntities[ind]);
            }
        }

        // Assign the Entities from the columns
        // (in our case the Schools) their respective preference list
        for (int i = 0; i < columnsEntities.length; i++) {
            List<Integer> preferences = valueAsIndice(mp.getColumn(i));
            for (Integer ind : preferences) {
                columnsEntities[i].addToPreferenceList(linesEntities[ind]);
            }
        }

        // The list of Entities that are not assigned to any other Entities
        // or that are not assigned to their full capacity
        List<Entity> notAssignedMatters;

        // The list of Entities that can't be assigned
        List<Entity> impossibleToAssignMatters = new ArrayList<>();

        // The list of Entities that do the biding
        List<Entity> matters;

        // The list of Entities that receive the biding and choose whom the keep
        List<Entity> matteds;

        // Assign the correct list depending on which Entity starts
        if (startWithLines) {
            // The Entities on the lines start
            matters =  new LinkedList<>(Arrays.asList(linesEntities));
            matteds = new LinkedList<>(Arrays.asList(columnsEntities));
            notAssignedMatters =matters;
        } else {
            // The Entities on the columns start
            matters = new LinkedList<>(Arrays.asList(columnsEntities));
            matteds = new LinkedList<>(Arrays.asList(linesEntities));
            notAssignedMatters =matters;
        }


        // Number of rounds needed to reach a stable marriage
        int cptTour = 0;

        // While the matters still have entities in their preference list
        // or can still be assigned a number of time
        // then we do one more round
        while ( ! notAssignedMatters.isEmpty()) {
            // Biding

            // the list of matters that reached full capacity
            // or don't have anymore Entity in their preference list
            List<Entity> toRemove = new LinkedList<>();

            // We loop over each Entity that can still be assigned
            for (Entity matter : notAssignedMatters) {
                // matter.popPrefered() -> return the next most prefered Entity
                // .addToWaitingList(matter) -> add to the waiting list of this Entity the matter
                // For instance if matter is a Students then
                //      matter.popPrefered() return the next prefered school
                //      and .addToWaitingLIst(matter) add the studens to the waiting list of the school
                matter.popPrefered().addToWaitingList(matter);

                // The matter can be assigned at most to capacity number of Entities
                // Therefore we decrease the capacity
                matter.decreaseCapacity();

                // If a matter is assigned to its max capacity
                // or doesn't have anymore Entity it can be assigned to
                if (matter.getCapacity() == 0 || !matter.isAssignable()) {
                    toRemove.add(matter);
                }
            }
            notAssignedMatters.removeAll(toRemove);

            // After assigning each matter with the matted we need to remove
            // the least prefered matter from the matted until it does not exceed
            // the capacity of the matted
            for (Entity matted : matteds) {
                while(matted.getWaitingList().size() > matted.getCapacity()) {
                    Entity matter = matted.popWorseFromWaitingList();

                    // Because we removed a matter from the waiting list of a matted
                    // we need to increase the number of time it can still be matted
                    matter.increaseCapacity();

                    // matter can still be assigned and is not in the notAssignedMatters list
                    if (!notAssignedMatters.contains(matter)) {
                        if (matter.isAssignable()) {
                            notAssignedMatters.add(matter);
                        }
                    }
                }
            }
            cptTour++;
        }

        // Show results
        System.out.println("<><><><><><><><><><><><><><><><><>\n\nNB TOURS : "+cptTour+"\n<><><><><><><><><><><><><><><><><>");
        for (Entity matted : matteds) {
            System.out.println(matted.toStringWaitingList());
        }
        //System.out.println("<><><><><><><><><><><><><><><><><>\nASSIGNATION INCOMPLETE : "+impossibleToAssignMatters);

    }

    public static List<Integer> valueAsIndice(List<Integer> inputList){
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
