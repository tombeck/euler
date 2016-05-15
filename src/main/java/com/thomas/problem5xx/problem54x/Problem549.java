/**
 * $Id$
 *
 * Copyright (c) 2016 Thomas Beckmann
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

package com.thomas.problem5xx.problem54x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @since 14.05.2016
 */
public class Problem549 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() throws Exception {

        final int max = 100000000;
        final int[] s = new int[max + 1];
        
        long sum = 0;
        
        for (int i = 2; i <= max; ++i) {
            if (s[i] == 0) {
                long p = 1;
                for (int j = 1; (p *= i) <= max; ++j) {
                    int f = factorial(i, j);
                    for (int k = (int)p; k <= max; k += (int)p) {
                        if (f > s[k]) s[k] = f;
                    }
                }
            }
            sum += s[i];
        }

        return sum;
    }

    int factorial(int prim, int power) {
        
        int factorial = prim;
        
        for (int c = 1, j = prim << 1; c < power; j += prim) {
            for (int a = j; a % prim == 0; a /= prim) {
                ++c;
            }
            factorial += prim;
        }
        
        return factorial;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem549());
    }

}
