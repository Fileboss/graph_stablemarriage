package mattingRitual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class stableMarriageApplication {

    public static void main(String[] args) {



        final String FILE = args[0];
        final String STARTER = args[1];
        final int SCHOOLCAPACITY = Integer.parseInt(args[2]);
        boolean studentsStart = true;

        switch (STARTER) {
            case "students" : studentsStart = true; break;
            case "schools" : studentsStart = false; break;
            default:
                System.out.println("ERREUR FORMAT ARGUMENTS");
                System.exit(2);
        }

        //Get the data
        // first not from a file
        MattingPreferences mp = new MattingPreferences(FILE);
        System.out.println(mp);

        //create all the students
        Student[] students = new Student[mp.getLines().size()];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(mp.getLines().get(i));
        }

        // create all schools
        School[] schools = new School[mp.getColumns().size()];
        for (int i = 0; i < schools.length; i++) {
            schools[i] = new School(mp.getColumns().get(i), SCHOOLCAPACITY);
        }

        // Assign student preference list
        for (int i = 0; i < students.length; i++) {
            List<Integer> preferences = valueAsIndice2(mp.getLine(i));
            for (Integer ind : preferences) {
                students[i].addToPreferenceList(schools[ind]);
            }
        }

        // Assign School preference list
        for (int i = 0; i < schools.length; i++) {
            List<Integer> preferences = valueAsIndice2(mp.getColumn(i));
            for (Integer ind : preferences) {
                schools[i].addToPreferenceList(students[ind]);
            }
        }

        // algo
        List<Entity> notAssignedMatters;
        List<Entity> matters;
        List<Entity> matteds;

        if (studentsStart) {
            matters =  new LinkedList<>(Arrays.asList(students));
            matteds = new LinkedList<>(Arrays.asList(schools));
            notAssignedMatters =matters;
        } else {
            matters = new LinkedList<>(Arrays.asList(schools));
            matteds = new LinkedList<>(Arrays.asList(students));
            notAssignedMatters =matters;
        }


        //List<Student> notAssignedStudents = Arrays.asList(students);
        int cptTour = 0;
        //Algo
        while ( ! notAssignedMatters.isEmpty()) {

            // Gestion des élèves non assignés ?

            List<Entity> toRemove = new LinkedList<>();
            for (Entity matter : notAssignedMatters) {
                matter.popPrefered().addToWaitingList(matter);
                matter.decreaseCapacity();
                if (matter.getCapacity() == 0) {
                    toRemove.add(matter);
                }
            }

            notAssignedMatters.removeAll(toRemove);
            //for(Entity e : toRemove) notAssignedMatters.remove(e);

            for (Entity matted : matteds) {
                while(matted.getWaitingList().size() > matted.getCapacity()) {
                    Entity matter = matted.popWorseFromWaitingList();
                    matter.increaseCapacity();
                    if (!notAssignedMatters.contains(matter))
                        notAssignedMatters.add(matter);
                }
            }
            cptTour++;
        }

        System.out.println("<><><><><><><><><><><><><><><><><>\n\nNB TOURS : "+cptTour+"\n<><><><><><><><><><><><><><><><><>");
        for (Entity matted : matteds) {
            System.out.println(matted.toStringWaitingList());
        }




    }

    /*private static List<Integer> valueAsIndice(List<Integer> inputList) {
        List<Integer> retour = new LinkedList<>();
        for (int i = 0; i < inputList.size(); i++) {
            int val = i+1;
            retour.add(inputList.lastIndexOf(val));
        }
        return retour;
    }*/

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
