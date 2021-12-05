

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 * Poised class is the main class file where the program will be executed from
 * The program manages/handles projects for an Engineering firm
 * It can create and store data such as Names, Addresses and the fee for the project
 *
 * @author Tadiwa Magara
 */
public class poised {

    //Public fields to be used throughout the program
    public static final String NEW_LINE = "\n";
    public static final String filepath ="src/projectFile.txt";
    public static   ArrayList<projectObject> objectArrayList;
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    //Try/Catch block to read the file contents into an array to be used in the program
    static {
        try {
            objectArrayList = FileHandlingClass.readObjectsFromFile(filepath);
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }


    //Main class where all methods and procedures will be run from
    public static void main(String[] args) throws IOException, ParseException {
        //Global variables/objects to be used in program

        projectObject poisedProjectObject = null;
        String userMenuChoice = poisedMenu();
        boolean isLoop = true;

        //Iterating through the menu interface then choosing what methods to execute based on user input
        while (isLoop) {

            if (Objects.equals(userMenuChoice, "1")) {
                System.out.println("Create new Project");
                poisedProjectObject = createProject();
                System.out.println(NEW_LINE);
                userMenuChoice = poisedMenu();
            }

            else if (Objects.equals(userMenuChoice, "2")) {
                System.out.println("View all created projects");
                /* Method-call to view all created the project */
                FileHandlingClass.outputProjects(objectArrayList);
                userMenuChoice = poisedMenu();
            }

            else if (Objects.equals(userMenuChoice, "3")) {
                System.out.println(NEW_LINE);

                //This will change the due date/Fee paid and/or the Contractor's contact details of the project if there has been a created project
                if (objectArrayList != null) {
                    //Method-call to change the details of the project
                    changeProjectDetails();
                } else {
                    System.out.println("User cannot perform this action because there are no projects on file.");
                }
                userMenuChoice = poisedMenu();

            }

            else if (Objects.equals(userMenuChoice, "4")) {
                System.out.println("Finalise a Project");
                //Method-call to finalise the project
                if (objectArrayList != null) {
                    //Method-call to change the details of the project
                    finaliseProject();
                } else {
                    System.out.println("User cannot perform this action because there are no projects on file.");
                }
                userMenuChoice = poisedMenu();
            }

            else if (Objects.equals(userMenuChoice, "5")) {
                System.out.println("View a newly created project (at runtime)");
                /* Method-call to view the created the project */
                if (poisedProjectObject != null) {
                    //Method-call to change the details of the project
                    outputProjectMethod(poisedProjectObject);
                } else {
                    System.out.println("User cannot perform this action because no project has been created during runtime (of this program)");
                }
                userMenuChoice = poisedMenu();

            }

            else if (Objects.equals(userMenuChoice, "-1")) {
                System.out.println("User exit");
                exitMethod();
                isLoop = false;

            }else {
                System.out.println("Invalid Choice, please retry");
                userMenuChoice = poisedMenu();
            }
        }
    }

            /* Refactored Extracted Method Creations */

    /**
     * Used to change particular details of a project
     * @throws ParseException Throws exception if it fails to parse data
     */
    private static void changeProjectDetails() throws ParseException {
        //Menu interface to change project details
        changeProjectDMenu();
        boolean isInnerLoop = true;
        Scanner inputObj = new Scanner(System.in);
        int projectChoice;
        projectObject finObject;


        //Iteration of user input and subsequent program action based on choice.
        while (isInnerLoop) {
            String  changeDetails = (inputObj.nextLine()).toLowerCase();
            switch (changeDetails) {
                case "d" -> {
                    System.out.println("\nThis column is to enter the new date the project is due.");
                    System.out.println("Here is a list of all projects that are past their due date:\n");
                    FileHandlingClass.outputProjectPastDate(objectArrayList);
                    projectChoice = FileHandlingClass.chooseFromList(inputObj, objectArrayList);
                    finObject = objectArrayList.get(projectChoice - 1);

                    int changeYearDue = tryCatchIntInputMethod(inputObj,"\nEnter the new year due for the project" );
                    System.out.println();
                    int changeMonthDue = tryCatchIntInputMethod(inputObj,"\nEnter the new month due for the project" );
                    System.out.println();
                    int changeDayDue = tryCatchIntInputMethod(inputObj,"\nEnter the new day due for the project" );
                    String dateStr = changeYearDue + "-" + changeMonthDue + "-" + changeDayDue;
                    Date changeDateDue = dateFormatter.parse(dateStr);

                    //Appending the new date value to its object attribute
                    finObject.setDateDue(changeDateDue);
                    System.out.println("\nDate value has been successfully changed");
                    System.out.println("New date due added is " + finObject.dateDue);
                    isInnerLoop = false;
                }
                case "a" -> {
                    finObject = getFinObject(inputObj);

                    float changeAmountPaid = tryCatchFloatInputMethod(inputObj,"\nEnter the new amount to add to the project" );

                    //Appending the new amount value to its object attribute
                    finObject.setAmountPaid(changeAmountPaid);
                    System.out.println("\nAmount paid has been successfully changed");
                    System.out.println("New amount added is R" + finObject.amountPaid);
                    isInnerLoop = false;
                }
                case "u" -> {
                    finObject = getFinObject(inputObj);

                    System.out.println("\nEnter the new telephone number and/or the new email address for the contractor");
                    String changeTelNumber = tryCatchStrInputMethod(inputObj, "\nEnter new telephone number - Leave blank if you do not intend to change this");
                    String changeEmailAddress = tryCatchStrInputMethod(inputObj,"\nEnter new email address - Leave blank if you do not intend to change this" );

                    //Appending the new values to their respective attribute fields
                    finObject.projectContractor.setContractDetails(changeTelNumber, changeEmailAddress);
                    System.out.println("The contractor's details have been successfully changed");
                    System.out.println("New contact details added is/are: \n" + finObject.projectContractor);
                    isInnerLoop = false;
                }
                case "0" -> isInnerLoop = false;
                default -> System.out.println("Invalid action. Please enter again");
            }
        }
    }

    /**
     * Used to get a project from the Arraylist to be used in finalising
     * @param inputObj Scanner object
     * @return returns the object
     */
    private static projectObject getFinObject(Scanner inputObj) {
        int projectChoice;
        projectObject finObject;
        System.out.println("Here is a list of all projects:\n");
        FileHandlingClass.outputProjects(objectArrayList);
        projectChoice = FileHandlingClass.chooseFromList(inputObj, objectArrayList);
        finObject = objectArrayList.get(projectChoice - 1);
        return finObject;
    }

    /**
     * Menu interface to change a project
     */
    private static void changeProjectDMenu() {
        System.out.println("\nHere are the options to change project details");
        System.out.println("\n1. \t\tPress 'd' to change the due date of the project");
        System.out.println("\n2. \t\tPress 'a' to change the amount paid for the project");
        System.out.println("\n3. \t\tPress 'u' to update the contractor's contact details");
        System.out.println("\n4. \t\tPress '0' to return to the main menu");
    }

    /**
     * Main menu interface used for the program
     * @return user's choice
     */
    //Method 1 - Menu interface for the program
    static String poisedMenu() {
        String userMenuChoice;
        Scanner menuInput = new Scanner(System.in);

        System.out.println("Welcome to the Poised Company Program. \tPress any key to continue\n");
        menuInput.nextLine();

        System.out.println("Welcome to the menu option. Please select the menu functionality you would like to use\n");

        System.out.println("1. \tCreate a new project");
        System.out.println("2. \tView all projects");
        System.out.println("3. \tChange the details about an existing project (Date Due/Fee Paid/Contractor's Contact Details)");
        System.out.println("4. \tFinalise a project");
        System.out.println("5. \tView a newly created project (at runtime)");
        System.out.println("-1 \tPress -1 to exit program");

        userMenuChoice = menuInput.nextLine();
        return  userMenuChoice;
    }

    /**
     * Method to create a new project object
     * @return a new project Object
     * @throws IOException if there is an input error
     * @throws ParseException if it cannot parse given data
     */
    static projectObject createProject() throws IOException, ParseException {
        Scanner inputObj = new Scanner(System.in);
        String inBuilding, inProjName, cstName, projAddress;
        int ERFNumber,projNumber;
        float totalFee, amountPaid;
        Date deadline;
        personMainObject poisedCustomer;
        personMainObject poisedContractor;
        personMainObject poisedArchitect;

        //Output of menu
        createProjectMenu(inputObj);

        //String Input
        inProjName = tryCatchStrInputMethod(inputObj, "\nEnter Project Name. Enter 0 if user is unable to provide a Project name at the moment:");

        //Integer Input
        projNumber = tryCatchIntInputMethod(inputObj,"\nProject Number:" );

        //Date input
        System.out.println("\nDate due For this project:");
        int year, month, day;
        System.out.println();
        year = tryCatchIntInputMethod(inputObj,"Please enter the year num in the format 'yyyy':" );
        month = tryCatchIntInputMethod(inputObj,"Please enter the month num in the format 'mm':" );
        day = tryCatchIntInputMethod(inputObj,"Please enter the day num in the format 'dd':" );
        String dateString = year + "-" + month + "-" + day;
        deadline = dateFormatter.parse(dateString);

        //String Input
        inputObj.nextLine();
        inBuilding = tryCatchStrInputMethod(inputObj,"\nType of building:" );

        //String Input
        projAddress = tryCatchStrInputMethod(inputObj, "\nProject Address:");

        //Int Input
        ERFNumber = tryCatchIntInputMethod(inputObj,"\nERF Number");

        //Float Input
        totalFee = tryCatchFloatInputMethod(inputObj,"\nTotal fee for the project" );

        //Float Input
        amountPaid = tryCatchFloatInputMethod(inputObj,"\nFee paid by customer to date" );
        inputObj.nextLine();

        //Adding details related to the customer
        //String Input
        System.out.println("\nYou are about to input details for the customer.\nPress any key to continue");
        inputObj.nextLine();
        poisedCustomer = inputCustomerDetails();
        cstName = poisedCustomer.getName();

        //String Input
        System.out.println("\nYou are about to input details for the contractor.\nPress any key to continue");
        inputObj.nextLine();
        poisedContractor = inputContractorDetails();

        //String Input
        System.out.println("\nYou are about to input details for the architect.\nPress any key to continue");
        inputObj.nextLine();
        poisedArchitect = inputArchitectDetails();

        //Creating and writing data to a project object
        projectObject poisedProjectObject = writeProjectObject(inProjName, inBuilding, cstName, projAddress, ERFNumber, projNumber, totalFee, amountPaid, deadline, poisedCustomer, poisedContractor, poisedArchitect);
        FileHandlingClass.addObject(objectArrayList, poisedProjectObject);
        FileHandlingClass.saveToFile(filepath, poisedProjectObject.writeToFileStr(), true);
        System.out.println("\nWould you like to change details of the project? \nEnter 'y' to proceed. Enter any other key to return to the main menu");
        String changeDetails = inputObj.nextLine();

        if (changeDetails.equalsIgnoreCase("y")) {
            changeProjectDMenu();
            boolean isLoop = true;

            while (isLoop) {
                changeDetails = (inputObj.nextLine()).toLowerCase();
                switch (changeDetails) {
                    case "d" -> {
                        System.out.println("\nEnter the new due date for the project");
                        int changeYearDue = tryCatchIntInputMethod(inputObj,"\nEnter the new year due for the project" );
                        int changeMonthDue = tryCatchIntInputMethod(inputObj,"Enter the new month due for the project" );
                        int changeDayDue = tryCatchIntInputMethod(inputObj,"Enter the new day due for the project" );
                        String dateStr = changeYearDue + "-" + changeMonthDue + "-" + changeDayDue;
                        Date changeDateDue = dateFormatter.parse(dateStr);

                        //Appending the new date value to its object attribute
                        poisedProjectObject.setDateDue(changeDateDue);
                        //Replaces the element in the array with the updated one
                        objectArrayList.set(objectArrayList.size() - 1, poisedProjectObject);
                        System.out.println("\nDate value has been successfully changed");
                        System.out.println("New date due added is " + poisedProjectObject.dateDue);
                        isLoop = false;
                    }
                    case "a" -> {
                        float changeAmountPaid = tryCatchFloatInputMethod(inputObj,"Enter the new amount to add to the project" );

                        //Appending the new amount value to its object attribute
                        poisedProjectObject.setAmountPaid(changeAmountPaid);
                        objectArrayList.set(objectArrayList.size() - 1, poisedProjectObject);
                        System.out.println("\nAmount paid has been successfully changed");
                        System.out.println("New amount added is " + poisedProjectObject.amountPaid);
                        isLoop = false;
                    }
                    case "u" -> {
                        System.out.println("\nEnter the new telephone number and/or the new email address for the contractor");
                        String changeTelNumber = tryCatchStrInputMethod(inputObj,"\nEnter new telephone number - Leave blank if you do not intend to change this");
                        String changeEmailAddress = tryCatchStrInputMethod(inputObj, "Enter new email address - Leave blank if you do not intend to change this");

                        //Appending the new values to their respective attribute fields
                        poisedContractor.setContractDetails(changeTelNumber, changeEmailAddress);
                        objectArrayList.set(objectArrayList.size() - 1, poisedProjectObject);
                        System.out.println("The contractor's details have been successfully changed");
                        System.out.println("New contact details added is/are: " + poisedProjectObject.projectContractor);
                        isLoop = false;
                    }
                    case "0" -> isLoop = false;
                    default -> System.out.println("Invalid action. Please enter again");
                }
            }
        }
        return poisedProjectObject;
    }

    //Try catch method for string input

    /**
     * Try catch for String input method
     * @param inputObj Scanner object
     * @param inputParameter Message to the user
     * @return Validated String
     */
     static String tryCatchStrInputMethod(Scanner inputObj, String inputParameter) {
        boolean bError = true;
        String input = null;
        do {
            try {
                System.out.println(inputParameter);
                input = inputObj.nextLine();
                bError = false;
            }
            catch (Exception e) {
                System.out.println("Error. Please try again.");
            }
        } while (bError);
        return  input;
    }

    //Try catch method for integer input
    /**
     * Try catch for Integer input method
     * @param inputObj Scanner object
     * @param inputParameter Message to the user
     * @return Validated Integer
     */
     static int tryCatchIntInputMethod(Scanner inputObj, String inputParameter) {
        boolean bError = true;
        int input = 0;
        do {
            try {
                System.out.println(inputParameter);
                input = Integer.parseInt(inputObj.next());
                bError = false;
            }
            catch (Exception e) {
                System.out.println("Error, Incorrect Numerical Input. Please try again.");
                inputObj.reset();
            }
        } while (bError);
        return  input;
    }

    //Try catch method for float input
    /**
     * Try catch for float input method
     * @param inputObj Scanner object
     * @param inputParameter Message to the user
     * @return Validated Integer
     */
     static float tryCatchFloatInputMethod(Scanner inputObj, String inputParameter) {
        boolean bError = true;
        float input = 0;
        do {
            try {
                System.out.println(inputParameter);
                input = Float.parseFloat(inputObj.next());
                bError = false;
            }
            catch (Exception e) {
                System.out.println("Error, Incorrect Numerical Input. Please try again.");
                inputObj.reset();
            }
        } while (bError);
        return  input;
    }





    /* Refactored Methods */

    /**
     * Method to write entered data to create a new object
     * @param inProjName Project Name
     * @param inBuilding Building Type
     * @param cstName Customer Name
     * @param projAddress Project Address
     * @param ERFNumber ERF Number
     * @param projNumber Project Number
     * @param totalFee Total fee of the project
     * @param amountPaid Total fee paid by customer
     * @param deadline Date due
     * @param poisedCustomer Customer Object
     * @param poisedContractor Contractor Object
     * @param poisedArchitect Architect Object
     * @return a new project object
     */
    private static projectObject writeProjectObject(String inProjName, String inBuilding, String cstName, String projAddress, int ERFNumber, int projNumber, float totalFee, float amountPaid, Date deadline, personMainObject poisedCustomer, personMainObject poisedContractor, personMainObject poisedArchitect) {
        if (Objects.equals(inProjName, "0")) {
            inProjName = "Project " + cstName;
        }

        projectObject poisedProjectObject = new projectObject(
                projNumber, inProjName, inBuilding, projAddress, ERFNumber, totalFee, amountPaid, deadline, poisedCustomer, poisedContractor, poisedArchitect);
        //Output of project object using a method
        outputProjectMethod(poisedProjectObject);
        return poisedProjectObject;
    }

    /**
     * Menu interface when creating a new project
     * @param inputObj Scanner Object
     */
    private static void createProjectMenu(Scanner inputObj) {
        System.out.println("\nMenu option to create a new company project\nPress any key to continue");
        inputObj.nextLine();

        System.out.println("\nYou will be required to enter the following details: ");
        System.out.println("Project Name");
        System.out.println("Project Number");
        System.out.println("Type of building");
        System.out.println("ERF Number");
        System.out.println("Total fee for the project");
        System.out.println("Fee paid by customer to date");
        System.out.println("Project Deadline");
        System.out.println("Customer Details");
        System.out.println("Contractor Details");
        System.out.println("Architect Details");
        System.out.println("\nPress any key to continue");

        inputObj.nextLine();
    }

    /**
     * Output the project after creation
     * @param poisedProjectObject newly created project
     */
    private static void outputProjectMethod(projectObject poisedProjectObject) {
        System.out.println("\nYou have successfully created a new project.\nHere's how it looks: \n ");
        System.out.println(poisedProjectObject);
    }

                /* Methods to input details about a customer to their specific objects */

    /**
     * Method to input Customer details to Customer Object
     * @return Customer Object
     */
    static personMainObject inputCustomerDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String cstName, cstAddress, cstEmail, cstTelPhone;

        //Menu Output to user
        System.out.println("\nCustomer Details: \n\t\tName, \n\t\tAddress, \n\t\tEmail Address, \n\t\tTelephone Number...");

        System.out.println("\nCustomer Name:");
        cstName = inputObj.nextLine();
        System.out.println("\nCustomer Address:");
        cstAddress = inputObj.nextLine();
        System.out.println("\nCustomer Email Address:");
        cstEmail = inputObj.nextLine();
        System.out.println("\nCustomer Telephone Number:");
        cstTelPhone = inputObj.nextLine();

        personMainObject poisedCustomerObject = new personMainObject(cstName, cstAddress, cstTelPhone, cstEmail);
        System.out.println("Customer Details Saved");


        return poisedCustomerObject;

    }

