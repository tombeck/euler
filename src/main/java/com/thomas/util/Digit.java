/**
 * $Id$
 *
 * Copyright (c) 2009 Thomas Beckmann 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.thomas.util;

import static java.lang.Character.digit;

import java.math.BigInteger;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.10.2009
 */
public class Digit {

    public static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    public static final int[] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public static final int[] SQUARE = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81};
    
    public static final int[] PRIME = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    
    public static final int[] FIFTH_POWER = {0, 1, 32, 243, 1024, 3125, 7776, 16807, 32768, 59049};
    
    
    
    public static int digitalRoot(int n) {
    
        if (n > 0) return (n - 1) % 9 + 1;
        if (n < 0) return digitalRoot(-n);
        
        return 0;
    }
    
    public static int sumOfDigits(String s) {
        
        int sum = 0;
        
        for (char ch : s.toCharArray()) sum += digit(ch, 10);
        
        return sum;
    }
    
    public static int sumOfDigitSquares(int... n) {
        
        int sum = 0;
        
        for (int d : n) sum += SQUARE[d];
        
        return sum;
    }

    public static int sumOfDigits(long n) {
        
        int sum = 0;
        
        for (; n >= 10; n /= 10) {
            sum += n % 10;
        }
        
        return sum + (int)n;
    }
    
    public static int sumOfDigits(BigInteger n) {
        
        return sumOfDigits(n.toString());
    }
    
    public static int digitCount(BigInteger n) {
        
        return n.abs().toString().length();
    }
    
    private Digit() {
        //
    }
    
    public static void main(String[] args) {
    
        for (int i = 0; i < 100; ++i) {
            System.out.println(i + ": " + digitalRoot(i));
        }
    }
    
}
