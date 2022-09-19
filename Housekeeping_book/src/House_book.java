import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/**
 こちらもよく書けているかと！気になった点は以下
 ①変数名は実態に則したものにすること
 　simple_Formatが何を保持しているかわからないと思う。fileNameとかすると保守時にわかりやすい。
 ②コメントのインデントは記載している処理と合わせる
 　宗派によるけど。自分ならこうするってのに直したから見てみて。
 ③不要な半角スペースなどは消すこと
 　Trailing SpacesってVSCode拡張機能がおすすめ。
 */

class House_book{
    public static void main(String[] args){
        Date nowDate = new Date();
        // 実態としてsimple_Formatはファイル名だからfileNameとかの方がいいのではないか。
        String simple_Format = new SimpleDateFormat("yyyy年MM月").format(nowDate);
        // これはお金を使った日だと思うからFormatDateよりかはuseDateとかの方がいいんでないか。
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

        // コメントは実際の処理とインデント合わせよう。宗派もあるけど。
        while(true){
            //ターミナル上で使用場所と使用金額を入力
            System.out.println("使用場所と金額を空白を挟んで入力してください");
            System.out.println("例：○○○○ 1000");
            System.out.println("閉じる時は[exit + 自由なキー]を押してEnter");
            // 変数へ「格納する」まで書くと親切。このままでもわかるが文章は動詞で終わる方がわかりやすい。
            //入力データを"場所"と"金額"で分けて変数へ
            String spot = scanner.next();
            String money = scanner.next();

            // 処理ブロックごとに改行するとコードが読みやすい。入力データを変数に格納するところとプログラムを終了する処理は別のはず。
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
