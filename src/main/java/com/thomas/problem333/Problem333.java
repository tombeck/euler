/**
 * $Id$
 *
 * Copyright (c) 2011 Thomas Beckmann
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
package com.thomas.problem333;

import static com.thomas.util.PrimeUtils.primes;
import static java.lang.Integer.lowestOneBit;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 23.04.2011
 */
public class Problem333 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int q : primes(1000000)) {
            if (count(q, q + 1, 0) == 1) sum += q;
        }
        
        return sum;
    }

    private int count(int q, int max, int count) {
        
        if (q == 0) return count + 1;

        for (int p2 = lowestOneBit(q), p3 = 1; p3 < max && count < 2; p3 *= 3) {
            
            if (p2 * p3 > q) break;
            
            count = count(q - p2 * p3, p3, count);
        }
        
        return count;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem333());
    }

}
