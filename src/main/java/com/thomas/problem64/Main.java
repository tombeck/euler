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
package com.thomas.problem64;

import java.util.Arrays;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 23.11.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.11.2009
     */
    public static void main(String[] args) {

        int sum = 0;
        
        for (int n = 2; n <= 10000; ++n) if (isOdd(n)) ++sum;
        
        System.out.print(sum);

    }

    private static boolean isOdd(int sqr) {
    
        try {
            final int[] init = {1, isqrt(sqr)};
            
            boolean odd = true;
            for (int[] a = init; !Arrays.equals((a = generate(sqr, a)), init); ) odd = !odd;
    
            return odd;
            
        } catch (ArithmeticException e) {
            
            return false;
        }
    }
    
    private static int[] generate(int sqr, int[] a) {
    
        final int den2 = (sqr - a[1] * a[1]) / a[0];
        final int num2 = (((isqrt(sqr) + a[1]) / den2) * den2) - a[1];
        
        return new int[] {den2, num2};
    }
    
    private static int isqrt(int n) {
    
        return (int)Math.sqrt(n);
    }
    
}
