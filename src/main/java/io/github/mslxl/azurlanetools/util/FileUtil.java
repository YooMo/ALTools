package io.github.mslxl.azurlanetools.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtil {
    public static List<File> listAllFile(File directory){
        if (!directory.exists()) {
            throw new RuntimeException(new FileNotFoundException());
        }
        ArrayList<File> list = new ArrayList<>();
        File files[] = directory.listFiles();
        for (File file : files) {
            if (file.isFile()){
                list.add(file);
            }else {
                list.addAll(listAllFile(file));
            }
        }
        return list;
    }

    public static List<String> readLines(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        List<String> list = readLines(inputStream);
        inputStream.close();
        return list;
    }

    public static List<String> readLines(InputStream inputStream) throws IOException{
        return readLines(new InputStreamReader(inputStream));
    }

    public static List<String> readLines(Reader reader) throws IOException{
        return readLines(new BufferedReader(reader));
    }
    public static List<String> readLines(BufferedReader bufferedReader) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String line;
        while((line = bufferedReader.readLine())!=null){
            list.add(line);
        }
        return list;
    }
    public static void write(String text,File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        write(text,outputStream);
        outputStream.close();
    }
    public static void write(String text,OutputStream outputStream) throws IOException {
        write(text,new OutputStreamWriter(outputStream));
    }
    public static void write(String text,Writer writer) throws IOException {
        write(text,new BufferedWriter(writer));
    }
    public static void write(String text,BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(text);
        bufferedWriter.flush();
    }

    public static String linesList2String(List<String> lines){
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()){
            builder.append(iterator.next());
            if (iterator.hasNext()){
                builder.append('\n');
            }
        }
        return builder.toString();
    }
}
