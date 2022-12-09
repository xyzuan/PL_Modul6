import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tugas1 {

    String[] inString = {};
    int[] inNumber = {};

    int indexString = 0, indexNumber = 0;

    String tmpInput;
    char entry;
    boolean next = true;

    public static void main(String[] args) {
        Tugas1 main = new Tugas1();
        try {
            main.menu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void menu() throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println("Data Terkini : ");
        try {
            readData("number");
            readData("string");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Input data ? y / n");
        char pilih = input.next().charAt(0);
        if (pilih == 'y'){
            do {

                System.out.print("\nData : ");
                tmpInput = input.next();

                if(containsNum(tmpInput)){
                    inNumber = Arrays.copyOf(inNumber, inNumber.length+1);
                    inNumber[indexNumber] = Integer.parseInt(tmpInput);
                    indexNumber++;
                    appendNum(inNumber);
                } else {
                    inString = Arrays.copyOf(inString, inString.length+1);
                    inString[indexString] = tmpInput;
                    indexString++;
                    appendString(inString);
                }

                System.out.print("Lagi ? ");
                entry = input.next().charAt(0);
                if (entry != 'y'){
                    next = false;
                }
            } while (next);
        }
    }

   static boolean containsNum(String text){
        char[] chars = text.toCharArray();
        for (char c : chars){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }


    static void appendNum (int[]x) throws IOException {

        FileWriter w = new FileWriter("dbNumber.db", true);

        for (int j : x) {
            w.write(String.format("%s|\n", j));
        }
        w.flush();
        w.close();
    }

    static void appendString (String[]x) throws IOException {

        FileWriter w = new FileWriter("dbString.db", true);

        for (String s : x) {
            w.write(String.format("%s|\n", s));
        }
        w.flush();
        w.close();
    }

    static void readData(String params) throws Exception {
        switch (params){
            case "number" -> {
                try {
                    FileReader readNumber = new FileReader("dbNumber.db");
                    BufferedReader buffNumber = new BufferedReader(readNumber);
                    String IOnum = buffNumber.readLine();
                    System.out.print("inNumber = ");
                    do {
                        StringTokenizer st = new StringTokenizer(IOnum, "|");
                        System.out.print(st.nextToken() + " ");
                        IOnum = buffNumber.readLine();
                    } while (IOnum != null);
                    System.out.print("\n");
                    buffNumber.close();
                    readNumber.close();
                } catch (FileNotFoundException fileMissing) {
                    System.err.println("Database tidak ditemukan");
                }
            }
            case "string" -> {
                try {
                    FileReader readString = new FileReader("dbString.db");
                    BufferedReader buffString = new BufferedReader(readString);
                    String IOstr = buffString.readLine();
                    System.out.print("inString = ");
                    do {
                        StringTokenizer st = new StringTokenizer(IOstr, "|");
                        System.out.print(st.nextToken() + " ");
                        IOstr = buffString.readLine();
                    } while (IOstr != null);
                    System.out.print("\n");
                    buffString.close();
                    readString.close();
                } catch (FileNotFoundException fileMissing) {
                    System.err.println("Database tidak ditemukan");
                }
            }
        }
    }
}
