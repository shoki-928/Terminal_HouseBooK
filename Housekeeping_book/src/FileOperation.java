import java.io.*;
import java.util.*;
import java.nio.file.*;

public class FileOperation {
    //日付ファイルの読み込み
    public List<String> read(String file_name){
        Path path = Paths.get("./" + file_name);  
        List<String> line = new ArrayList<String>();    
        
        try {
            List<String> lines = Files.readAllLines(path);
            for (String i : lines){
                line.add(i);
            }
            return (line);   
            
        } catch (IOException e) {
            e.printStackTrace();
            return (line);
        }
    }

    //日付ファイルの作成
    public void create(String file_name){
        File newFile = new File("./" + file_name);
        try {
            if (newFile.createNewFile()) {
                System.out.println("新規ファイル作成成功");                
            } else {
                System.out.println("新規ファイル作成失敗");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    //日付ファイルへの書き込み
    public void write(String spot, String money, String date, String file_name){
        try {
            FileWriter file = new FileWriter("./" + file_name, true);
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));

            pw.println(spot + " " + money + "円" + " " + date);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ファイルから読み込んだデータを2次元配列へ
    public String[][] data(List<String> line){
        List<String> spots = new ArrayList<String>();
        List<String> moneys = new ArrayList<String>();
        List<String> times = new ArrayList<String>();
        String[][] table = new String[line.size()][3];
    
        for (String contents : line){
            String[] content = contents.split(" ");
            spots.add(content[0]);
            moneys.add(content[1]);
            times.add(content[2]);
            } for (int i = 0; i < line.size(); i++) {    
                table[i][0] = spots.get(i);
                table[i][1] = moneys.get(i);
                table[i][2] = times.get(i);
            }
            return table;
        }
    
    //コンソール上に綺麗に表示する
    public void display(String[][] data){
        System.out.println("-----------------------------------------");
        for(int i = 0; i < data.length; i++){
            String str = String.join(" ",data[i]);
            String[] list = str.split(" ");
            List<String> n_list = new ArrayList<String>();
            for(int j = 0; j < 3; j++){
                StringBuilder sb = new StringBuilder(list[j]);
                int len = list[j].length();
                len = 10 - len;
                for(int k = 0; k < len; k++){
                    sb.append(" ");
                    }
                n_list.add(sb.toString());
            }
        System.out.println(n_list);
        }
    System.out.println("-----------------------------------------");
    }

    //○○円をint型に変更後リストに格納。足して合計を表示。
    public void result(String[][] data){
        List<Integer> money_yen = new ArrayList<Integer>();
        for(int i = 0; i < data.length; i++){
            String str = String.join(" ",data[i]);
            String[] list = str.split(" ");
            String str_res = list[1].replace("円", "");
            int int_res = Integer.parseInt(str_res);
            money_yen.add(int_res);                    
        }
        System.out.println("合計       , " + money_yen.stream().mapToInt(Integer::intValue).sum() + "円");
        System.out.println("-----------------------------------------");
    }
}

