package com.spartaglobal.samurah.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class FileReader {
    /**
     * This FileReader class starts reading the file and creates a LinkedList.
     */


    private final LinkedList<String> fileInput;
    private final BufferedReader fileReader;

    public FileReader(String path) throws FileNotFoundException {
        this.fileReader = new BufferedReader(new java.io.FileReader(path));
        fileInput = new LinkedList<>();
    }

    private void readFile() {
        clear();
        fileReader.lines().filter(it -> !it.isBlank()).forEach(fileInput::add);
    }

    private void readFileRaw() {
        clear();
        fileReader.lines().forEach(fileInput::add);
    }

    private void clear(){
        if(fileInput.size()>0){
            fileInput.clear();
        }
    }

    public LinkedList<String> getRawFileInput() {
        readFileRaw();
        return fileInput;
    } //return file input as it is (including blank lines).

    public LinkedList<String> getFileInput() {
        readFile();
        return fileInput;
    } //returns file input without blank lines.
}
