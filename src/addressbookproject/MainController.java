/**
 *
 * @author
 * 1	Ebrahim	Safdari     101326518
 * 2	Elham	Veisouei    101277407
 * 3	Safa	Aru         101331910
 *
 */
package addressbookproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MainController implements Initializable {

    @FXML
    private TextArea tfNotes;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfFLastName;
    @FXML
    private TextField tfHomePhone;
    @FXML
    private TextField tfWorkPhone;
    @FXML
    private TextField tfHomeAddress;
    @FXML
    private TextField tfEmail;
    @FXML
    private TableView<Contact> tvContact;
    @FXML
    private TableColumn<String, Contact> tblFirstName;
    @FXML
    private TableColumn<String, Contact> tblLastName;
    @FXML
    private TableColumn<String, Contact> tblHomePhone;
    @FXML
    private TableColumn<String, Contact> tblWorkPhone;
    @FXML
    private TableColumn<String, Address> tblstreetinfo1;
    @FXML
    private TableColumn<String, Address> tblstreetinfo2;
    @FXML
    private TableColumn<String, Address> tblcity;
    @FXML
    private TableColumn<String, Address> tblpostalcode;
    @FXML
    private TableColumn<String, Address> tblprovince;
    @FXML
    private TableColumn<String, Address> tblcountry;
    @FXML
    private TableColumn<String, Contact> tblemail;
    @FXML
    private TableColumn<String, MyDate> tblday;
    @FXML
    private TableColumn<String, MyDate> tblmonth;
    @FXML
    private TableColumn<String, MyDate> tblyear;
    @FXML
    private TableColumn<String, Contact> tblnotes;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnView;
    @FXML
    private TextField tfst1;
    @FXML
    private TextField tfst2;
    @FXML
    private TextField tfct;
    @FXML
    private TextField tfpc;
    @FXML
    private TextField tfpr;
    @FXML
    private TextField tfco;
    @FXML
    private TextField tfday;
    @FXML
    private TextField tfmonth;
    @FXML
    private TextField tfyear;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExit;

    @FXML
    private String handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnAdd) {

            BtnInsert();
        }
        else if (event.getSource() == btnEdit) {

            BtnUpdate();
        }
        else if (event.getSource() == btnDelete) {

            BtnDelete();
        }
        else if (event.getSource() == btnView) {

            BtnView();
        }
        else if (event.getSource()== btnExit){

            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }

        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showContact();
    }
    public Connection getConnection() {

        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?zeroDateTimeBehavior=convertToNull","root","");
            return conn;
        }
        catch (SQLException Ex){

            System.out.println("Error: " + Ex.getMessage());
        }
        return null;
    }
    public ObservableList<Contact> getContactList(){

        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM contact inner join address on contact.ID = address.ID inner join mydate on contact.ID = mydate.ID";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Contact contact;

            while (rs.next()) {
                Address address = new Address(rs.getString("streetInfo1"),
                        rs.getString("streetInfo2"),
                        rs.getString("city"),
                        rs.getString("postalcode"),
                        rs.getString("province"),
                        rs.getString("country")
                );
                MyDate myDate = new MyDate(rs.getInt("Day"),
                        rs.getInt("Month"),
                        rs.getInt("Year")
                );

                contact = new Contact(rs.getInt("ID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("homePhone"),
                        rs.getString("workPhone"),
                        address,
                        rs.getString("email"),
                        myDate,
                        rs.getString("notes") );

                contactList.add(contact);
            }
        }
        catch (SQLException ex){
            System.out.printf("Something is wrong1");
        }
        return contactList;
    }

    public void showContact() {

        ObservableList<Contact> List = getContactList();

        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblHomePhone.setCellValueFactory(new PropertyValueFactory<>("homePhone"));
        tblWorkPhone.setCellValueFactory(new PropertyValueFactory<>("workPhone"));

        tblstreetinfo1.setCellValueFactory(new PropertyValueFactory<>("streetInfo1"));
        tblstreetinfo2.setCellValueFactory(new PropertyValueFactory<>("streetInfo2"));
        tblcity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblpostalcode.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        tblprovince.setCellValueFactory(new PropertyValueFactory<>("province"));
        tblcountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("year"));

        tblday.setCellValueFactory(new PropertyValueFactory<>("day"));
        tblmonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        tblyear.setCellValueFactory(new PropertyValueFactory<>("year"));
        tblnotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        tvContact.setItems(List);
    }

    private void BtnInsert() {

        Connection conn = getConnection();
        String query1 = "SELECT Max(ID) AS ID FROM contact";
        int id=0;

        try {
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query1);
            rs.next();
            id = rs.getInt("ID") + 1;
        }
        catch(Exception ex){
            System.out.printf("Something is wrong2");
        }

        String  query = MessageFormat.format
                ("insert into contact (ID,FIRSTNAME, LASTNAME, HOMEPHONE, WORKPHONE, EMAIL, NOTES) VALUES ({0} ,''{1}'' ,''{2}'' ,''{3}'' ,''{4}'' ,''{5}'',''{6}'')",
                        id,tfFirstName.getText(), tfFLastName.getText(), tfHomePhone.getText(), tfWorkPhone.getText(), tfEmail.getText(),  tfNotes.getText());
        System.out.println(query);
        executeQuery(query);

        query=  MessageFormat.format
                ("insert into address (STREETINFO1, STREETINFO2, CITY, POSTALCODE, PROVINCE, COUNTRY, ID) VALUES (''{1}'',''{2}'',''{3}'',''{4}'',''{5}'',''{6}'',{0})",
                        tfst1.getText(), tfst2.getText(), tfct.getText(), tfpc.getText(), tfpr.getText(), tfco.getText() ,id);

        System.out.println(query);
        executeQuery(query);

        query=  MessageFormat.format
                ("insert into mydate (DAY, MONTH, YEAR, ID) VALUES (''{1}'',''{2}'',''{3}'',{0})",
                        tfday.getText(), tfmonth.getText(), tfyear.getText(), id);
        executeQuery(query);
        System.out.println(query);
        showContact();
    }

    private void BtnUpdate() {
        Contact contact = (Contact)tvContact.getSelectionModel().getSelectedItem();
        Address address = contact.getHomeAddress();
        MyDate myDate = contact.getBirthday();

        String  query = MessageFormat.format
                ("UPDATE contact  SET FirstName=''{1}'' , LastName=''{2}'' ,HomePhone=''{3}'' ,WorkPhone=''{4}''"
                        + ",StreetInfo1=''{5}'',StreetInfo2=''{6}'',City=''{7}'',PostalCode=''{8}'',Province=''{9}'',Country=''{10}''"
                        + "Email=''{11}'',Day=''{12}'',Month=''{13}'',Year=''{14}'' ,Notes=''{15}'' WHERE ID = {0}",
                        contact.getId(), tfFirstName.getText(), tfFLastName.getText(), tfHomePhone.getText(), tfWorkPhone.getText(),
                        tfst1.getText(),tfst2.getText(),tfct.getText(),tfpc.getText(), tfpr.getText(), tfco.getText(),
                        tfEmail.getText(),tfday.getText(),tfmonth.getText(),tfyear.getText(),
                        tfNotes.getText());

        System.out.println(query);

        executeQuery(query);
        showContact();
    }
    private void BtnView(){

       showContact();
    }
    private void BtnDelete() {
        Contact contact = (Contact)tvContact.getSelectionModel().getSelectedItem();

        String query;
        query = MessageFormat.format
                ("DELETE FROM contact WHERE id = {0}",
                        contact.getId());
        executeQuery(query);
        showContact();
    }
    private void executeQuery(String query) {

        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
