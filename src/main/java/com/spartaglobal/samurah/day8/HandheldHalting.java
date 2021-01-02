package com.spartaglobal.samurah.day8;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The boot code is represented as a text file with one instruction per line of text.
 * Each instruction consists of an operation (acc, jmp, or nop) and an argument (a signed number like +4 or -20).
 * <p>
 * acc increases or decreases a single global value called the accumulator by the value given in the argument.
 * For example,
 * - acc +7 would increase the accumulator by 7. The accumulator starts at 0. After an acc instruction,
 * the instruction immediately below it is executed next.
 * - jmp jumps to a new instruction relative to itself. The next instruction to execute is found using the argument
 * as an offset from the jmp instruction; for example, jmp +2 would skip the next instruction, jmp +1 would continue
 * to the instruction immediately below it, and jmp -20 would cause the instruction 20 lines above to be executed next.
 * - nop stands for No OPeration - it does nothing. The instruction immediately below it is executed next.
 */

public class HandheldHalting {

    private static int LOADING_TIME = 300;

    private final BootCommand[] bootCode;
    private final HashMap<BootCommand, Boolean> executionLog;
    private int index;
    private int accumulator;

    public HandheldHalting(String[] inputArray) {
        this.bootCode = new BootCommand[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            bootCode[i] = new BootCommand(inputArray[i]);
        }
        this.executionLog = new HashMap<>();
    }

    private void interpretCommand(BootCommand command) {
        executionLog.computeIfPresent(command, (key, value) -> {
            throw new IllegalStateException("Infinite loop. Last value of accumulator: " + accumulator);
        });
        switch (command.getCommand()) {
            case "jmp": {
                index += command.getValue();
                break;
            }
            case "acc": {
                accumulator += command.getValue();
                index++;
                break;
            }
            case "nop": {
                index++;
                break;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + command.getCommand());
        }
        executionLog.put(command, true);
    }

    public void boot() {
        printLoadingMessage("Booting", LOADING_TIME);
        try {
            run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (reboot()) {
                System.out.println("System recovered.");
            }else{
                System.err.println("System corrupted! Unable to recover.");
            }
        }
        System.out.println("Successfully started!");
        System.out.println("Accumulator value at the exit of program: "+accumulator);
    }

    private void run() {
        reset();
        while (index < bootCode.length) {
            interpretCommand(bootCode[index]);
        }
    }

    private void reset() {
        index = 0;
        accumulator = 0;
        executionLog.clear();
    }

    private boolean reboot() {
        printLoadingMessage("Rebooting", LOADING_TIME);
        for (int i = 0; i < bootCode.length;) {
            BootCommand saveReplacedCommand = bootCode[i];
            BootCommand invertedBootCommand = null;
            if (saveReplacedCommand.getCommand().matches("nop")
                    && saveReplacedCommand.getValue() != 0) {
                invertedBootCommand = new BootCommand(BootCommand.toString("jmp", saveReplacedCommand.getValue()));
            } else if (saveReplacedCommand.getCommand().matches("jmp")) {
                invertedBootCommand = new BootCommand(BootCommand.toString("nop", saveReplacedCommand.getValue()));
            }
            if(invertedBootCommand!=null) {
                bootCode[i] = invertedBootCommand;
                try {
                    run();
                    return true;
                } catch (Exception e) {
                    bootCode[i] = saveReplacedCommand;
                    i++;
                }
            }else{
                i++;
            }
        }
        return false;
    }

    private void wait(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ignored) { }
    }

    private void printLoadingMessage(String message, int milliseconds){
        System.out.print(message);
        for (int i = 0; i < 3 ; i++) {
            wait(milliseconds);
            System.out.print(".");
        }
        System.out.println();
    }

    private static class BootCommand {
        private final String command;
        private final int value;

        public BootCommand(String input) {
            Pattern pattern = Pattern.compile("^(?<command>(jmp|acc|nop))\\s+(?<value>[-+]\\d+)\\s*?$");
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                this.command = matcher.group("command");
                this.value = Integer.parseInt(matcher.group("value"));
            } else {
                throw new IllegalArgumentException("Invalid command: " + input);
            }
        }

        public static String toString(String command, int value) {
            return String.format("%s %+d", command, value);
        }

        public String getCommand() {
            return command;
        }

        public int getValue() {
            return value;
        }

    }
}
