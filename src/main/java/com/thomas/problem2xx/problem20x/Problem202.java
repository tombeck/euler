/**
 * $Id$
 * 
 * Copyright (c) 2013 Thomas Beckmann
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

package com.thomas.problem2xx.problem20x;

import static com.thomas.util.NumberUtils.toLongArray;
import static com.thomas.util.PrimeUtils.getDistinctPrimeFactors;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 08.08.2013
 */
public class Problem202 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long n = 12017639147L;
        final long m = (n+3)/2;
                    
        final long[] primes = toLongArray(getDistinctPrimeFactors(m));
        
        long sum = 0;
        
        for (long x = 3 - m%3; x < m/2; x += 3) {
            if (coprime(x, primes)) ++sum;
        }
        
        return 2*sum;
    }

    boolean coprime(long x, long[] primes) {
    
        for (long prime : primes) {
            if (x % prime == 0) return false;
        }
        
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem202());
    }

}
