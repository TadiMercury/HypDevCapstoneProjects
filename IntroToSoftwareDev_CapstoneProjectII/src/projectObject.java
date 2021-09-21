import java.util.Date;

public class projectObject {

    //Attributes
    int projectNumber;
    String projectName;
    String buildType;
    String projectAddress;
    int numERF;
    float projectFee;
    float amountPaid;
    Date dateDue;
    customerObject projectCustomer;
    contractorObject projectContractor;
    architectObject projectArchitect;
    char projectComplete = 'N';
    private Date dateFinished;

    //Constructor
    public  projectObject(int projectNumber, String projectName, String buildType, String projectAddress, int numERF, float projectFee, float amountPaid, Date dateDue, customerObject projectCustomer, contractorObject projectContractor, architectObject projectArchitect) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.buildType = buildType;
        this.numERF = numERF;
        this.projectFee = projectFee;
        this.amountPaid = amountPaid;
        this.dateDue = dateDue;
        this.projectCustomer = projectCustomer;
        this.projectContractor = projectContractor;
        this.projectArchitect = projectArchitect;
    }

    //Changing the due date of the project
    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    //Changing the projectComplete variable
    //Marking a project as finalised
    public String setProjectComplete() {
        this.projectComplete = 'Y';
        this.dateFinished = new Date();
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
        output += "\nCustomer's Name: " + projectCustomer.getCustomerName() ;
        output += "\nProject Contractor: " + projectContractor.getContractorName();
        output += "\nProject Architect: " + projectArchitect.getArchitectName();
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
}
