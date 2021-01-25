package cqyc.structure.Tree.set;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author cqyc
 * @Description: 文件相关操作
 * @date 2021/1/23
 */
public class FileOperation {
    //读取文件名称为filename中的内容，并将其中包含的所有词语放进words中
    public static boolean readFile(String fileName, ArrayList<String> words) {
        if (fileName == null || words == null) {
            System.out.println("filename is null or wrods is null");
            return false;
        }
        //文件读取
        Scanner scanner;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(inputStream), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Cannot open " + fileName);
            return false;
        }

        //简单分词，这个分词相对简陋，没有考虑很多文本处理情况中的特殊问题，这里做demo演示
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();
            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
            }
        }
        return true;
    }

    private static int firstCharacterIndex(String contents, int start) {
        for (int i = start; i < contents.length(); i++) {
            if (Character.isLetter(contents.charAt(i))) {
                return i;
            }
        }
        return contents.length();
    }
}
