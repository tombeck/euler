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
package com.thomas.problem193;

import static com.thomas.util.NumberUtils.toIntArray;
import static com.thomas.util.PrimeUtils.primes;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 21.11.2009
 */
public class Problem193 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 26.03.2010
     */
    @Override
    public Long solve() {

        final long max = (1L << 50) - 1; // 2^50 - 1

        return max - sum(toIntArray(primes(33554468)), 0, 1, true, max);
    }

    private long sum(int[] primes, int i, long prev, boolean add, long max) {
    
        long sum = 0;
        
        for (long sqr, div = max / prev; (sqr = (long)primes[i] * primes[i]) <= div; ) {
            
            final long next =  prev * sqr;
            
            if (add) {
                sum += max / next;
            } else {
                sum -= max / next;
            }
            sum += sum(primes, ++i, next, !add, max);
        }
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 21.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem193());
    }

}
