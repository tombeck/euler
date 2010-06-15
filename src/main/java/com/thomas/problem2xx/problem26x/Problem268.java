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
package com.thomas.problem2xx.problem26x;

import static com.thomas.util.NumberUtils.toIntArray;
import static com.thomas.util.PrimeUtils.primes;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 20.02.2010
 */
public class Problem268 implements Problem {

    /**
     * TODO Method documentation

     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 20.02.2010
     */
    @Override
    public Long solve() {

        final int[] primes = toIntArray(primes(100));
        final long max = 10000000000000000L - 1;

        return sum(primes, 0, 1,true, 1, 0, 0, max);
    }

    private long sum(int[] primes, int i, long prev, boolean add, int m1, int m2, int m3, long max) {
        
        long sum = 0;
        
        for (long m = 1 - m1 + m2 - m3, next; i < primes.length && (next = prev * primes[i]) <= max; ) {
            if (add) sum += m * (max / next);
            else sum -= m * (max / next);
            sum += sum(primes, ++i, next, !add, m1 + 1, m2 + m1, m3 + m2, max);
        }
        
        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 20.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem268());
    }

}
