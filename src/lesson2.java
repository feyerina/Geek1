import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lesson2 {
    public static void main(String[] args) {
        String user1 = "0";
        String userDefault = "X ";
        Boolean hodDefault = true;
        boolean gameDo = true;
        int value = 4;
        int countLine = 3;
        String hod = "11";
        int x = 1, y = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите размерность поля!");
        try {
            value = Integer.parseInt(reader.readLine());
            value++;
        } catch (IOException e) {
            e.printStackTrace();
            value = 4;
        }
        if (value <= 4) {
            value = 4;
            countLine = 3;
        } else if (value > 4 && value < 7){
            countLine = value - 1;
        } else if (value > 11) {
            value = 11;
            countLine = 5;
        } else countLine = 5;
        String field[][] = new String[value][value];
        initialField(field);
        writeField(field);
        System.out.println("Выберите - Х или 0?");
        try {
            user1 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!(user1.equals("X"))) user1 = "0 ";
        else {
            userDefault = "0 ";
            user1 = "X ";
        }
        System.out.println("Ваш символ - " + user1 + "");
        while (gameDo){
            System.out.println("Введите координаты Вашего хода!");
            try {
                hod = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String[] coords = hod.split(" ");
                y = Integer.parseInt(coords[0]);
                x = Integer.parseInt(coords[1]);
            } catch (Exception e){
                e.printStackTrace();
            }
            if (x > value - 1) x = value - 1;
            if (y > value - 1) y = value - 1;
            if (field[x][y].equals("* ")) {
                field[x][y] = user1;
            } else {
                System.out.println("Координата уже занята!");
                continue;
            }
            while (hodDefault){
                int a = rnd(1, value - 1);
                int b = rnd(1, value - 1);
                if (field[a][b].equals("* ")){
                    field[a][b] = userDefault;
                    hodDefault = false;
                }
            }
            hodDefault = true;
            writeField(field);
            boolean gameOver = checkGameOver(field, user1, userDefault, countLine);
            if (gameOver == true) gameDo = false;
        }
    }
    public static void initialField(String field[][]){
        int value = field.length;
        if (value > 10) field[0][0] = "X  ";
        else field[0][0] = "X ";
        for (int i = 0; i < field.length; i++){
            for (int k = 0; k < field[i].length; k++){
                if (i == 0 && k > 0) field[i][k] = String.valueOf(k + " ");
                else if (i > 0 && k == 0) {
                    if (value > 10 && i < 10) field[i][k] = String.valueOf(i + "  ");
                    else field[i][k] = String.valueOf(i + " ");
                }
                else if (i > 0 && k > 0) field[i][k] = "* ";
            }
        }
    }
    public static void writeField(String field[][]){
        for (int i = 0; i < field.length; i++){
            for (int k = 0; k < field[i].length; k++){
                if (k == field[i].length - 1) System.out.println(field[i][k] + " ");
                else System.out.print(field[i][k] + " ");
            }
        }
    }
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
    public static boolean fullField(String field[][]){
        for (int i = 0; i < field.length; i++){
            for (int k = 0; k < field[i].length; k++){
                if (field[i][k].equals("* ")) return false;
            }
        }
        return true;
    }
    public static boolean checkWinner(String field[][], String cymbol, int t){
        int a = 0;
        for (int i = 1; i < field.length; i++){
            for (int k = 1; k < field[i].length; k++){
                if (field[i][k].equals(cymbol)){
                    // проверяем горизонталь
                    a = 1;
                    if (k != field[i].length - 1) {
                        for (int x = k + 1; x < field[i].length; x++) {
                            if (field[i][x].equals(cymbol)) {
                                a++;
                                if (a == t) return true;
                            } else break;
                        }
                    }
                    // проверяем вертикаль
                    a = 1;
                    if (i != field.length - 1) {
                        for (int x = i + 1; x < field.length; x++) {
                            if (field[x][k].equals(cymbol)) {
                                a++;
                                if (a == t) return true;
                            } else break;
                        }
                    }
                    // проверяем диагональ \
                    a = 1;
                    if (k != field[i].length - 1 && i != field.length - 1) {
                        int x = i + 1, y = k + 1;
                        boolean goon = true;
                        while (goon){
                            if (field[x][y].equals(cymbol)) {
                                a++;
                                if (a == t) return true;
                            } else goon = false;
                            x++;
                            y++;
                            if (y == field[i].length || x == field.length ) goon = false;
                        }
                    }
                    // проверяем диагональ /
                    a = 1;
                    if (k != 1 && i != field.length - 1) {
                        int x = i + 1, y = k - 1;
                        boolean goon = true;
                        while (goon){
                            if (field[x][y].equals(cymbol)) {
                                a++;
                                if (a == t) return true;
                            } else goon = false;
                            x++;
                            y--;
                            if (y == 0 || x == field.length) goon = false;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkGameOver(String field[][], String user1, String userDefault, int t){
        if (checkWinner(field, user1, t) == true){
            System.out.println("Вы выиграли!");
            return true;
        }
        else if (checkWinner(field, userDefault, t) == true) {
            System.out.println("Вы проиграли!");
            return true;
        }
        else if (fullField(field) == true){
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

}
