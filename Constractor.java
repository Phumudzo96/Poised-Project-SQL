
public class Constractor {
	// Listing variable attributes for the 'Constractor' class.
    String name;
    int number;
    String email;
    String address;

    // Writing a constructor method to create a constractor object.
    public Constractor(String name, int number, String email, String address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String DisplayConstractor() {

        String output = "Name: " + name;
        output += "\nNumber: " + number;
        output += "\nEmail Address: " + email;
        output += "\nPhysical Address: " + address;

        return output;
    }

}
