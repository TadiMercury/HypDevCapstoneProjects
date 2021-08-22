public class customerObject extends personMainObject {

    public customerObject(String personName, String personAddress, String personTelNumber, String personEmailAddress) {
        this.personName = personName;
        this.personAddress = personAddress;
        this.personTelNumber = personTelNumber;
        this.personEmailAddress = personEmailAddress;

    }

    //Getting the name of the contractor from the object
    public String getCustomerName() {

        return personName;
    }

    //Setting the details of the customer to different data values.
    public void setCustomerContactDetails(String personTelNumber, String personEmailAddress) {
        this.personTelNumber = personTelNumber;
        this.personEmailAddress = personEmailAddress;
    }


    public String toString() {
        String output = "Customer Name: " + personName;
        output += "\nCustomer Address: " + personAddress;
        output += "\nCustomer Tel Number: " + personTelNumber;
        output += "\nCustomer Email Address: " + personEmailAddress;

        return output;
    }

}