    /**
     * Method to input Contractor details to Contractor Object
     * @return Contractor Object
     */
    static personMainObject inputContractorDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String contName, contAddress, contEmail, contTelPhone;

        System.out.println("\nContractor Details: \n\t\tName, \n\t\tAddress, \n\t\tEmail Address, \n\t\tTelephone Number...");

        System.out.println("Contractor Name:");
        contName = inputObj.nextLine();
        System.out.println("Contractor's Address:");
        contAddress = inputObj.nextLine();
        System.out.println("Contractor's Email Address:");
        contEmail = inputObj.nextLine();
        System.out.println("Contractor's Telephone Number:");
        contTelPhone = inputObj.nextLine();

        System.out.println("Contractor Details Saved Successfully");
        personMainObject contractorProgObject;
        contractorProgObject = new personMainObject(contName,contAddress,contTelPhone,contEmail);


        return contractorProgObject ;

    }

    /**
     * Method to input Architect details to an Architect Object
     * @return Architect Object
     */
    static personMainObject inputArchitectDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String archName, archAddress, archEmail, archTelPhone;

        System.out.println("\nArchitect Details: \n\t\tName, \n\t\tAddress, \n\t\tEmail Address, \n\t\tTelephone Number...");

        System.out.println("\nArchitect's Name:");
        archName = inputObj.nextLine();
        System.out.println("Architect's Address:");
        archAddress = inputObj.nextLine();
        System.out.println("Architect's Email Address:");
        archEmail = inputObj.nextLine();
        System.out.println("Architect's Telephone Number:");
        archTelPhone = inputObj.nextLine();

        System.out.println("Architect Details Saved");
        return new personMainObject(archName,archAddress,archTelPhone,archEmail);
    }


    //Methods - Finalising a created project

    /**
     * Used to finalise a project
     */
    static void finaliseProject() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String userChoice;
        boolean isLoop = true;
        String returnVal;
        projectObject finObject;
        int projectChoice;

        //Nested if statements to perform specific tasks

        System.out.println("Here is a list of all projects that are incomplete\n");
        FileHandlingClass.outputProjectNotComplete(objectArrayList);
        projectChoice = FileHandlingClass.chooseFromList(inputObj, objectArrayList);
        finObject = objectArrayList.get(projectChoice - 1);



        if (projectChoice != 0) {

            //Output of menu interface
            finaliseMenuInterface(inputObj);

            while (isLoop) {
                System.out.println("Enter choice here:");
                userChoice = inputObj.nextLine();

                //Choice to output the invoice from the project Object
                if (Objects.equals(userChoice, "1")) {
                    returnVal = finObject.generateInvoice();
                    System.out.println(returnVal);
                    isLoop = false;

                //Choice to enable the user to mark the project as finalised.
                } else if (Objects.equals(userChoice, "2")) {
                    markFinalised(finObject);
                    //Replaces the element in the array with the updated one
                    objectArrayList.set(projectChoice - 1, finObject);
                    isLoop = false;

                //Enabling the user to return to the menu
                } else if (Objects.equals(userChoice, "0")) {
                    isLoop = false;

                } else {
                    System.out.println("Invalid entry, Please try again");
                }
            }

        //Output to user if there has been no project created
        }else {
            System.out.println("\nThere is no project that has been created. \nPlease try again by returning to the main menu then choose option 1 to create a new project entry..\n");
        }
    }
    //Method - Menu to finalise project

    /**
     * Menu interface to finalise project
     * @param inputObj Scanner Object
     */
    private static void finaliseMenuInterface(Scanner inputObj) {
        System.out.println("\nMenu option to finalise a project");
        System.out.println("Press any key to continue");
        inputObj.nextLine();

        System.out.println("Please choose from the following actions");
        System.out.println("\n1. Press 1 to generate an invoice");
        System.out.println("2. Press 2 to Mark a project as Finalised.");
        System.out.println("3. Press 0 to return to the main menu");
    }
    //Method to mark a project as complete

    /**
     * Used within the finaliseProject() to mark a project as finalised
     * @param poisedProjectObject returns updated project object
     */
    private static void markFinalised(projectObject poisedProjectObject) {
        //Declarations
        Scanner inputObj = new Scanner(System.in);
        String userChoice;
        String returnValue;
        boolean isLoop = true;

        if (poisedProjectObject != null) {
            markFinalMenu(inputObj);

            while (isLoop) {
                System.out.println("Enter choice here:");
                userChoice = inputObj.nextLine();

                //Choice to output the invoice from the project Object
                if (Objects.equals(userChoice, "1")) {
                    returnValue = poisedProjectObject.setProjectComplete();
                    System.out.println(returnValue);
                    isLoop = false;

                    //Choice to enable the user to mark the project as finalised.
                } else if (Objects.equals(userChoice, "0")) {
                    isLoop = false;
                } else {
                    System.out.println("Invalid choice, Please try again");
                }
            }
        }
    }
    //Method to output the menu interface to mark a project as finalised
    private static void markFinalMenu(Scanner inputObj) {
        System.out.println("\nMenu interface to set project as complete");
        System.out.println("Press any key to continue");
        inputObj.nextLine();

        System.out.println("\nPlease choose from the following actions");
        System.out.println("\n1. Press 1 to mark Project as complete");
        System.out.println("2. Press 0 to return to the main menu.");
    }

    //Method to write to the file when the user exits

    /**
     * Method to write the contents of the project List to the file when the program is terminated by the user
     * @throws IOException throws exception if it fails to do file operation
     */
    static void exitMethod() throws IOException {

        //Truncating the existing file to get rid of the previous contents and write the new contents
        FileChannel.open(Paths.get(filepath), StandardOpenOption.WRITE).truncate(0).close();
        //Iterating through the arrayList
        for (projectObject writeObject : objectArrayList) {
            //Writing each line to the object - with the preferred formatting
            FileHandlingClass.saveToFile(filepath, writeObject.writeToFileStr(), true);
        }

    }
}
