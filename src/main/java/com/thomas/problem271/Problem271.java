/**
 * $Id$
 *
 * Copyright (c) 2010 Thomas Beckmann
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
package com.thomas.problem271;

import static com.thomas.util.NumberUtils.modInv;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem271 implements Problem {

    /**
     * 13082761331670030 = 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23 * 29 * 31 * 37 * 41 * 43
     * 
     *  2 :  3 + 2x
     *  3 :  4 + 3x
     *  5 :  6 + 5x
     *  7 :  2 + 7x or 4 + 7x or 8 + 7x
     * 11 : 12 + 11x
     * 13 :  3 + 13x or 9 + 13x or 14 + 13x
     * 17 : 18 + 17x
     * 19 :  7 + 19x or 11 + 19x or 20 + 19x
     * 23 : 24 + 23x
     * 29 : 30 + 29x
     * 31 :  5 + 31x or 25 + 31x or 32 +31x
     * 37 : 10 + 37x or 26 + 37x or 38 + 37x
     * 41 : 42 + 41x
     * 43 :  6 + 43x or 36 + 43x or 44 + 43x
     * 
     *  x >= 0
     *  
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final int [][][] primes = {
                {{3, 2}},
                {{4, 3}},
                {{6, 5}},
                {{2, 7}, {4, 7}, {8, 7}},
                {{12, 11}},
                {{3, 13}, {9, 13}, {14, 13}},
                {{18, 17}},
                {{7, 19}, {11, 19}, {20, 19}},
                {{24, 23}},
                {{30, 29}},
                {{5, 31}, {25, 31}, {32, 31}},
                {{10, 37}, {26, 37}, {38, 37}},
                {{42, 41}},
                {{6, 43}, {36, 43}, {44, 43}}
        };

        return sum(primes, 0, 2, 1); //4617456485273129588
    }

    private long sum(int[][][] primes, int i, long base, long mul) {
        
        if (i == primes.length) return base < mul ? base : 0;
        
        long sum = 0;
        
        for (int[] prime : primes[i]) {
            long d = prime[1];
            sum += sum(primes, i + 1, (base + mul * ((((prime[0] - base) * modInv(mul, d)) % d + d) % d)), mul * prime[1]);
        }
        
        return sum; 
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem271());
    }

}
