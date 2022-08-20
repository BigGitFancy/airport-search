import java.util.ArrayList;

public class HelperSearch {

    private ArrayList<ArrayList<String>> Aline = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> abcArray = new ArrayList<ArrayList<String>>();
    private String sameLine = "";
    private int count = 0;
    public int i = 0; //!!! сделать либо метод ГЕТ или удалить
    String str = "℧";
    BuildIDArray abc = new BuildIDArray();
    int column = 0;


    public HelperSearch(){

    }

    public void buildGuideArray(String[] line, int column){        //создает массив с первыми элементами в строках для упрощения поиска и сортировки
        Aline.add(i, new ArrayList<String>());
        if (sameLine.equals("")){       //можно потом вынести в отдельный privet метод
            sameLine = line[column];        //добавить параметр запуска
        }
        if (sameLine.equals(line[column]) && (i == count)){         //и сюда
            count++;
        }
        Aline.get(i).add(0, line[column]);



        Aline.get(i).add(1, Integer.toString(i+1));   //добавляем id строки, чтоб не потерялся при сортировке
        i++;

    }

    public void getAllALine(){
        for (int x = 0; x < i; x++){
            System.out.println(Aline.get(x).toString());
        }

    }

    public String getALine() {
        return Aline.get(i - 1).toString();
    }


    public String[] mergeProblemLine(String[] massstr){         //если в названии есть запятая, то делает массив строк правильным
        String[] fixedLine = new String[massstr.length-1];
        fixedLine = massstr;
        char gruzinI = 'İ';
        for (int i = 0; i < massstr.length; i++){
            long occCount = massstr[i].chars().filter(ch -> ch == '"').count();
            if (occCount == 1){
                massstr[i] = massstr[i] + massstr[i+1];
                for (int j = i+1; j < massstr.length-1; j++){
                    massstr[j] = massstr[j+1];
                }
                System.arraycopy(massstr, 0, fixedLine,0,massstr.length-1);
            }
            if (0 < fixedLine[i].lastIndexOf("[Duplicate]")){
                StringBuilder delKvadr = new StringBuilder(fixedLine[i]);
                delKvadr.delete(delKvadr.lastIndexOf("["),delKvadr.lastIndexOf("]")+2);
                fixedLine[i] = delKvadr.toString();
            }
            if (0 < fixedLine[i].lastIndexOf(gruzinI)){
                String bufString = "";
                bufString = fixedLine[i].substring(1,fixedLine[i].length());
                fixedLine[i] = '"' + str + bufString;
            }
        }
        return fixedLine;
    }

    public void ender(int column){      //заменяет в первой строке в запрашиваемой колонке первый элемент на спецзнак, если вся колонка одинаковоя
        this.column = column;       //возможно перенести в инициализацию
        if (i == count){
            Aline.get(0).add(0, str);
        }else {
            SortArray sortArray = new SortArray();
            sortArray.sortArray(Aline, 0,0, i-1);

        }
    }

    public void buildABCArray(){
        BuildIDArray buildIDArray = new BuildIDArray();
        buildIDArray.buildABCArray(Aline);
        buildIDArray.getABCArray();
    }

}
    /*String buf = Aline.get(0).get(0); Для сортировки ID
    int borderRight = 0;
    int borderLeft = 0;
            for (int x =1; x<i-1; x++){
        if (Aline.get(x).get(0).equals(buf)){
        borderRight++;
        }else{
        sortArray.sortArrayByID(Aline, 1, borderLeft, borderRight);
        buf = Aline.get(x+1).get(0);
        borderRight++;
        borderLeft = borderRight;
        }
        }
        sortArray.sortArray(Aline, 1, borderLeft, i-1);*/