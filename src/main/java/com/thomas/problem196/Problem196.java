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
package com.thomas.problem196;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.PrimeUtils;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 30.03.2010
 */
public class Problem196 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 30.03.2010
     */
    @Override
    public Long solve() {
        
        final List<Integer> primes = PrimeUtils.primes(5097383);

        return S(5678027, primes) + S(7208785, primes);
    }

    private long S(int n, List<Integer> primes) {
    
        long first = first(n - 2);

        final boolean[] sieve = new boolean[n * 5];
        
        for (int prime : primes) {
            int offset = (int)(first % prime);
            if (offset != 0) offset = prime - offset;
            if (first + offset == prime) offset += prime;
            for (int i = offset; i < sieve.length; i += prime) {
                sieve[i] = true;
            }
        }
        first = first(n);
        long sum = 0;
        for (int pos = 0; pos < n; ++pos) {
            if (isInTriplet(n, pos, sieve)) {
                sum += pos + first;
            }
        }

        return sum;
    }

    private boolean isInTriplet(int len, int pos, boolean[] sieve) {
        
        if (isPrime(len, pos, 0, 0, sieve)) {
            if ((len & 1) == 0) { // even
                if (isPrime(len, pos, -1, -1, sieve)) {
                    return isPrime(len, pos, -1, -2, sieve) || isPrime(len, pos, 1, -1, sieve)|| isPrime(len, pos, 0, 1, sieve) || isPrime(len, pos, -2, 0, sieve);
                } else if (isPrime(len, pos, 1, -1, sieve)) {
                    return isPrime(len, pos, 1, -2, sieve) || isPrime(len, pos, 2, 0, sieve) || isPrime(len, pos, 0, 1, sieve);
                } else if (isPrime(len, pos, 0, 1, sieve)) {
                    return isPrime(len, pos, -1, 2, sieve) || isPrime(len, pos, 1, 2, sieve);
                }
            } else { // odd
                if (isPrime(len, pos, -1, 1, sieve)) {
                    return isPrime(len, pos, -1, 2, sieve) || isPrime(len, pos, 1, 1, sieve)|| isPrime(len, pos, 0, -1, sieve) || isPrime(len, pos, -2, 0, sieve);
                } else if (isPrime(len, pos, 1, 1, sieve)) {
                    return isPrime(len, pos, 1, 2, sieve) || isPrime(len, pos, 2, 0, sieve) || isPrime(len, pos, 0, -1, sieve);
                } else if (isPrime(len, pos, 0, -1, sieve)) {
                    return isPrime(len, pos, -1, -2, sieve) || isPrime(len, pos, 1, -2, sieve);
                }
            }
        }
        
        return false;
    }
    
    private boolean isPrime(int len, int pos, int x, int y, boolean[] sieve) {
    
        if (pos + x < 0) return false;
        if (pos + x >= len + y) return false;
        
        switch(y) {
        case -2: return !sieve[pos + x + 0 * len - 0];
        case -1: return !sieve[pos + x + 1 * len - 2];
        case  0: return !sieve[pos + x + 2 * len - 3];
        case  1: return !sieve[pos + x + 3 * len - 3];
        case  2: return !sieve[pos + x + 4 * len - 2];
        }
        
        throw new IllegalArgumentException();
    }
    
    private long first(long n) {
        
        return ((n - 1) * n) / 2 + 1;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 30.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem196());
    }

}
