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
package com.thomas.problem1xx.problem12x;

import static java.util.Arrays.sort;

import java.util.Comparator;

import com.thomas.util.Euler;
import static com.thomas.util.PrimeUtils.*;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 11.11.2009
 */
public class Problem124 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 26.03.2010
     */
    @Override
    public Integer solve() {

        final int[][] tuples = new int[100000 + 1][2];
        
        for (int n = 1; n <= 100000; ++n) {
            tuples[n][0] = n;
            tuples[n][1] = 1;
        }
        for (int p : primes(100000)) {
            for (int i = p; i <= 100000; i += p) tuples[i][1] *= p;
        }
        sort(tuples, new Comparator<int[]>() {

            @Override
            public int compare(int[] arg0, int[] arg1) {

                int diff = arg0[1] - arg1[1];
                
                return diff == 0 ? arg0[0] - arg1[0] : diff;
            }
            
        });

        return tuples[10000][0];
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 11.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem124());
    }

}
