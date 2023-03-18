/**
 *
 * @author
 * 1	Ebrahim	Safdari     101326518
 * 2	Elham	Veisouei    101277407
 * 3	Safa	Aru         101331910
 *          
 */
 
package addressbookproject;

import java.util.Arrays;


public class Contact {

    private final  int id;
    private final  String firstName;
    private final  String lastName;
    private final  String homePhone;
    private final  String workPhone;
    private final  Address homeAddress;
    private final  String email;
    private final MyDate birthday;
    private final  String notes;

    public Contact(int id, String firstName, String lastName, String homePhone, String workPhone, Address homeAddress, String email, MyDate birthday, String notes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.homeAddress = homeAddress;
        this.email = email;
        this.birthday = birthday;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public Address getHomeAddress() {
        return homeAddress;
    }
    public String getEmail() {
        return email;
    }
    public MyDate getBirthday() {
        return birthday;
    }
    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Contact{" + "firstName=" + firstName + ", lastName=" + lastName + ", homePhone=" + homePhone + ", workPhone=" +
                workPhone + ", homeAddress=" + homeAddress + ", email=" + email + ", birthday=" +
                birthday + ", notes=" + notes + '}';

    }
}
