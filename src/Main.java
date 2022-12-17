/** Copyright (C) 2022 xyzuan, xyzscape LLC
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *      http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import Database.NumberController;
import Database.StringController;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    String[] inString = {};
    String[] inNumber = {};
    int inStringIndex = 0, inNumberIndex = 0;
    NumberController numCtl = new NumberController();
    StringController strCtl = new StringController();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.inputData();
        main.showData();
    }

    /**
     * inputData are used to write data to Local Database which call Controller function from DBController interfaces
     * @throws IOException InputOutput Error
     */
    void inputData() throws IOException {
        char backMenu;
        do {
            Scanner inputScan = new Scanner(System.in);
            System.out.print("Masukkan Data : ");
            String inTemp = inputScan.nextLine();

            if (!containsNumber(inTemp)) {
                inString = Arrays.copyOf(inString, inString.length + 1);
                inString[inStringIndex] = inTemp;
                strCtl.appendData(inString[inStringIndex]);
                inStringIndex++;
            } else {
                inNumber = Arrays.copyOf(inNumber, inNumber.length + 1);
                inNumber[inNumberIndex] = inTemp;
                numCtl.appendData(inNumber[inNumberIndex]);
                inNumberIndex++;
            }

            System.out.println("Masukkan data lagi? Y / N");
            backMenu = inputScan.next().charAt(0);
        } while (backMenu == 'Y');
    }

    /**
     * showData function used to show data from Local Database which controlled by DBController
     * @throws IOException InputOutput Error
     */
    void showData() throws IOException {
        System.out.println("========================");
        strCtl.readData();
        numCtl.readData();
        System.out.println("========================");
    }


    /**
     * containsNumber function used to check the @param String data are contains numeric or nothing
     * @param data String
     * @return boolean true = contains numeric
     */
    boolean containsNumber(@NotNull String data){
        char[] chars = data.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
}