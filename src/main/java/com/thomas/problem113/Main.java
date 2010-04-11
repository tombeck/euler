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
package com.thomas.problem113;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.11.2009
 */
public class Main {

    private static final long[][] INCREASING = new long[101][10];
    private static final long[][] DECREASING = new long[101][10];
    
    /**
     * TODO Method documentation
     * 
     * 51161058134250
     * 
     * @param args
     * @author Thomas
     * @since 28.11.2009
     */
    public static void main(String[] args) {

        long sum = 99;
        
        for (int len = 3; len < 101; ++len) {
            for (int n = 1; n < 10; ++n) {
                sum += countIncreasing(len, n);
                sum += countDecreasing(len, n);
            }
            sum -= 9;
            System.out.println(len + " : " + sum);
        }
    }

    private static long countIncreasing(int len, int first) {
    
        if (len == 1) return 1;
        
        if (INCREASING[len][first] > 0) return INCREASING[len][first];
        
        long count = 0;
        
        for (int n = first; n < 10; ++n) {
            count += countIncreasing(len - 1, n);
        }

        INCREASING[len][first] = count;
        
        return count;
    }
    
    private static long countDecreasing(int len, int first) {
    
        if (len == 1) return 1;
        
        if (DECREASING[len][first] > 0) return DECREASING[len][first];
        
        long count = 0;
        
        for (int n = first; n >= 0; --n) {
            count += countDecreasing(len - 1, n);
        }

        DECREASING[len][first] = count;
        
        return count;
    }
    
}
