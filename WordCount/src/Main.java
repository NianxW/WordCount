import java.io.*;
import java.util.Objects;

/**
 * Created by 念贤 on 2018/3/20.
 */
public class Main {
    public static int Charcount = 0;
    public static int Wordcount = 0;
    public static int Linecount = 0;

    public static BufferedReader bufferedReader;
    public static FileOutputStream fileOutputStream;
    public static String filename = " ";

    public Main(String filepath)throws FileNotFoundException{
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath))));
        this.fileOutputStream = new FileOutputStream (new File("output.txt"));
        this.filename = filepath;
    }

    // 统计字符数
    public static void Charcount() throws IOException {
        String s = " ";
        while (((s=bufferedReader.readLine())!= null) ){
            Charcount = Charcount + s.length();
            if(s.contains("\t")){
                Charcount --;
            }
        }
    }

    // 统计单词数
    public static void Wordcount() {
        String s = "";
        try {
            while ((s =bufferedReader.readLine()) != null) {
                String[] Wordstr = s.split(" \\t|\\n| |,");
                Wordcount=  Wordstr.length;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    //统计行数
    public static void Linecount() {
        String s = "";
        try {
            bufferedReader.readLine();
            while ((s =bufferedReader.readLine()) != null) {
                if(s.contains("\n")){
                    Linecount ++;
                }
            }
        }catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    //输出到output.txt
    public static void Output() throws IOException {
        Charcount();
        Wordcount();
        Linecount();
    }
    public static void main(String args[]) throws IOException {
        String filename = " ";
        if(args.length>=2){
            filename = args[args.length-1];
            Main file=new Main(filename);
            for(String str : args) {
                if (Objects.equals(str, "-c")) {
                    file.Charcount();
                    String result = "文件：" + filename + ",\n字符数：" + Charcount;
                    System.out.print("文件：" + filename + "\n字符数：" + Charcount);
                    fileOutputStream.write(result.getBytes());
                    break;
                } else if (Objects.equals(str, "-w")) {
                    file.Wordcount();
                    String result = "文件：" + filename + ",\n单词数：" + Wordcount;
                    System.out.print("文件：" + filename + "\n单词数：" + Wordcount);
                    fileOutputStream.write(result.getBytes());
                    break;
                } else if (Objects.equals(str, "-l")) {
                    file.Linecount();
                    String result = "文件：" + filename + ",\n行数：" + Linecount;
                    System.out.print("文件：" + filename + "\n行数：" + Linecount);
                    fileOutputStream.write(result.getBytes());
                    break;
                } else if (Objects.equals(str, "-o")) {
                    file.Output();
                    String result = "文件：" + filename + ",\n字符数：" + Charcount + ",\n单词数：" + Wordcount + ",\n行数：" + Linecount;
                    System.out.print("文件：" + filename + "\n字符数：" + Charcount + "\n单词数：" + Wordcount + "\n行数：" + Linecount);
                    fileOutputStream.write(result.getBytes());
                    break;
                } else {
                    System.out.println("指令错误。");
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedReader.close();
            }
        }
    }
}
