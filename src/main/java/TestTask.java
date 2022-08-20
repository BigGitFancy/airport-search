import java.io.*;
import java.util.Scanner;
import java.util.Timer;

public class TestTask {
    Scanner scanner = new Scanner(System.in);
    int column;
    public void testTaskInit(int column){
        this.column = column;
        String path = "airports.csv";
        String line = "";
        BufferedReader br = null;
        boolean exitFlag = true;
        HelperSearch helper = new HelperSearch();

        int i = 0;
        do {
            try {
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {
                    String[] oneline = line.split(",");
                    oneline = helper.mergeProblemLine(oneline);
                    helper.buildGuideArray(oneline, this.column);
                    //System.out.println(helper.getALine());
                }
                helper.ender(this.column);
                helper.buildABCArray();
                //helper.getAllALine();
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден, введите правильный путь к файлу");
                System.out.println("Для выхода введите \"Exit\"");
                path = scanner.nextLine();
                if (path.equals("Exit")){
                    System.exit(0);
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
        //scanner.close();
        //System.out.println("END");

    }

    public void searching(String userLine){                     //пока только для букв, потом сделать проверку для чисел и для
        long start = System.currentTimeMillis();
        char firstSymbol = userLine.charAt(0);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
    }
}
