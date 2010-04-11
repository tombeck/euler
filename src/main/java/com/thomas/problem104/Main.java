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
package com.thomas.problem104;

import static java.lang.Character.digit;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 06.12.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 06.12.2009
     */
    public static void main(String[] args) {

        int[] i = {1, 1};
        double[] d = {1.0, 1.0};
        int k = 2;

        while(i[1] < 100000000) {
            i = new int[] {i[1], i[0] + i[1]};
            d = new double[] {d[1], d[0] + d[1]};
            ++k;
        }
        while(true) {
            if (isPandigital(Integer.toString(i[1]).toCharArray())
                    && isPandigital(Integer.toString((int)d[1]).toCharArray())) break;
            i = new int[] {i[1], (i[0] + i[1]) % 1000000000};
            d = new double[] {d[1], d[0] + d[1]};
            if (d[1] > 1000000000.0) {
                d[0] /= 10.0;
                d[1] /= 10.0;
            }
            ++k;
        }
        
        System.out.println("> " + k);
    }

    private static boolean isPandigital(char[] a) {
    
        final boolean[] ba = {false, false, false, false, false, false, false, false, false, false};

        for (char c : a) {
            ba[digit(c, 10)] = true;
        }
        for (int i = 1; i < 10; ++i) {
            if (!ba[i]) return false;
        }

        return true;
    }
    
}
