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
package com.thomas.problem0xx.problem4x;

import static com.thomas.util.Digit.PRIME;
import static com.thomas.util.PrimeUtils.primes;
import static java.util.Collections.sort;

import java.util.Comparator;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 08.11.2009
 */
class Problem49 implements Problem {

    private static final Comparator<Integer> HASH = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {

            return getHash(o1) - getHash(o2);
        }
        
        private int getHash(int n) {
            
            int hash = 1;
            
            for (; n > 0; n /= 10) hash *= PRIME[n % 10];
            
            return hash;
        }
        
    };
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 24.01.2010
     */
    @Override
    public String solve() {

        final List<Integer> primes = primes(1000, 10000);

        sort(primes, HASH); // sort is stable

        for (int i = 1, len = 1; ; ++i) {
            if (HASH.compare(primes.get(i), primes.get(i - 1)) != 0) len = 0;
            else if (++len >= 3 && primes.get(i) != 8147) {
                if (primes.get(i - 1) - primes.get(i - 2) == primes.get(i) - primes.get(i - 1)) {

                    return "" + primes.get(i - 2) + primes.get(i - 1) + primes.get(i);
                }
            }
        }
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 08.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem49());
    }

}
