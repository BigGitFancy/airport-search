import java.util.ArrayList;

public  class  SortArray {              //реализация quicksort
    public void sortArray(ArrayList<ArrayList<String>> Aline, int columnArray, int left, int right){
    int leftMark = left;
    int rightMark = right;
    String pivot = Aline.get((leftMark + rightMark)/2).get(columnArray);
    int compareMark = 0; // если строка сравнение 0, то одинаково
    if (isParsable(pivot)){             //если встречается число, то сортируется по числовому порядку, если нет чисел, то сортируется в лексиграфическом порядке
        double pivot1 = Double.parseDouble(Aline.get((leftMark + rightMark)/2).get(columnArray));
        do{
            try {

                while (pivot1 > Double.parseDouble(Aline.get(leftMark).get(columnArray))) {
                    leftMark++;
                }
            }catch (NumberFormatException e){
                leftMark++;
            }
            try {
                while (pivot1 < Double.parseDouble(Aline.get(rightMark).get(columnArray))) {
                    rightMark--;
                }
            }catch (NumberFormatException e){
                rightMark--;
            }

            if (leftMark <= rightMark){
                if (leftMark < rightMark){
                    ArrayList<String> buf = Aline.get(leftMark);
                    Aline.set(leftMark, Aline.get(rightMark));
                    Aline.set(rightMark, buf);
                }
                leftMark++;
                rightMark--;
            }

        }while (leftMark <= rightMark);

        if (leftMark < right){
            sortArray(Aline, columnArray,leftMark, right);
        }
        if (left < rightMark){
            sortArray(Aline, columnArray, left, rightMark);
        }
        }else {
        do{
            while (compareMark > Aline.get(leftMark).get(columnArray).compareToIgnoreCase(pivot)){
                leftMark++;
            }
            while (compareMark < Aline.get(rightMark).get(columnArray).compareToIgnoreCase(pivot)){
                rightMark--;
            }

            if (leftMark <= rightMark){
                if (leftMark < rightMark){
                    ArrayList<String> buf = Aline.get(leftMark);
                    Aline.set(leftMark, Aline.get(rightMark));
                    Aline.set(rightMark, buf);
                }
                leftMark++;
                rightMark--;
            }

        }while (leftMark <= rightMark);

        if (leftMark < right){
            sortArray(Aline, columnArray,leftMark, right);
        }
        if (left < rightMark){
            sortArray(Aline, columnArray, left, rightMark);
        }
    }
}


    public static boolean isParsable(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}

