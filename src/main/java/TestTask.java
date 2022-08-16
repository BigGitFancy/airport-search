import java.io.*;
import java.util.Scanner;

public class TestTask {

    public static void main (String[] args){
        String path = "airports.csv";
        String line = "";
        BufferedReader br = null;
        Boolean exitFlag = true;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {
                    String[] oneline = line.split(",");
                    System.out.println(oneline[0] + " " + oneline[1] + " " + oneline[2] + " " + oneline[3] + " " + oneline[4] + " " + oneline[5] + " " + oneline[6] + " " + oneline[7]);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден, введите правильный путь к файлу");
                System.out.println("Для выхода введите \"Выход\"");
                path = scanner.nextLine();
                if (path.equals("Выход")){
                    exitFlag=false;
                }
            } catch (IOException e) {
                System.out.println("Что-то случилось с файлом");
            } finally {
                if (br != null) {
                    try {
                        br.close();
                        exitFlag = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }while(exitFlag);
        scanner.close();
        System.out.println("END");

    }
}
