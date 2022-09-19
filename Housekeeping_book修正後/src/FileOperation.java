import java.util.*;
import java.io.*;
import java.nio.file.*;

public class FileOperation {
    // 使用場所(Spot)、金額(Money)、日付(Date)からStringとして読み込む
    public String readSpotFile(String fileName) {
        try {
            Path useSpotFilePath = Paths.get("../HouseBookDate/" + fileName + "/useSpot");
            String strUseSpotLine = Files.readString(useSpotFilePath);
            return (strUseSpotLine);
        } catch (IOException e) {
            System.out.println(e);
            return ("ファイル読み込み時にエラーが発生しました");
        }
    }

    public String readPriceFile(String fileName) {
        try {
            Path usePriceFilePath = Paths.get("../HouseBookDate/" + fileName + "/usePrice");
            String strUsePriceLine = Files.readString(usePriceFilePath);
            return (strUsePriceLine);
        } catch (IOException e) {
            System.out.println(e);
            return ("ファイル読み込み時にエラーが発生しました");
        }
    }

    public String readDateFile(String fileName) {
        try {
            Path useDateFilePath = Paths.get("../HouseBookDate/" + fileName + "/useDate");
            String strUseDateLine = Files.readString(useDateFilePath);
            return (strUseDateLine);
        } catch (IOException e) {
            System.out.println(e);
            return ("ファイル読み込み時にエラーが発生しました");
        }
    }

    // 使用場所(Spot)、金額(Price)、日付(Date)を保存するためのフォルダとファイルを作成
    public void createFile(String fileName, List<String> houseBookDateNames) {
        try {
            Path createDateFolderPath = Paths.get("../HouseBookDate/" + fileName);
            Files.createDirectories(createDateFolderPath);
            if (houseBookDateNames.size() > 0) {
                for (String houseBookDateName : houseBookDateNames) {
                    File createHouseBookDataFile = new File("../HouseBookDate/" + fileName + houseBookDateName);
                    if (createHouseBookDataFile.createNewFile()) {
                        System.out.println(houseBookDateName + "ファイル作成成功");
                    } else {
                        System.out.println(houseBookDateName + "ファイル作成失敗");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("新規フォルダ作成中にエラーが発生しました");
            System.out.println(e);
        }
    }

    // 引数で渡されたデータを各ファイルに保存する
    public void writeFile(String useSpot, String usePrice, String useDate, String fileName) {
        try {
            File spotDataFile = new File("../HouseBookDate/" + fileName + "/useSpot");
            File priceDataFile = new File("../HouseBookDate/" + fileName + "/usePrice");
            File dateDataFile = new File("../HouseBookDate/" + fileName + "/useDate");

            FileWriter spotFileWriter = new FileWriter(spotDataFile, true);
            FileWriter priceFileWriter = new FileWriter(priceDataFile, true);
            FileWriter dateFileWriter = new FileWriter(dateDataFile, true);

            spotFileWriter.write(useSpot + ",");
            spotFileWriter.close();

            priceFileWriter.write(usePrice + ",");
            priceFileWriter.close();

            dateFileWriter.write(useDate + ",");
            dateFileWriter.close();

        } catch (IOException e) {
            System.out.println("書き込み中にエラーが発生しました");
            System.out.println(e);
        }
    }

    // コンソール上に各データを列を揃えて表示する
    public void displayFile(String strUseSpotLine, String strUsePriceLine, String strUseDateLine) {
        String[] useSpotList = strUseSpotLine.split(",");
        String[] usePriceList = strUsePriceLine.split(",");
        String[] useDateList = strUseDateLine.split(",");
        List<Integer> intUsePriceList = new ArrayList<Integer>();

        System.out.println("-----------------------------------------");

        for (int i = 0; i < useDateList.length; i++) {
            StringBuilder useSpotSb = new StringBuilder(useSpotList[i]);
            StringBuilder usePriceSb = new StringBuilder(usePriceList[i]);
            StringBuilder useDateSb = new StringBuilder(useDateList[i]);

            int useSpotLen = 10 - useSpotList[i].length();
            int usePriceLen = 10 - usePriceList[i].length();
            int useDateLen = 10 - useDateList[i].length();

            for (int j = 0; j < useSpotLen; j++) {
                useSpotSb.append(" ");
            }
            for (int k = 0; k < usePriceLen; k++) {
                if (k == 0) {
                    usePriceSb.append("円");
                }
                usePriceSb.append(" ");
            }
            for (int l = 0; l < useDateLen; l++) {
                useDateSb.append(" ");
            }
            System.out.println(useSpotSb.toString() + usePriceSb.toString() + useDateSb.toString());
        }
        for (int i = 0; i < usePriceList.length; i++) {
            String strUsePrice = usePriceList[i].replace(" ", "");
            int intUsePrice = Integer.parseInt(strUsePrice);
            intUsePriceList.add(intUsePrice);
        }
        System.out.println("-----------------------------------------");
        System.out.println("合計      " + intUsePriceList.stream().mapToInt(Integer::intValue).sum() + "円");
        System.out.println("-----------------------------------------");
    }
}