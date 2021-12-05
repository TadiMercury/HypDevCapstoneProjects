import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**This is the file handling class that will be used to operate any processing that has to do with
 * the project file and/or
 *  objects
 *  It has 9 methods that will be used in the posed class
 * @author Tadiwa Magara
 */
public class FileHandlingClass {


                    //Methods to be used in the poised class

    //Method to output projects

    /**
     * Used to output all projects on the file
     * @param arrayList is the ArrayList housing the project Objects
     */
    public static void outputProjects(ArrayList<projectObject> arrayList) {
        for (int i = 0; i< arrayList.size(); i++) {
            projectObject iNNE = arrayList.get(i);
            System.out.println("Project Number: "+(i+1) +"\nProject Name: \t"+iNNE.projectName +"\n");
        }
    }

    //Method to output projects that have not been marked as complete

    /**
     * Used to output projects that are not complete
     * @param arrayLists is the ArrayList housing the project Objects
     */
    public static void outputProjectNotComplete(ArrayList<projectObject> arrayLists) {
        int projComplete = arrayLists.size();
        for (int i = 0; i < arrayLists.size(); i++) {
            projectObject iNNE = arrayLists.get(i);
            if (iNNE.projectComplete != 'Y') {
                System.out.println("Project Number: " + (i + 1) + "\nProject Name: \t" + iNNE.projectName + "\n");
            }else {
                projComplete -= 1;
            }
        }
        if (projComplete == 0) {
            System.out.println("All projects recorded on file have been marked as completed");
        }

    }

    /**
     * Used to output projects past due date
     * @param arrayLists is the ArrayList housing the project Objects
     */
    //Method to output projects that are pas the due date
    public static void outputProjectPastDate(ArrayList<projectObject> arrayLists) {
        int projectsNotDue = arrayLists.size();
        Date currentDate = new Date();
        for (int a = 0; a < arrayLists.size(); a++) {
            projectObject iNNE = arrayLists.get(a);
            if (currentDate.after(iNNE.dateDue)) {
                System.out.println("Project Number: " + (a + 1) + "\nProject Name: \t" + iNNE.projectName + "\n");
            }else {
                projectsNotDue -= 1;
            }
        }
        if (projectsNotDue == 0) System.out.println("All projects recorded on file are not past their due date");
    }

    /**
     * Used to write to a file
     * @param Filename text file name
     * @param text String text to write to file
     * @param append boolean to append(true) or write(false)
     * @throws IOException Throws an exception if the file does not exist
     */
    //Method to save a line of text (String) into a text file
    public static void saveToFile(String Filename, String text, boolean append) throws IOException {

        //Create a file
        File file1 = new File(Filename);

        //Create a file writer class
        FileWriter fw = new FileWriter(file1, append);

        //Create a print writer class
        PrintWriter pw = new PrintWriter(fw);

        pw.println(text);

        pw.close();
    }

