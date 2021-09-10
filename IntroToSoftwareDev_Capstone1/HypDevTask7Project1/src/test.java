import java.util.*;
import java.util.Date;
public class test {
    public static void main(String[] args) {
        customerObject sustain = new customerObject(
                "Tadi", "4 Fark Road", "012342245", "deez@gmail.com");
        Date testDate = new Date(2012, 9,21);

        System.out.println(testDate.getYear());
        //System.out.println(sustain.isEmpty());

    }
}
