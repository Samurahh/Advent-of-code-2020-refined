package com.spartaglobal.samurah.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class FileReader {
    /**
     * This FileReader class starts reading the file and create a LinkedList.
     */


    private final LinkedList<String> fileInput;
    private final BufferedReader fileReader;
    public FileReader(String path) throws FileNotFoundException {
        this.fileReader = new BufferedReader(new java.io.FileReader(path));
        fileInput = new LinkedList<>();
        readFile();
    }

    private void readFile(){
        fileReader.lines().filter(it -> !it.isBlank()).forEach(fileInput::add);
    }

    public LinkedList<String> getFileInput() {
        return fileInput;
    }
}
