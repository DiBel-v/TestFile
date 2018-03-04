import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args){
        Pattern pattern;
        Matcher matcher;
        String line = null;
        String separator = File.separator;
        String startPath = separator + "D:"+separator+"test.txt";
        String endPath = separator + "D:"+separator+"result.txt";

        ArrayList<Country> countyList = new ArrayList<Country>();

        //задаем патерн строке, которую читаем из файла, для устойчивости к формату
        final String patternLine = "\\d+" + ";" + "\\d+" + ";" + "[\\D\\S]+";
        pattern = Pattern.compile(patternLine);
        File myresult = new File(endPath);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(startPath));
            while((line =bufferedReader.readLine())!=null){
                //подаем строку на проверку формата
                matcher=pattern.matcher(line);

                //заполняем коллекцию значениями строки
                if (matcher.matches()==true){
                    String[] lineSpl = line.split(";");
                    if ((Integer.parseInt(lineSpl[0])>Integer.MAX_VALUE) || (Integer.parseInt(lineSpl[1])>Integer.MAX_VALUE)){
                        throw new NumberFormatException("Number too high");
                    }
                    int userId = Integer.parseInt(lineSpl[0]);
                    int count = Integer.parseInt(lineSpl[1]);
                    String countryName = lineSpl[2];

                    countyList.add(new Country(userId,count,countryName));
                }

            }

            bufferedReader.close();
            //преобразуем исходные данные в нужный вид, конечный результат
            System.out.println(countyList);
            for(int i = 0; i<countyList.size();i++){
                for (int j =i+1;j<countyList.size();j++ ){
                    if(countyList.get(i).getnameCountry().equals(countyList.get(j).getnameCountry())){
                        if (countyList.get(i).getUser_id() != countyList.get(j).getUser_id()){
                            countyList.get(i).setCountUniq(1);
                            countyList.get(i).setSumCount(countyList.get(j).getCount());
                            countyList.remove(j);
                            j--;
                        }else{
                            countyList.get(i).setSumCount(countyList.get(j).getCount());
                            countyList.remove(j);
                            j--;
                        }

                    }
                }
            }



            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myresult)));
            writer.println(" Country;sum(count);count_uniq(user_id)\n");
            //отсортируем, использую интерфейс Comparable, и запишем в новый файл
            Collections.sort(countyList);
            for (Country c: countyList
                    ) {
                writer.println(String.valueOf(c));
                System.out.println(c);
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
