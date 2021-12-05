
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class used to create a project
 * Consists of 16 attributes, 10 methods and 1 constructor
 * @author Tadiwa Magara
 */
public class projectObject {

    //Attributes
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int projectNumber;
    String projectName;
    String buildType;
    String projectAddress;
    int numERF;
    float projectFee;
    float amountPaid;
    Date dateDue;
    private final String strDateDue;
    personMainObject projectCustomer;
    personMainObject projectContractor;
    personMainObject projectArchitect;
    char projectComplete = 'N';
    private Date dateFinished;
    private String strDateFinished;

    //Constructor
    public  projectObject(int projectNumber, String projectName, String buildType, String projectAddress, int numERF, float projectFee, float amountPaid, Date dateDue, personMainObject projectCustomer, personMainObject projectContractor, personMainObject projectArchitect) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.buildType = buildType;
        this.numERF = numERF;
        this.projectFee = projectFee;
        this.amountPaid = amountPaid;
        this.dateDue = dateDue;
        this.strDateDue = dateFormat.format(dateDue);
        this.projectCustomer = projectCustomer;
        this.projectContractor = projectContractor;
        this.projectArchitect = projectArchitect;
    }


    //Changing the due date of the project

    /**
     * Set date due of the project
     * @param dateDue Date class param
     */
    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    //Changing the projectComplete variable
    //Marking a project as finalised
    public String setProjectComplete() {
        this.projectComplete = 'Y';
        this.dateFinished = new Date();
        this.strDateFinished =  dateFormat.format(dateFinished);
        return "Details have been amended.\nThe project has been marked as complete.";
    }

    //Changing the value of the fee paid to date
    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    //Generating an invoice
    public String generateInvoice() {
        float amountOwing = (projectFee - amountPaid);
        String invoice;

        if (amountPaid == projectFee) {
            invoice = "\nThe customer has paid the full amount. Invoice Unavailable";
        }else {
            invoice = "\nInvoice Statement for \t" + projectName + "\n";
            invoice += "\nCustomer Name: \t\t\t\t " + projectCustomer.personName;
            invoice += "\nCustomer Tel Number: \t\t " + projectCustomer.personTelNumber;
            invoice += "\nCustomer Email Address: \t " + projectCustomer.personEmailAddress + "\n";
            invoice += "\nProject Fee: \t\t\t\t R" + projectFee;
            invoice += "\nAmount Paid to date: \t\t R" + amountPaid;
            invoice += "\nDifference yet to be paid: \t R" + amountOwing;
        }
        return invoice;
    }

    //Output of data of object
    public  String toString() {

        String output = "\nProject Name: " + projectName;
        output += "\nProject Number: " + projectNumber;
        output += "\nCustomer's Name: " + projectCustomer.getName() ;
        output += "\nProject Contractor: " + projectContractor.getName();
        output += "\nProject Architect: " + projectArchitect.getName();
        output += "\nProject Address: " + projectAddress;
        output += "\nType of Building?: " + buildType;
        output += "\nERF Number: " + numERF;
        output += "\nTotal Project Fee: " + projectFee;
        output += "\nAmount Paid to date: " + amountPaid;
        output += "\nDate due for project " + projectNumber + ": " + dateDue;
        output += "\nProject Complete?: " + projectComplete;
        output += "\nDate Completed?: " + dateFinished;
        return output;
    }

    private String customerWriteToString() {
        String concatenate = " && ";
        String split = "|";
        return this.projectCustomer.personName + concatenate
                + this.projectCustomer.personAddress + concatenate
                + this.projectCustomer.personTelNumber + concatenate
                + this.projectCustomer.personEmailAddress
                + split;
    }

    private String contractorWriteToString() {
        String concatenate = " && ";
        String split = "|";

        return this.projectContractor.personName + concatenate
                + this.projectContractor.personAddress + concatenate
                + this.projectContractor.personTelNumber + concatenate
                + this.projectContractor.personEmailAddress
                + split;
    }


    private String architectWriteToString() {
        String concatenate = " && ";
        String split = "|";
        return this.projectArchitect.personName + concatenate
                + this.projectArchitect.personAddress + concatenate
                + this.projectArchitect.personTelNumber + concatenate
                + this.projectArchitect.personEmailAddress
                + split;
    }

    /**
     * Method to concatenate the project data into a line String to be written to a file
     * @return String
     */
    public String writeToFileStr() {
        String split = "|";
        String output = this.projectName + split
                + this.projectNumber + split
                + customerWriteToString()
                + contractorWriteToString()
                + architectWriteToString()
                + this.projectAddress + split
                + this.buildType + split
                + this.numERF + split
                + this.projectFee + split
                + this.amountPaid + split
                + this.strDateDue + split;

        if (this.projectComplete != 'N') {
            output += this.projectComplete + split;
            output += this.strDateFinished;
        }
        return output;
    }

    /**
     * Used to set a project as complete when reading from a file
     * @param dateFinished Date class param
     */
    public void setProjectCompleteFromFile(Date dateFinished) {
        this.projectComplete = 'Y';
        this.dateFinished = dateFinished;
    }
}
