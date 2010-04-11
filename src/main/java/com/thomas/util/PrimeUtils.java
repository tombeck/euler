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
package com.thomas.util;

import static java.util.Collections.binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.10.2009
 */
public class PrimeUtils {

    public static List<Long> getDistinctPrimeFactors(long n) {
        
        final List<Long> factors = new ArrayList<Long>();
        
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                do n /= i;
                while (n % i == 0);
            }
        }
        if (n > 1) factors.add(n);

        return factors;
    }

    public static List<Integer> getDistinctPrimeFactors(int n) {
        
        final List<Integer> factors = new ArrayList<Integer>();
        
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                do n /= i;
                while (n % i == 0);
            }
        }
        if (n > 1) factors.add(n);

        return factors;
    }

    public static List<Long> getPrimeFactors(long n) {
        
        final List<Long> factors = new ArrayList<Long>();
        
        for (long i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add(n);

        return factors;
    }

    public static List<Integer> getPrimeFactors(int n) {
        
        final List<Integer> factors = new ArrayList<Integer>();
        
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add(n);

        return factors;
    }

    public static List<Integer> getPrimeFactors(int n, List<Integer> primes) {
        
        final List<Integer> factors = new ArrayList<Integer>();
        int sqrt = (int)Math.sqrt(n);
       
        for (int prime : primes) {
            if (n == 1) return factors;
            if (prime > sqrt) break;
            while (n % prime == 0) {
                factors.add(prime);
                n /= prime;
            }
        }
        factors.add(n);

        return factors;
    }

    public static boolean isPrime(long index, List<Integer> primes) {

        if (index < 2) return false;
        
        int sqrt = (int)Math.sqrt(index);
        
        for (Integer prime : primes) {
            if (prime > sqrt) break;
            if (index % prime.longValue() == 0) return false;
        }

        return true;
    }
    
    public static boolean isPrime(long n) {
        
        if (n < 2) return false;
        
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) return false;
        }
        
        return true;
    }
    
    public static List<Integer> primes(int max) {
    
        final boolean[] sieve = new boolean[max];
        final List<Integer> primes = new ArrayList<Integer>();
        
        int i = 2;
        
        for (int q; (q = i * i) < max; ++i) {
            if (!sieve[i]) {
                primes.add(i);
                for (int j = q; j < max; j += i) sieve[j] = true;
            }
        }
        for ( ; i < max; ++i) {
            if (!sieve[i]) primes.add(i);
        }

        return primes;
    }
    
    public static List<Integer> primes(int min, int max) {
        
        final List<Integer> primes = primes(max);
        final int index = binarySearch(primes, min);
        
        if (index >= 0) return primes.subList(index, primes.size());
        return primes.subList(index - 1, primes.size());
    }
    
    private PrimeUtils() {
        //
    }

}
