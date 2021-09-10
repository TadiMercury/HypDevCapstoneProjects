import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/*
public class poisedMain {

    String projectMainObject, customerMainObject, contractorMainObject, architectMainObject;

    public static void main(String[] args) {
        //Global Variables
        //Main method of the program.
        // This is where all the methods will be called
        poisedMenu();
    }


                            //methods to use in main program

    //Method   1
    static void poisedMenu() {
        String userMenuChoice;
        Scanner menuInput = new Scanner(System.in);

        System.out.println("Welcome to the Poised Company Program. \tPress any key to continue\n");
        String nextLine = menuInput.nextLine();

        System.out.println("Welcome to the menu option. Please select the menu functionality you would like to use\n");

        System.out.println("1. \tCreate a new project");
        System.out.println("2. \tChange the due date of an existing project");
        System.out.println("3. \tChange the fee paid on an existing project");
        System.out.println("4. \tCreate new details for a new Customer/Contractor/Architect");
        System.out.println("5. \tUpdate a contractor's details");
        System.out.println("6. \tFinalise a project");
        System.out.println("-1 \tPress -1 to exit program");

        userMenuChoice = menuInput.nextLine();

        if (Objects.equals(userMenuChoice, "1")) {
            System.out.println("Create new Project");
            createProject();
            poisedMenu();
        } else if (Objects.equals(userMenuChoice, "2")) {
            System.out.println("Due date");
            poisedMenu();
        } else if (Objects.equals(userMenuChoice, "3")) {
            System.out.println("Fee paid");
            poisedMenu();
        }else if (Objects.equals(userMenuChoice, "4")) {
            System.out.println("Create new details for a new Customer/Contractor/Architect");
            addPersonDetails();
            poisedMenu();
        }else if (Objects.equals(userMenuChoice, "5")) {
            System.out.println("Update a contractor's Details");
            poisedMenu();
        }else if (Objects.equals(userMenuChoice, "6")) {
            System.out.println("Finalise a Project");
            poisedMenu();
        }else if (Objects.equals(userMenuChoice, "-1")) {
            System.out.println("User exit");
        }else {
            System.out.println("Invalid");
            poisedMenu();
        }
    }


    //Method 2
    static String createProject() {
        Scanner inputObj = new Scanner(System.in);
        String inProjName, inBuilding, contName, archName, cstName, projAddress, personDetails;
        int ERFNumber,projNumber;
        float totalFee, amountPaid;
        Date deadline;
        boolean isProjectName;


        System.out.println("\nMenu option to create a new company project\nPress any key to continue");
        String anyKey = inputObj.nextLine();

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

        anyKey = inputObj.nextLine();

        System.out.println("Enter Project Name. Enter 0 if Project name is unavailable:");
        inProjName = inputObj.nextLine();

        System.out.println("Project Number:");
        projNumber = Integer.parseInt(inputObj.nextLine());

        System.out.println("Type of building:");
        inBuilding = inputObj.nextLine();

        System.out.println("Project Address:");
        projAddress = inputObj.nextLine();

        System.out.println("ERF Number");
        ERFNumber = inputObj.nextInt();

        System.out.println("Total fee for the project");
        totalFee = inputObj.nextFloat();

        System.out.println("Fee paid by customer to date");
        amountPaid = inputObj.nextFloat();
        inputObj.nextLine();

        System.out.println("Customer Name:");
        cstName = inputObj.nextLine();

        System.out.println("Contractor Name:");
        contName = inputObj.nextLine();

        System.out.println("Architect's Name:");
        archName = inputObj.nextLine();


                //Creating and writing data to a project object
        if (Objects.equals(inProjName, "0")) {
            isProjectName = false;
            inProjName = "Project " + cstName;
        }

        projectObject object1 = new projectObject(
                    projNumber, inProjName, inBuilding,projAddress, ERFNumber, totalFee,amountPaid,3,cstName, contName, archName);

        System.out.println(object1);

        return object1.toString();
    }

    //Method 3
    static String inputCustomerDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String cstName, cstAddress, cstEmail, cstTelPhone;

        //Menu Output to user
        System.out.println("\nCustomer Details: \t\tName, Address, Email Address, Telephone Number...");

        System.out.println("Customer Name:");
        cstName = inputObj.nextLine();
        System.out.println("Customer Address:");
        cstAddress = inputObj.nextLine();
        System.out.println("Customer Email Address:");
        cstEmail = inputObj.nextLine();
        System.out.println("Customer Telephone Number:");
        cstTelPhone = inputObj.nextLine();

        customerObject customerProgObject = new customerObject(cstName, cstAddress, cstTelPhone, cstEmail);
        System.out.println("Customer Details Saved, Here's how it looks");
        System.out.println(customerProgObject);

        return customerProgObject.toString();

    }

    //Method 4
    static String inputContractorDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String contName, contAddress, contEmail, contTelPhone;

        System.out.println("\nContractor Details: \t\tName, Address, Email Address, Telephone Number...");

        System.out.println("Contractor Name:");
        contName = inputObj.nextLine();
        System.out.println("Contractor's Address:");
        contAddress = inputObj.nextLine();
        System.out.println("Contractor's Email Address:");
        contEmail = inputObj.nextLine();
        System.out.println("Contractor's Telephone Number:");
        contTelPhone = inputObj.nextLine();

        System.out.println("Contractor Details Saved Successfully");
        contractorObject contractorProgObject;
        contractorProgObject = new contractorObject(contName,contAddress,contTelPhone,contEmail);
        System.out.println(contractorProgObject);

        return contractorProgObject.toString();

    }

    //Method 5
    static String inputArchitectDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String archName, archAddress, archEmail, archTelPhone;

        System.out.println("\nArchitect Details: \t\tName, Address, Email Address, Telephone Number...");

        System.out.println("\nArchitect's Name:");
        archName = inputObj.nextLine();
        System.out.println("Architect's Address:");
        archAddress = inputObj.nextLine();
        System.out.println("Architect's Email Address:");
        archEmail = inputObj.nextLine();
        System.out.println("Architect's Telephone Number:");
        archTelPhone = inputObj.nextLine();

        System.out.println("Architect Details Saved");
        architectObject architectProgObject = new architectObject(archName,archAddress,archTelPhone,archEmail);
        System.out.println(architectProgObject);

        return  architectProgObject.toString();
    }
    //Method 6 - Method to choose which person object the user would like to create
    static void addPersonDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String userInput;
        userInput = "";

        System.out.println("Menu option to add person details. Please select the type of person to add to the system:\n");

        //Iteration of if statement until user enters to exit.
        while (!Objects.equals(userInput, "-1")) {
            System.out.println("1. '1' - \t\tCustomer Entry");
            System.out.println("2. '2' - \t\tContractor Entry");
            System.out.println("3. '3' - \t\tArchitect Entry");
            System.out.println("4. '0' - \t\tBack to main menu");

            userInput = inputObj.nextLine();

            if (Objects.equals(userInput, "1")) {
                System.out.println("User chose Customer entry");
                String customerProgObject = inputCustomerDetails();

                break;
            } else if (Objects.equals(userInput, "2")) {
                System.out.println("User chose Contractor Entry");
                String contractorProgObject = inputContractorDetails();
                break;
            } else if (Objects.equals(userInput, "3")) {
                System.out.println("User to chose Architect Entry");
                inputArchitectDetails();
                break;
            } else if (Objects.equals(userInput, "0")) {
                System.out.println("User chose to get back to main menu");
                break;
            } else {
                userInput = "-1";
            }

        }
    }
}

    //Method 7 - Updating a contractor's contact details
    /*static String updateContractorDetails() {
        //Variable Declarations
        Scanner inputObj = new Scanner(System.in);
        String contractorName;
        String userInput;
        userInput = "";

        System.out.println("Menu option to update a contractor's contact details");
        System.out.println("Press any key to continue");
        userInput = inputObj.nextLine();

        System.out.println("\nPlease input the contractor name you would like to amend");
        String contractorNameInput = inputObj.nextLine();

        System.out.println("\nPlease input the contractor telephone number to update");
        String contractorTelNumber = inputObj.nextLine();

        if (contractorNameInput ==  )

        userInput = inputObj.nextLine();


    }*/



