public class architectObject extends personMainObject{

    //Constructor


    architectObject(String personName, String personAddress, String personTelNumber, String personEmailAddress) {
        this.personName = personName;
        this.personAddress = personAddress;
        this.personTelNumber = personTelNumber;
        this.personEmailAddress = personEmailAddress;

    }

    //Get the architect's name
    public String getArchitectName() {

        return personName;
    }


    public String toString() {
        String output = "Architect Name: " + personName;
        output += "\nArchitect Adresss: " + personAddress;
        output += "\nArchitect Telephone Number: " + personTelNumber;
        output += "\nArchitect Email Address: " + personEmailAddress;
        //output += "\nProject Name: " + ;

        return output;
    }

}
