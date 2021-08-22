public class contractorObject extends personMainObject{

    //Constructor


    public contractorObject(String personName, String personAddress, String personTelNumber, String personEmailAddress) {
        this.personName = personName;
        this.personAddress = personAddress;
        this.personTelNumber = personTelNumber;
        this.personEmailAddress = personEmailAddress;

    }

    //Getting the name of the contractor from the object
    public String getContractorName() {

        return personName;
    }

    //Setting the details of the contractor to different data values.
    public void setContractorContractDetails(String personTelNumber, String personEmailAddress) {
        this.personTelNumber = personTelNumber;
        this.personEmailAddress = personEmailAddress;
    }


    public String toString() {
        String output = "Contractor Name: " + personName;
        output += "\nContractor Address: " + personAddress;
        output += "\nContractor Tel Number: " + personTelNumber;
        output += "\nContractor Email Address: " + personEmailAddress;
        //output += "\nProject Name: " + ;

        return output;
    }
}
