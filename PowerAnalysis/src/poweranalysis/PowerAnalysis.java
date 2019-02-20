/*
 * Licensed in Public Domain by IoTica Research Lab.
 * Foundation University Islamabad, Rawalpindi Campus (FURC)
 * Rawalpindi, Pakistan
 */
package poweranalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author Umar Mahmud VIII-II-MMXIX
 */
public class PowerAnalysis {

    private final Runtime runTime;
    private final WriteInFile fileWriter;
    private InsertionSort insertionSorter;
    private int[] sortingArray;
    private Random randomNumber;
    private Process processInfo;
    private BufferedReader stdInput;

    public PowerAnalysis() {
        fileWriter = new WriteInFile("Output File.txt");
        runTime = Runtime.getRuntime();
        fileWriter.writeInFile("Available processors cores are: " + Runtime.getRuntime().availableProcessors());
        fileWriter.writeInFile("Available memory bytes avilable to JVM are: " + Runtime.getRuntime().maxMemory());
        executeSort(1000000,12); //array start size and total number of increasing size repetitions
        fileWriter.closeWriting();
    }
    
    private void executeSort(int startSize, int repNumber) {
        randomNumber = new Random();
        int arraySize;
        for (int i = 0; i < repNumber; i++) {
            fileWriter.writeInFile("\n");
            fileWriter.writeInFile("CONDUCTING TEST NUMBER " + (i + 1));
            fileWriter.writeInFile("\n");
            arraySize = startSize * (i + 1);
            System.out.println(arraySize);
            fileWriter.writeInFile("Size of array is: " + arraySize);
            sortingArray = new int[arraySize];
            randomlyFillData();
            fileWriter.writeInFile("START time of algorithm in nanoseconds is: " + System.nanoTime());
            fileWriter.writeInFile("START state of battery");
            try {
                processInfo = runTime.exec("upower -i /org/freedesktop/UPower/devices/battery_BAT0");
                stdInput = new BufferedReader(new InputStreamReader(processInfo.getInputStream()));
                String line;
                while ((line = stdInput.readLine()) != null) {
                    fileWriter.writeInFile(line);
                }
            } catch (IOException e) {
                System.err.println("Error from reading console");
            }
            insertionSorter = new InsertionSort(sortingArray);
            fileWriter.writeInFile("END time of algorithm in nanoseconds is: " + System.nanoTime());
            fileWriter.writeInFile("END state of battery");
            try {
                processInfo = runTime.exec("upower -i /org/freedesktop/UPower/devices/battery_BAT0");
                stdInput = new BufferedReader(new InputStreamReader(processInfo.getInputStream()));
                String line;
                while ((line = stdInput.readLine()) != null) {
                    fileWriter.writeInFile(line);
                }
            } catch (IOException e) {
                System.err.println("Error from reading console");
            }
        }
    }

    private void randomlyFillData() {
        for (int i = 0; i < sortingArray.length; i++) {
            sortingArray[i] = randomNumber.nextInt();
        }
    }

    public static void main(String[] args) {
        PowerAnalysis powerAnalysisObject = new PowerAnalysis();
    }

}
