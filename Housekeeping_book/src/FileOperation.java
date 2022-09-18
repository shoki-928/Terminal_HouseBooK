import java.io.*;
import java.util.*;
import java.nio.file.*;

/**
 全体的によく書けてると思う！指摘事項をつらつらと書いていくね
 ①メソッド名は動詞+名詞が一般的
 　今回作ってくれたファイルではcreateとかreadみたいな名前が多いけどcreateFileとかreadFileの方がよい。
 　可読性と保守性の面から、何をしてるかが一目でわかることが大事。
 　dataみたいなメソッド名は何してるかわかりにくいからやめよう。
 ②命名記法を統一しよう
 　file_name(スネークケース)もあればnewFile(キャメルケース)もある。
 　どれかに統一するとコードが読みやすい。
 　このへんは宗派の問題もあるから参考程度に。
 ③インデントがちょいちょい崩れてる
 　可読性に関わるからちゃんと見直そう。
 　インデントをハイライトしてくれる拡張機能なんかもあるから使うとよい。
 */

public class FileOperation {
    //日付ファイルの読み込み
    public List<String> read(String file_name){
        Path path = Paths.get("./" + file_name);
        // 行データを複数保持する前提に見受けられるからlines,もしくはrowsの方がよい。
        List<String> line = new ArrayList<String>();

        try {
            List<String> lines = Files.readAllLines(path);
            for (String i : lines){
                line.add(i);
            }
            return (line);

        } catch (IOException e) {
            e.printStackTrace();
            // ここでlineをreturnして大丈夫？エラーがあればちゃんと落とした方がいいと思う。
            // ユーザはそれが正規の動作と思ってそのまま使っちゃうんじゃないかな。
            // 考えがあってのことなら教えてほしい。
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
    // moneyもいいけどpriceの方が適した表現じゃないかな。名は体を表すってことで参考程度に。
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

    // メソッド名が'data'は何をしてるかわからなすぎる。動詞+名詞にしてくれ〜！
    //ファイルから読み込んだデータを2次元配列へ
    public String[][] data(List<String> line){
        List<String> spots = new ArrayList<String>();
        List<String> moneys = new ArrayList<String>();
        List<String> times = new ArrayList<String>();
        String[][] table = new String[line.size()][3];

        for (String contents : line){
            String[] content = contents.split(" ");
            // ここで渡してる数値はメンバー変数なり別ファイルなりに持つとよいのではないか。
            // private final int spotColumnNum = 0;とかしておけば今後金額のを列の場所を変更したいときにも簡単に対応できるよね。
            spots.add(content[0]);
            moneys.add(content[1]);
            times.add(content[2]);
            }
            // インデントしっかり〜！
            // 今回は読み流して問題ないが、forを使う前は処理対象のArrayListの中身があるかを見ておくとよい。
            // データの取得処理があるArrayListなら書いておくとよいかと。
            // if(line.size()>0){(後続の処理)}的な感じで。
            for (int i = 0; i < line.size(); i++) {
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
            // ただただユーザビリティの話なんだけど使用場所に半角スペース入ってるとエラー起きるよねこのプログラム。「Apple Store 45000」とか。
            // データ入力時にはカンマ区切りなどにしたほうが使いやすいのではないか。
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
            // インデント崩れはスコープあってる？みたいな疑問にも繋がるから注意しよう！
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

