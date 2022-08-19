import java.util.ArrayList;

public  class  SortArray {
    public void sortArray(ArrayList<ArrayList<String>> Aline, int column, int left, int right){
    int leftMark = left;
    int rightMark = right;
    String pivot = Aline.get((leftMark + rightMark)/2).get(column);
    int compareMark = 0; // если строка сравнение 0, то одинаково
    do{
        while (compareMark < Aline.get(leftMark).get(column).compareTo(pivot)){
            leftMark++;
        }
        while (compareMark > Aline.get(rightMark).get(column).compareTo(pivot)){
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
        sortArray(Aline, column, leftMark, right);
    }
    if (left < rightMark){
        sortArray(Aline, column, left, rightMark);
    }
}
}