    /**
     * Used to read data from file and write data into object list
     * @param fileName text file name
     * @return project Object list
     * @throws FileNotFoundException Throws an exception if the file does not exist
     * @throws ParseException Throws an exception if the method could not parse data sets
     */
    //Method to read strings from a file as an object.
    public static ArrayList<projectObject> readObjectsFromFile(String fileName) throws FileNotFoundException, ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        File file = new File(fileName);
        Scanner s = new Scanner(file);
        ArrayList<projectObject> projectList = new ArrayList<>();


        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] itemArray = line.split("\\|");

            projectObject testProject;
            if (itemArray.length == 13) {
                testProject = readAllFieldsFromList(formatter, itemArray);
            } else {
                testProject = readFieldsFromList(formatter, itemArray);
            }
            projectList.add(testProject);
        }
        return projectList;
    }

    /**
     * Used to write all data read from a file into an arrayList
     * @param formatter used to format date (yyyy-MM-dd)
     * @param itemArray contains a line of string split by delimiter '|'
     * @return returns a project object
     * @throws ParseException Throws an exception if the method could not parse data sets
     */
    //Method to read strings from a file as an object - If the text has additional info
    private static projectObject readAllFieldsFromList(SimpleDateFormat formatter, String[] itemArray) throws ParseException {
        String projectName = itemArray[0];
        int projectNumber = Integer.parseInt(itemArray[1]);

        String [] customerList = itemArray[2].split(" && ");
        personMainObject customerObject = new personMainObject(customerList[0], customerList[1], customerList[2], customerList[3]);

        String [] contractorList = itemArray[3].split(" && ");
        personMainObject contractorObject = new personMainObject(contractorList[0], contractorList[1], contractorList[2], contractorList[3]);

        String [] architectList = itemArray[4].split(" && ");
        personMainObject architectObject = new personMainObject(architectList[0], architectList[1], architectList[2], architectList[3]);

        String projectAddress = itemArray[5];
        String buildType = itemArray[6];
        int ERFNum = Integer.parseInt(itemArray[7]);
        float projectFee = Float.parseFloat(itemArray[8]);
        float amountPaid = Float.parseFloat(itemArray[9]);
        Date dateDue = formatter.parse(itemArray[10]) ;
        Date dateComplete = formatter.parse(itemArray[12]);

        //Creating an object from the given string data
        projectObject testProject = new projectObject(projectNumber, projectName, buildType, projectAddress, ERFNum, projectFee, amountPaid, dateDue, customerObject, contractorObject, architectObject );
        testProject.setProjectCompleteFromFile(dateComplete);
        return testProject;
    }

    /**
     * Used to write data read from a file into an arrayList
     * @param formatter used to format date (yyyy-MM-dd)
     * @param itemArray contains a line of string split by delimiter '|'
     * @return returns a project object
     * @throws ParseException Throws an exception if the method could not parse data sets
     */
    private static projectObject readFieldsFromList(SimpleDateFormat formatter, String[] itemArray) throws ParseException {
        String projectName = itemArray[0];
        int projectNumber = Integer.parseInt(itemArray[1]);

        String [] customerList = itemArray[2].split(" && ");
        personMainObject customerObject = new personMainObject(customerList[0], customerList[1], customerList[2], customerList[3]);

        String [] contractorList = itemArray[3].split(" && ");
        personMainObject contractorObject = new personMainObject(contractorList[0], contractorList[1], contractorList[2], contractorList[3]);

        String [] architectList = itemArray[4].split(" && ");
        personMainObject architectObject = new personMainObject(architectList[0], architectList[1], architectList[2], architectList[3]);

        String projectAddress = itemArray[5];
        String buildType = itemArray[6];
        int ERFNum = Integer.parseInt(itemArray[7]);
        float projectFee = Float.parseFloat(itemArray[8]);
        float amountPaid = Float.parseFloat(itemArray[9]);
        Date dateDue = formatter.parse(itemArray[10]) ;

        return new projectObject(projectNumber, projectName, buildType, projectAddress, ERFNum, projectFee, amountPaid, dateDue, customerObject, contractorObject, architectObject );
    }

    /**
     * Used to add an object to the end of an ArrayList
     * @param arrayList List of type projectObject
     * @param object object to be added
     */
    //Method to add an object to the end of the list
    public static void addObject(ArrayList<projectObject> arrayList, projectObject object) {
        //Adding an object to the list
        if (object != null) {
            arrayList.add(object);
            System.out.println("Project has been added to the list");
        } else {
            System.out.println("Project could not be added to the list");
        }
    }

    /**
     * Used in poised to select a project from a given list
     * @param inputObj Scanner Object
     * @param arrayList ArrayList of type projectObject
     * @return integer number choice
     */
    //Method to choose an object from the list
    public static int chooseFromList(Scanner inputObj,ArrayList<projectObject> arrayList) {
        int userChoice;

        while (true) {
            userChoice = poised.tryCatchIntInputMethod(inputObj,"\nPlease enter the number of the project you would like to finalise.\n");

            if (userChoice < 1 || userChoice > arrayList.size()) {
                System.out.println("Given number is not in the range of projects. Please try again");
            } else {
                break;
            }
        }
        return userChoice;
    }
}
