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
package com.thomas.problem87;

import static com.thomas.util.PrimeUtils.isPrime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 15.11.2009
 */
class Problem87 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 12.03.2010
     */
    @Override
    public Integer solve() {

        List<Integer> primes2 = new ArrayList<Integer>();
        List<Integer> primes3 = new ArrayList<Integer>();
        List<Integer> primes4 = new ArrayList<Integer>();
        
        for (int n = 2, p; (p = n) <= 84; ++n) { // 84 = 50000000^(1/4)
            if (isPrime(n)) {
                primes2.add(p *= n);
                primes3.add(p *= n);
                primes4.add(p *= n);
            }
        }
        for (int n = 85, p; (p = n) <= 368; ++n) { // 368 = 50000000^(1/3)
            if (isPrime(n)) {
                primes2.add(p *= n);
                primes3.add(p *= n);
            }
        }
        for (int n = 369, p; (p = n) <= 7071; ++n) { // 7071 = 50000000^(1/2)
            if (isPrime(n)) {
                primes2.add(p *= n);
            }
        }

        final Set<Integer> res = new HashSet<Integer>(primes2.size() * primes3.size() * primes4.size());
        
        for (int p4 : primes4) {
            for (int p3 : primes3) {
                for (int p2 : primes2) {
                    
                    final int sum = p4 + p3 + p2;
                    
                    if (sum < 50000000) res.add(sum);
                }
            }
        }
        
        return res.size();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 15.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem87());
    }

}
