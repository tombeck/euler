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
package com.thomas.problem2xx.problem20x;

import static com.thomas.util.NumberUtils.toLongArray;
import static com.thomas.util.PrimeUtils.isPrime;
import static com.thomas.util.PrimeUtils.primes;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 16.12.2010
 */
public class Problem200 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final long[] primes = toLongArray(primes(200000));

        final Set<Long> squbes = new TreeSet<Long>();
        
        for (long p : primes) {
            
            long sqube;
            
            if (String.valueOf(sqube = 2 * 2 * p * p * p).contains("200")) squbes.add(sqube);
            if (String.valueOf(sqube = 5 * 5 * p * p * p).contains("200")) squbes.add(sqube);
            if (String.valueOf(sqube = 2 * 2 * 2 * p * p).contains("200")) squbes.add(sqube);
            if (String.valueOf(sqube = 5 * 5 * 5 * p * p).contains("200")) squbes.add(sqube);
        }

        int count = 0;
        
        for (long sqube : squbes) {
            if (isPrimeProof(sqube) && ++count == 200) return sqube;
        }
        
        return null;
    }

    private boolean isPrimeProof(final long n) {
        
        final int d = (int)(n % 10);
        
        for (int i = -d; i < 0; ++i) {
            if (isPrime(n + i)) return false;
        }
        for (int i = 10 - d; i-- > 1; ) {
            if (isPrime(n + i)) return false;
        }

        return true;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem200());
    }

}
