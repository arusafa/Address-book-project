
/**
 *
 * @author
 * 1	Ebrahim	Safdari     101326518
 * 2	Elham	Veisouei    101277407
 * 3	Safa	Aru         101331910
 *          
 */
package addressbookproject;

public class MyDate {
    
    public  int day_info;
    public  int month_info;
    public  int year_info;
    
    public String [] months = new String [] {"January","February","March","April","May","July","June","August","September","October","November","December"};

    public MyDate(int year_info,int month_info,int day_info) {
        
        this.day_info = day_info;
        this.month_info = month_info;
        this.year_info = year_info;
    }

    public int getDay_info() {
        return day_info;
    }

    public void setDay_info(int day_info) {
        this.day_info = day_info;
    }

    public int getMonth_info() {
        return month_info;
    }
    
    public String findLongForm() {
        
        return months[month_info+1];
    }
    
    public String findShortForm() {
        
        String string = months[month_info -1].substring(0,3);
        return string;
    }

    public void setMonth_info(int month_info) {
        this.month_info = month_info;
    }

    public int getYear_info() {
        return year_info;
    }

    public void setYear_info(int year_info) {
        this.year_info = year_info;
    }


    @Override
    public String toString() {
        return "{" +year_info+"-"+month_info+"-"+day_info+"}";
    }
}
