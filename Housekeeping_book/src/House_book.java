import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;



class House_book{
    public static void main(String[] args){
        Date nowDate = new Date();
        String simple_Format = new SimpleDateFormat("yyyy年MM月").format(nowDate);
        String Format = new SimpleDateFormat("yyyy年MM月dd日").format(nowDate);
        Scanner scanner = new Scanner(System.in);
        File file = new File("./" + simple_Format);
        FileOperation fo = new FileOperation();
        //同じ月のファイルがあるかをチェックし、あった場合は読み込み、なかった場合は新しい日付で作成して読み込み
        if (file.exists()){
            System.out.println("ファイルは存在します");
            fo.read(simple_Format);
        } else {
            System.out.println("日付を変更します。");
            fo.create(simple_Format);
        }
        //ファイルを読み込んでターミナル上に表示
        String[][] data = fo.data(fo.read(simple_Format));
        fo.display(data);

        while(true){
        //ターミナル上で使用場所と使用金額を入力
            System.out.println("使用場所と金額を空白を挟んで入力してください");
            System.out.println("例：○○○○ 1000");
            System.out.println("閉じる時は[exit + 自由なキー]を押してEnter");
        //入力データを"場所"と"金額"で分けて変数へ
            String spot = scanner.next();
            String money = scanner.next();
            String exit_command = "exit";
        
        //ターミナルからの指定のコマンドでウィンドウを閉じる
            if(spot.equals(exit_command)){
                System.out.println("ウィンドウを閉じます....");
                break;
            }
        //入力データを指定ファイルに書き込み
            fo.write(spot, money, Format, simple_Format);
            data = fo.data(fo.read(simple_Format));
        //読み込んだファイルをターミナル上に表示    
            fo.display(data);
        
        //読み込んだファイル内の合計値を出力
            fo.result(data);
        }
    }
}
