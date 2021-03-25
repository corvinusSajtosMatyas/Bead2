package com.sajtos.bead2.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class FileHandler {
    public void write(String text) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(timestamp.toString() + ".txt");
            fileWriter.write(text);
            System.out.println("File " + timestamp.toString() + ".txt created.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }
    }

    public String read(String path) {
//        FIXME: not needed
        return null;
    }
}
