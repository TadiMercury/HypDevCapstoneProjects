/**
 * Class used to create people objects.
 * Can be used to create a Customer, A Contractor and An Architect
 * @author Tadiwa Magara
 */
public class personMainObject {

    //Attributes
    String personName;
    String personAddress;
    String personTelNumber;
    String personEmailAddress;

    //Constructor
    public personMainObject(String personName, String personAddress, String personTelNumber, String personEmailAddress) {
        this.personName = personName;
        this.personAddress = personAddress;
        this.personTelNumber = personTelNumber;
        this.personEmailAddress = personEmailAddress;
    }

                                            //General Methods to use for a customer object
    public String getName() {
        return personName;
    }

    /**
     * Used in the program to set the contact details of a person
     * @param personTelNumber Person telephone number
     * @param personEmailAddress Person email address
     */
    public void setContractDetails(String personTelNumber, String personEmailAddress) {
        this.personTelNumber = personTelNumber;
        this.personEmailAddress = personEmailAddress;
    }


                                            //To String Method
    public String toString() {
        String output = "Name: " + personName;
        output += "\nAddress: " + personAddress;
        output += "\nTelephone Number: " + personTelNumber;
        output += "\nEmail Address: " + personEmailAddress;
        return output;
    }
}
