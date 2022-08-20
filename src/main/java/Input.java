import java.util.Scanner;

public class Input {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        TestTask testTask = new TestTask();
        String inColumn = "9"; // !!! стоит заглушка, поменять на args[0], проставить -1 к результату и сделать проверку на что число подходит
        int inputColumn = 0;
        inputColumn = Integer.parseInt(inColumn);
        testTask.testTaskInit(inputColumn);

        boolean isNumber = true;
        while (isNumber) {
            try {
                inputColumn = Integer.parseInt(inColumn);
                testTask.testTaskInit(inputColumn);
                isNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Введено не число, введите номер колонки");
                inColumn = in.nextLine();
            }
        }
        /*String inputString = "";
        inputString= in.nextLine();
        while (!inputString.equals("!quit")) {
            if (!inputString.isEmpty()) {

            } else {
                System.out.println("Введена пустая строка");
                inputString = in.nextLine();
            }
        }*/
        in.close();
    }

}
