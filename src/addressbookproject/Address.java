/**
 *
 * @author
 * 1	Ebrahim	Safdari     101326518
 * 2	Elham	Veisouei    101277407
 * 3	Safa	Aru         101331910
 *
 */

package addressbookproject;


public class Address {

    public String streetInfo1;
    public String streetInfo2;
    public String city;
    public String postalCode;
    public String province;
    public String country;

    public Address(String streetInfo1, String streetInfo2, String city, String postalCode, String province, String country) {
        this.streetInfo1 = streetInfo1;
        this.streetInfo2 = streetInfo2;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
        this.country = country;
    }

    public String getStreetInfo1() {
        return streetInfo1;
    }
    public String getStreetInfo2() {
        return streetInfo2;
    }
    public String getCity() {
        return city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getProvince() {
        return province;
    }
    public String getCountry() {
        return country;
    }

    public void setStreetInfo1(String streetInfo1) {
        this.streetInfo1 = streetInfo1;
    }

    public void setStreetInfo2(String streetInfo2) {
        this.streetInfo2 = streetInfo2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{" + "streetInfo1=" + streetInfo1 + ", streetInfo2=" + streetInfo2 + ", city=" + city + ", postalCode=" + postalCode + ", province=" + province + ", country=" + country + '}';
    }
}

