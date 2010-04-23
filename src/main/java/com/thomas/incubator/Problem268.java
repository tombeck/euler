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
package com.thomas.incubator;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 20.02.2010
 */
class Problem268 implements Problem {

    /**
     * TODO Method documentation

     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 20.02.2010
     */
    @Override
    public Object solve() {

        final int[] primes = {
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
                43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
        };
        final long max = 10000000000000000L;

        long p = 1;
        for (int prime : primes) {
            p *= prime;
        }
        System.out.println(primes);
        
        return null;
    }

    private long product(int[] primes, int mask) {
    
        long p = 1;
        
        for (int i = 0; mask > 0; ++i) {
            if ((mask & 1) != 0) p *= primes[i];
            mask >>= 1;
        }
        
        return p;
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
