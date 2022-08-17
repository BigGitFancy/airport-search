import java.io.*;
import java.util.Scanner;

public class TestTask {

    public static void main (String[] args){                //13 - Это параметр с консоли
        String path = "airports.csv";
        String line = "";
        BufferedReader br = null;
        Boolean exitFlag = true;
        HelperSearch helper = new HelperSearch();
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        do {
            try {
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {
                    String[] oneline = line.split(",");
                    oneline = helper.mergeProblemLine(oneline);
                    helper.initHelperSearch(oneline);
                    System.out.println(helper.getALine());
                }
                helper.ender(13);
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
