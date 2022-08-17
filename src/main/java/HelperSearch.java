import java.util.ArrayList;

public class HelperSearch {

    private ArrayList<ArrayList<String>> Aline = new ArrayList<ArrayList<String>>();
    private String sameLine = "";
    private int count = 0;
    public static int i = 0;
    String str = "℧";

    public HelperSearch(){

    }

    public void initHelperSearch(String[] line){        //создает массив с первыми элементами в строках для упрощения поиска и сортировки
        Aline.add(i, new ArrayList<String>());
        if (sameLine.equals("")){       //можно потом вынести в отдельный privet метод
            sameLine = line[13];        //добавить параметр запуска
        }
        if (sameLine.equals(line[13]) && (i == count)){         //и сюда
            count++;
        }
        for(int j = 0; j<line.length; j++){

            if (line[j].charAt(0) == '"'){
                Aline.get(i).add(j, line[j].substring(1,2));
            }
            else{
                if (line[j].charAt(0) == '-'){
                    Aline.get(i).add(j, line[j].substring(0,2));
            }
                else{
                    Aline.get(i).add(j, line[j].substring(0,1));
            }
            }

        }

        i++;

    }

    public String getALine() {

        return Aline.get(i - 1).toString();

    }

    public String[] mergeProblemLine(String[] massstr){         //если в названии есть запятая, то делает массив строк правильным
        String[] fixedLine = new String[massstr.length-1];
        fixedLine = massstr;
        for (int i = 0; i < massstr.length; i++){
            long occCount = massstr[i].chars().filter(ch -> ch == '"').count();
            if (occCount == 1){
                massstr[i] = massstr[i] + massstr[i+1];
                for (int j = i+1; j < massstr.length-1; j++){
                    massstr[j] = massstr[j+1];
                }
                System.arraycopy(massstr, 0, fixedLine,0,massstr.length-1);
            }
        }
        return fixedLine;
    }

    public void ender(int column){      //заменяет в первой строке в запрашиваемой колонке первый элемент на спецзнак, если вся колонка одинаковоя
        if (i == count){
            Aline.get(0).add(column, str);
        }
    }
}
