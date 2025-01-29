import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        
    }

    public static boolean dichotomie(String[] liste, String name) {
        int start = 0 ;
        int end = liste.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (liste[mid].equals(name)) {
                return true;
            }
            if (liste[mid].compareTo(name) < 0) {
                end = mid - 1;
            }
            if (liste[mid].compareTo(name) > 0) {
                start = mid + 1;
            }

        }
        return false;
    }
}
