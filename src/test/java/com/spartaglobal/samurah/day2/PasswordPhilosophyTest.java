package com.spartaglobal.samurah.day2;

import com.spartaglobal.samurah.utilities.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

class PasswordPhilosophyTest {

    static PasswordPhilosophy passwordPhilosophy;

    @BeforeAll
    static void initClass(){
        try {
            FileReader fileReader = new FileReader("src/test/java/com/spartaglobal/samurah/day2/PasswordPhilosophyTest.txt");
            passwordPhilosophy = new PasswordPhilosophy(fileReader.getFileInput().toArray(new String[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkIfNumberOfInputsIsCorrect(){
        Assertions.assertEquals(17, Arrays.stream(passwordPhilosophy.getPasswordPolicies()).count());
    }

    @Test
    void firstPolicy(){
        Assertions.assertEquals(6, passwordPhilosophy.getTotalCompliantFirstPolicy());
    }

    @Test
    void secondPolicy(){
        Assertions.assertEquals(9, passwordPhilosophy.getTotalCompliantSecondPolicy());
    }

}