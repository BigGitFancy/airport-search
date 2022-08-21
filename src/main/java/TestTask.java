import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class TestTask {

    public TestTask(){

    }
    int id;
    Scanner scanner = new Scanner(System.in);
    int column = 1;
    HelperSearch helper = new HelperSearch();

    public void testTaskInit(int column){
        this.column = column;
        String path = "airports.csv";
        String line = "";
        BufferedReader br = null;
        boolean exitFlag = true;
        int i = 0;
        do {
            try {
                br = new BufferedReader(new FileReader(path));
                while ((line = br.readLine()) != null) {
                    String[] oneline = line.split(",");
                    oneline = helper.mergeProblemLine(oneline);
                    helper.buildGuideArray(oneline, this.column);
                }
                helper.ender(this.column);
                helper.buildABCArray();       //удалить вывод массива
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
    }
        //long
    public void searching(ArrayList<ArrayList<String>> outArray, String userLine){                     //пока только для букв, потом сделать проверку для чисел и для
        //long start = System.currentTimeMillis();
        helper.searching(outArray, userLine);
        //long finish = System.currentTimeMillis();
        //long elapsed = finish - start;
    }

    public int getMinID(){
        return id;
    }

    public int minID(ArrayList<ArrayList<String>> outArray){
        int min = Integer.MAX_VALUE;
        for (int x = 0; x < outArray.size(); x++){
            try {
                if ((Integer.parseInt(outArray.get(x).get(1)) < min) && (outArray.get(x).size() == 2)){
                    min = Integer.parseInt(outArray.get(x).get(1));
                    id = x;
                }
            }catch (NumberFormatException e){
                continue;
            }

        }
        return min;
    }
}
