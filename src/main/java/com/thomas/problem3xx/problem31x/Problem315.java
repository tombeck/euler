/**
 * $Id$
 * 
 * Copyright (c) 2014 Thomas Beckmann
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

package com.thomas.problem3xx.problem31x;

import static com.thomas.util.Digit.sumOfDigits;
import static com.thomas.util.PrimeUtils.primes;
import static java.lang.Integer.bitCount;

import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 21.01.2014
 */
public class Problem315 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final int[] digits = {0x3f, 0x06, 0x5b, 0x4f, 0x66, 0x6d, 0x7d, 0x27, 0x7f, 0x6f};
        final int[][] d1diff = new int[10][10]; // one digit transition cost differences
        final int[][] d2diff = new int[100][100]; // two digit transition cost differences
        final int[] d2rootdiff = new int[100]; // two digit transition to root cost differences
        
        for (int from = 0; from < 10; ++from) {
            for (int to = 0; to < 10; ++to) {
                d1diff[from][to] = bitCount(digits[from]&digits[to]) * 2;
            }
        }
        for (int from = 0; from < 100; ++from) { // transitions without leading zero
            for (int to = 0; to < 100; ++to) {
                if (from < 10 || to < 10) {
                    d2diff[from][to] = d1diff[from%10][to%10];
                } else {
                    d2diff[from][to] = d1diff[from%10][to%10] + d1diff[from/10][to/10];
                }
            }
        }
        for (int from = 10; from < 100; ++from) {
            int to = sumOfDigits(from);
            d2rootdiff[from] = d2diff[from][to] + d2rootdiff[to];
        }
        for (int from = 0; from < 10; ++from) { // transitions with 'from' having leading zeros
            for (int to = 10; to < 100; ++to) {
                d2diff[from][to] = d1diff[from][to%10] + d1diff[0][to/10];
            }
        }

        int diff = 0;
        
        for (int p : new TreeSet<Integer>(primes(20000000)).tailSet(10000000)) {
            int s = sumOfDigits(p);
            diff += d2diff[p%100][s] + d2rootdiff[s];
        }

        return diff;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem315());
    }

}
