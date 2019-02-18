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
public class RecursiveFactorial {

    protected long factorial(long number) {
        if (number == 0) {
            return 1;
        } else {
            return (number * factorial(number - 1));
        }
    }
}
