import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class const6174 {
    public static void main(String[] args) {
        int x = 0;
        int it = 0;
        boolean count = true;
        hello("You are welcome!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (count) {
            System.out.println("Введите четырехзначное число:");
            try {
                x = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Неправильный формат числа");
                x = 0;
            }
            if (x >= 100 && x < 10000) count = false;
            boolean input = checkSameDijit(x);
            if (input) {
                System.out.println("Хотя бы одна цифра числа должна отличаться от других!");
                count = true;
            }
        };
        while (x != 6174) {
            int a = sortDesc(x);
            int b = sortAsc(x);
            x = a - b;
            System.out.println(a + " - " + b + " = " + x + "\r\n");
            it++;
        }
        System.out.println(it + " раз(a)");

    }

    private static void hello(String s) {
        System.out.println(s);
    }

    public static int sortAsc(int x){
        int a = x / 1000;
        int b = (x - a * 1000) / 100;
        int c = (x - a * 1000 - b * 100) / 10;
        int d = (x - a * 1000 - b * 100 - c * 10);
        ArrayList<Integer> digit = new ArrayList<Integer>();
        digit.add(a);
        digit.add(b);
        digit.add(c);
        digit.add(d);
        Collections.sort(digit);
        int y = digit.get(0) * 1000 + digit.get(1) * 100 + digit.get(2) * 10 + digit.get(3);
        return y;
    }
    public static int sortDesc(int x){
        int a = x / 1000;
        int b = (x - a * 1000) / 100;
        int c = (x - a * 1000 - b * 100) / 10;
        int d = (x - a * 1000 - b * 100 - c * 10);
        ArrayList<Integer> digit = new ArrayList<Integer>();
        digit.add(a);
        digit.add(b);
        digit.add(c);
        digit.add(d);
        Collections.sort(digit);
        Collections.reverse(digit);
        int y = digit.get(0) * 1000 + digit.get(1) * 100 + digit.get(2) * 10 + digit.get(3);
        return y;
    }
    public static boolean checkSameDijit(int x){
        int a = x / 1000;
        int b = (x - a * 1000) / 100;
        int c = (x - a * 1000 - b * 100) / 10;
        int d = (x - a * 1000 - b * 100 - c * 10);
        if (a == b && a == c && a == d) return true;
        return false;
    }
}