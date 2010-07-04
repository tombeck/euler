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

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 03.04.2010
 */
class Problem229 implements Problem {

    /**
     * TODO Method documentation
     * 
     * {@link http://www.research.att.com/~njas/sequences/A139506 }
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 03.04.2010
     */
    @Override
    public Object solve() {

        final long max = 100000L;
        
        final Set<Long> set = new TreeSet<Long>();
        
        for (long i = 1, ii = i * i; ii <= max; ii += 2 * i++ + 1) {
            for (long j = i, jj = j * j; jj <= max; jj += 2 * j++ + 1) {
                
                final long n = ii + jj;
                
                for (long k = 1, kk = k * k; kk <= n; kk += 2 * k++ + 1) {
                    if (NumberUtils.isSquare(n - 2 * kk)) {
                        for (long l = 1, ll = l * l; ll <= n; ll += 2 * l++ + 1) {
                            if (NumberUtils.isSquare(n - 3 * ll)) {
                                for (long m = 1, mm = m * m; mm <= n; mm += 2 * m++ + 1) {
                                    if (NumberUtils.isSquare(n - 7 * mm)) {
//                                        for (long s : set) {
//                                            if (n % s == 0) continue loop;
//                                        }
                                        set.add(n);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        for (long n : set) {
            System.out.println(n + " = " + PrimeUtils.getPrimeFactors(n));
        }
        
        return set.size();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 03.04.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem229());
    }

}
