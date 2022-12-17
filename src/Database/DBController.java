/**
 * Copyright (C) 2022 xyzuan, xyzscape LLC
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

package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

interface DBController {

    /**
     * DBWrite are default DBController interface method
     * which used to write @param data to @param dbName Files.
     * @param data String | The data which will be writen to the file
     * @param dbName String | The database name in local database
     * @throws IOException InputOutput Error
     */
    default void DBWrite(String data, String dbName) throws IOException {
        FileWriter w = new FileWriter(dbName, true);
        w.write(String.format("%s|\n", data));
        w.flush();
        w.close();
    }

    /**
     * DBRead are default DBController interface method which used to
     * read data from @param dbName
     * @param dbName String | The database which will be readed
     * @throws IOException InputOutput Error
     */
    default void DBRead(String dbName) throws IOException {
        FileReader readDB = new FileReader(dbName);
        BufferedReader read = new BufferedReader(readDB);
        String IO = read.readLine();
        System.out.print(dbName + ": ");
        do {
            StringTokenizer st = new StringTokenizer(IO, "|");
            System.out.print(st.nextToken() + ", ");
            IO = read.readLine();
        } while (IO != null);
        System.out.print("\n");
        read.close();
        readDB.close();
    }
}
