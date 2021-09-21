public class personMainObject {

    //Attributes
    String personName;
    String personAddress;
    String personTelNumber;
    String personEmailAddress;

    //Constructor
    public personMainObject() {}

    public String toString() {
        String output = "Name: " + personName;
        output += "\nAddress: " + personAddress;
        output += "\nTelephone Number: " + personTelNumber;
        output += "\nEmail Address: " + personEmailAddress;
        return output;
    }
}
