import java.util.ArrayList;

public class BuildIDArray {

    ArrayList<String> idBeginEndSymbol = new ArrayList<>();
    public ArrayList<ArrayList<String>> abcArray = new ArrayList<>();

    public void buildABCArray(ArrayList<ArrayList<String>> Aline) {    //создание массива с первыми символами
        int countLines = 0;
        abcArray.add(countLines, new ArrayList<String>());
        for (int x = 0; x < Aline.size(); x++) {
            if (isParsable(Aline.get(x).get(0))) {
                if (Aline.get(x).get(0).charAt(0) == '-'){
                    if (x == 0) {
                        abcArray.get(countLines).add(0, Aline.get(x).get(0).substring(0, 2));
                        abcArray.get(countLines).add(1, Integer.toString(x));
                    }
                    if (abcArray.get(countLines).get(0).equals(Aline.get(x).get(0).substring(0, 2))) {

                    } else {

                        abcArray.get(countLines).add(Integer.toString(x - 1));
                        countLines++;
                        abcArray.add(countLines, new ArrayList<String>());
                        abcArray.get(countLines).add(Aline.get(x).get(0).substring(0, 2));
                        abcArray.get(countLines).add(Integer.toString(x));
                    }
                }else {
                    if (x == 0) {
                        abcArray.get(countLines).add(0, Aline.get(x).get(0).substring(0, 1));
                        abcArray.get(countLines).add(1, Integer.toString(x));
                    }
                    if (abcArray.get(countLines).get(0).equals(Aline.get(x).get(0).substring(0, 1))) {

                    } else {

                        abcArray.get(countLines).add(Integer.toString(x - 1));
                        countLines++;
                        abcArray.add(countLines, new ArrayList<String>());
                        abcArray.get(countLines).add(Aline.get(x).get(0).substring(0, 1));
                        abcArray.get(countLines).add(Integer.toString(x));
                    }
                }

            } else {
                if (Aline.get(x).get(0).charAt(0) == '"') {
                    if (x == 0) {
                        abcArray.get(countLines).add(0, Aline.get(x).get(0).substring(1, 2));
                        abcArray.get(countLines).add(1, Integer.toString(x));
                    } else {
                        if (abcArray.get(countLines).get(0).equals(Aline.get(x).get(0).substring(1, 2))) {
                        } else {
                            abcArray.get(countLines).add(Integer.toString(x - 1));
                            countLines++;
                            abcArray.add(countLines, new ArrayList<String>());
                            abcArray.get(countLines).add(Aline.get(x).get(0).substring(1, 2));
                            abcArray.get(countLines).add(Integer.toString(x));
                        }
                    }
                } else {
                    if (x == 0) {
                        abcArray.get(countLines).add(0, Aline.get(x).get(0).substring(0, 1));
                        abcArray.get(countLines).add(1, Integer.toString(x));
                    }
                    if (abcArray.get(countLines).get(0).equals(Aline.get(x).get(0).substring(0, 1))) {

                    } else {

                        abcArray.get(countLines).add(Integer.toString(x - 1));
                        countLines++;
                        abcArray.add(countLines, new ArrayList<String>());
                        abcArray.get(countLines).add(Aline.get(x).get(0).substring(0, 1));
                        abcArray.get(countLines).add(Integer.toString(x));
                    }
                }
            }


        }
        abcArray.get(abcArray.size() - 1).add(Integer.toString(Aline.size() - 1));
    }



    public static boolean isParsable(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public void getABCArray(){
        for (int x = 0; x < abcArray.size(); x++){
            System.out.println(abcArray.get(x).toString());
        }
    }
}

