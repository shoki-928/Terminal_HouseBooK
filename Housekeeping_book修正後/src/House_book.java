import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class House_book {
    public static void main(String[] args) {
        Date nowDate = new Date();
        FileOperation fo = new FileOperation();
        List<String> houseBookDataNames = new ArrayList<String>(Arrays.asList("/useSpot", "/usePrice", "/useDate"));
        String fileName = new SimpleDateFormat("yyyy年MM月").format(nowDate);
        String useDate = new SimpleDateFormat("yyyy年MM月dd日").format(nowDate);
        Scanner scan = new Scanner(System.in);
        File dateFile = new File("../HouseBookDate/" + fileName);

        String strUseSpotLine;
        String strUsePriceLine;
        String strUseDateLine;

        // 同じ月のファイルがあるかチェックし、あった場合は読み込みコンソール上へ表示、なかった場合は新しい日付で作成して読み込み
        if (dateFile.exists()) {
            strUseSpotLine = fo.readSpotFile(fileName);
            strUsePriceLine = fo.readPriceFile(fileName);
            strUseDateLine = fo.readDateFile(fileName);
            fo.displayFile(strUseSpotLine, strUsePriceLine, strUseDateLine);
        } else {
            fo.createFile(fileName, houseBookDataNames);
            System.out.println("月が変わったためファイルを新規作成します");
        }

        while (true) {
            // ターミナル上で使用場所と金額をカンマ区切りで入力
            System.out.println("使用場所と金額を[,]で区切り入力して下さい");
            System.out.println("例:○○○○,1980");
            System.out.println("閉じるときは[exit,好きなキー]を押してEnter");

            // 入力データを"場所(useSpot)"と"金額(usePrice)"に分けて変数へ
            String useSpotPrice = scan.next();
            Scanner scanSpotPrice = new Scanner(useSpotPrice);
            scanSpotPrice.useDelimiter(",");

            String useSpot = scanSpotPrice.next();
            String usePrice = scanSpotPrice.next();

            // ターミナルから指定のコマンドでプログラムを終了する
            String exit_command = "exit";
            if (useSpot.equals(exit_command)) {
                System.out.println("システムを終了します...");
                break;
            }

            // 入力データを指定ファイルに書き込み
            fo.writeFile(useSpot, usePrice, useDate, fileName);

            // 読み込んだ各データを各変数へ格納する
            strUseSpotLine = fo.readSpotFile(fileName);
            strUsePriceLine = fo.readPriceFile(fileName);
            strUseDateLine = fo.readDateFile(fileName);

            // 読み込んだファイルをコンソール上へ表示する
            fo.displayFile(strUseSpotLine, strUsePriceLine, strUseDateLine);
        }
    }
}