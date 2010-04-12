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
package com.thomas.problem0xx.problem3x;

import static com.thomas.util.PrimeUtils.isPrime;
import static com.thomas.util.PrimeUtils.primes;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 21.10.2009
 */
class Problem35 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 13.01.2010
     */
    @Override
    public Integer solve() {

        final List<Integer> primes = primes(1000);
        final int [] digits = {1, 3, 7, 9};
        
        int sum = 4; // 2, 3, 5, 7

        for (int i1 = 0; i1 < 4; ++i1) {
            for (int i2 = 0, p1 = 10 * digits[i1]; i2 < 4; ++i2) {
                if (isCircularPrime(p1 + digits[i2], 2, 10, primes)) ++sum;
                for (int i3 = 0, p2 = 10 * (p1 + digits[i2]); i3 < 4; ++i3) {
                    if (isCircularPrime(p2 + digits[i3], 3, 100, primes)) ++sum;
                    for (int i4 = 0, p3 = 10 * (p2 + digits[i3]); i4 < 4; ++i4) {
                        if (isCircularPrime(p3 + digits[i4], 4, 1000, primes)) ++sum;
                        for (int i5 = 0, p4 = 10 * (p3 + digits[i4]); i5 < 4; ++i5) {
                            if (isCircularPrime(p4 + digits[i5], 5, 10000, primes)) ++sum;
                            for (int i6 = 0, p5 = 10 * (p4 + digits[i5]); i6 < 4; ++i6) {
                                if (isCircularPrime(p5 + digits[i6], 6, 100000, primes)) ++sum;
                            }
                        }
                    }
                }
            }
        }

        return sum;
    }

    private boolean isCircularPrime(int p, int length, int magnitude, List<Integer> primes) {
    
        if (!isPrime(p, primes)) return false;

        for (int i = 1; i < length; ++i) {
            if (!isPrime(p = p / 10 + (p % 10) * magnitude, primes)) return false;
        }
        
        return true;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 21.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem35());
    }

}
