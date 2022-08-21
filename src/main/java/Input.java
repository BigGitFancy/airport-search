import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        TestTask testTask = new TestTask();
        ArrayList<ArrayList<String>> outArray = new ArrayList<ArrayList<String>>();
        String inColumn = "2"; // !!! стоит заглушка, поменять на args[0], проставить -1 к результату и сделать проверку на что число подходит
        String path = "airports.csv";
        String line = "";
        BufferedReader br = null;
        int inputColumn = 0;
        int max = Integer.MAX_VALUE;
        long time = 0;
        inputColumn = Integer.parseInt(inColumn);

        boolean isNumber = true;
        while (isNumber) {
            try {
                inputColumn = Integer.parseInt(inColumn)-1;
                testTask.testTaskInit(inputColumn);
                isNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено не число, введите номер колонки");
                inColumn = in.nextLine();
            }
        }
        String inputString = "";
        System.out.println("Введите строку: ");
        inputString= in.nextLine();
        while (!inputString.equals("!quit")) {              //Начало вводного блока
            if(inputString.isEmpty()){                      //Если введенно ничего, то добавляются ковычки, чтоб найти пустые поля
                inputString = "\"";
            }
            outArray = new ArrayList<ArrayList<String>>();
            boolean readingEnd = false;
            long start = 0;
            int numberLine = 0;
            int count = 0;
            try {
                br = new BufferedReader(new FileReader(path));
                start = System.currentTimeMillis();
                testTask.searching(outArray, inputString);          //Поиск
                if (outArray.get(0).get(0).equals("!")) {           //Если ничего не нашлось

                } else{
                    if (outArray.get(0).get(0).equals("℧")) {       //Если вся колонка одинаковая, то вывод без сортировки

                    while ((line = br.readLine()) != null) {
                        count++;
                        String[] oneline = line.split(",");
                        System.out.print(outArray.get(0).get(1) + "[");
                        for (int i = 0; i < oneline.length; i++) {
                            System.out.print(oneline[i] + " ");
                        }
                        System.out.println("]");
                    }

                } else {
                    for (int x = 0; x < outArray.size(); x++) {
                        int id = 0;
                        int neededLine = testTask.minID(outArray);
                        id = testTask.getMinID();
                        while ((line = br.readLine()) != null) {
                            if (numberLine != neededLine - 1) {
                                numberLine++;
                                continue;
                            }

                            String[] oneline = line.split(",");
                            String merginLine = "[";
                            for (int i = 0; i < oneline.length; i++) {
                                merginLine = merginLine + oneline[i] + " ";
                            }
                            merginLine = merginLine + "]";
                            outArray.get(id).set(1, merginLine);
                            numberLine++;
                            count++;
                            break;
                        }
                    }
                    readingEnd = true;
                }
            }
            } catch (IOException e) {                 //при запуске программы уже есть проверка на наличие файла
                    System.out.println("Что-то случилось с файлом");
                } finally {
                    if (br != null || readingEnd) {     //если закончился файл или прочитали все найденые строки
                        try {
                            br.close();
                            long finish = System.currentTimeMillis();
                            long elapsed = finish - start;
                            if (outArray.get(0).get(0).equals("!")){
                                System.out.println("Ничего не найдено");
                            }else {
                                for (int i = 0; i < outArray.size(); i++){
                                    System.out.println(outArray.get(i));
                                }
                                System.out.println("Найдено строк: " + outArray.size() + " Время затраченное на поиск: " + elapsed + " мс");
                            }

                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            if (outArray.get(0).get(0).equals("!")){
                outArray.get(0).remove(0);
            }else {
                outArray = null;
            }
            System.out.println("Введите строку: ");
            inputString= in.nextLine();
            }

        in.close();
    }

}
