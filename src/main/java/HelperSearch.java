import java.util.ArrayList;
import java.util.Locale;

public class HelperSearch {

    private final ArrayList<ArrayList<String>> Aline = new ArrayList<ArrayList<String>>();
    private String sameLine = "";
    private int count = 0;
    public int numbersOfLines = 0;
    String str = "℧";
    BuildIDArray buildIDArray = new BuildIDArray();
    int column = 0;
    ArrayList<String> nothing = new ArrayList<>();


    public HelperSearch(){

    }

    public void searching(ArrayList<ArrayList<String>> outArray, String userLine){
        String firstSymbol = "";
        ArrayList<String> buf = null;
        nothing.add("!");
        int count = 0;
        ArrayList<Integer> beginEndArray = new ArrayList<>();
        if (!userLine.isEmpty()){
            if (userLine.charAt(0) == '-'){
                firstSymbol = userLine.substring(0,2);
            }else if (userLine.equals("İ"))
                {
                firstSymbol = "ℂ" + userLine.substring(1);
            }else{
                firstSymbol = userLine.substring(0,1);
            }
        }
        buildIDArray.searching(beginEndArray, firstSymbol);
        if (!beginEndArray.isEmpty()) {
            if (Aline.get(0).get(0).equals(str)) {          // проверка на спецзнак ℧, что означает колонка состоит из одинаковых элементов
                if(Aline.get(1).get(0).substring(1).toLowerCase(Locale.ROOT).startsWith(userLine.toLowerCase(Locale.ROOT))){
                    buf = Aline.get(0);
                    outArray.add(buf);
                }else {
                    outArray.add(nothing);
                }

            } else {
                for (int x = 0; x < beginEndArray.size(); x = x + 2) {
                    for (int j = beginEndArray.get(x); j <= beginEndArray.get(x + 1); j++) {
                        if (Aline.get(j).get(0).substring(1,2).equals("ℂ")){
                            if ((Aline.get(j).get(0).substring(1).toLowerCase(Locale.ROOT)).startsWith("ℂ" + userLine.substring(1).toLowerCase(Locale.ROOT))) {

                                buf = Aline.get(j);
                                outArray.add(buf);

                            }
                        }
                        if (Aline.get(j).get(0).substring(0,1).equals("\"")){
                            if ((Aline.get(j).get(0).substring(1).toLowerCase(Locale.ROOT)).startsWith(userLine.toLowerCase(Locale.ROOT))) {


                                    buf = Aline.get(j);
                                    outArray.add(buf);

                        }
                        }else {
                            if ((Aline.get(j).get(0)).startsWith(userLine)) {
                                buf = Aline.get(j);
                                outArray.add(buf);
                            }
                        }
                    }
                }
            }
        }else outArray.add(nothing);
    }

    public void buildGuideArray(String[] line, int column){        //создает массив с первыми элементами в строках для упрощения поиска и сортировки
        Aline.add(numbersOfLines, new ArrayList<String>());
        if (sameLine.equals("")){
            sameLine = line[column];
        }
        if (sameLine.equals(line[column]) && (numbersOfLines == count)){
            count++;
        }
        Aline.get(numbersOfLines).add(0, line[column]);



        Aline.get(numbersOfLines).add(1, Integer.toString(numbersOfLines +1));   //добавляем id строки, чтоб не потерялся при сортировке
        numbersOfLines++;

    }

    public void getAllALine(){
        for (int x = 0; x < numbersOfLines; x++){
            System.out.println(Aline.get(x).toString());
        }

    }

    public String getALine() {
        return Aline.get(numbersOfLines - 1).toString();
    }


    public String[] mergeProblemLine(String[] massstr){         //если в названии есть запятая, то делает массив строк правильным, если встретиться [Duplicate], то удалит, İ поменяет на знак ℂ, тк метод compareTo не читает ее
        String[] fixedLine = new String[massstr.length-1];
        fixedLine = massstr;
        char gruzinI = 'İ';
        String doplerGruzinI = "ℂ";
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
                bufString = fixedLine[i].substring(2,fixedLine[i].length());
                fixedLine[i] = '"' + doplerGruzinI + bufString;
            }
        }
        return fixedLine;
    }

    public void ender(int column){      //заменяет в первой строке в запрашиваемой колонке первый элемент на спецзнак, если вся колонка одинаковоя
        this.column = column;
        if (numbersOfLines == count){
            Aline.get(0).add(0, str);
        }else {
            SortArray sortArray = new SortArray();
            sortArray.sortArray(Aline, 0,0, numbersOfLines -1);

        }
    }

    public void buildABCArray(){
        buildIDArray.buildABCArray(Aline);

    }

}
