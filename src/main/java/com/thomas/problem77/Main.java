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
package com.thomas.problem77;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thomas.util.PrimeUtils;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 11.11.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 11.11.2009
     */
    public static void main(String[] args) {

        final List<List<Set<Integer>>> results = new ArrayList<List<Set<Integer>>>();
        
        results.add(Collections.<Set<Integer>>emptyList());
        results.add(Collections.<Set<Integer>>emptyList());

        for (int n = 2; ; ++n) {
            final List<Set<Integer>> sums = new ArrayList<Set<Integer>>(1);
            for (int i = 0, p; (p = getPrime(i)) <= n; ++i) {
                int r = n - p;
                if (r == 0) {
                    final Set<Integer> set = new HashSet<Integer>(1);
                    set.add(p);
                    sums.add(set);
                } else {
                    for (Set<Integer> sum : results.get(r)) {
                        if (isValid(sum, p)) {
                            final Set<Integer> set = new HashSet<Integer>(sum);
                            set.add(p);
                            sums.add(set);
                        }
                    }
                }
            }
            if (sums.size() > 5000) {
                System.out.println(n);
                return;
            }
            results.add(sums);
        }
    }

    private static boolean isValid(Set<Integer> set, int p) {
        
        for (Integer i : set) {
            if (i < p) return false;
        }
        return true;
    }
    private static final List<Integer> PRIMES = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
    
    private static int getPrime(int i) {
    
        if (i < PRIMES.size()) return PRIMES.get(i);
        
        for (int n = PRIMES.get(PRIMES.size() - 1) + 1; ; ++n) {
            if (PrimeUtils.isPrime(n, PRIMES)) {
                PRIMES.add(n);
                if (i == PRIMES.size() - 1){
                    return PRIMES.get(i);
                }
            }
        }
    }
    
}
