/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poweranalysis;

/**
 *
 * @author iotica
 */
public class MergeSort {

    public MergeSort(int sortingArray[]) {
        mergeSort(sortingArray, 0, sortingArray.length);
    }

    private void mergeSort(int sortingArray[], int lowNumber, int highNumber) {
        int differenceNumber = highNumber - lowNumber;
        if (differenceNumber <= 1) {
            return;
        }
        int middleNumber = lowNumber + (differenceNumber / 2);
        mergeSort(sortingArray, lowNumber, middleNumber);
        mergeSort(sortingArray, middleNumber, highNumber);
        int[] tempArray = new int[differenceNumber];
        int i = lowNumber, j = middleNumber;
        for (int k = 0; k < differenceNumber; k++) {
            if (i == middleNumber) {
                tempArray[k] = sortingArray[j++];
            } else if (j == highNumber) {
                tempArray[k] = sortingArray[i++];
            } else if (sortingArray[j] < sortingArray[i]) {
                tempArray[k] = sortingArray[j++];
            } else {
                tempArray[k] = sortingArray[i++];
            }
        }

        for (int k = 0; k < differenceNumber; k++) {
            sortingArray[lowNumber+k] = tempArray[k];
        }
    }
}
