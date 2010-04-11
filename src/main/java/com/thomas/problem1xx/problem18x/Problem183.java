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
package com.thomas.problem1xx.problem18x;

import static com.thomas.util.PrimeUtils.getPrimeFactors;
import static java.lang.Math.E;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import static java.lang.Math.log;

import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.01.2010
 */
class Problem183 implements Problem {

    private static final Integer TWO = Integer.valueOf(2);
    private static final Integer FIVE = Integer.valueOf(5);
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 16.01.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int n = 5; n <= 10000; ++n) sum += d(n);
        
        return sum;
    }

    private int d(int n) {
    
        final List<Integer> pn = getPrimeFactors(n);
        final List<Integer> pk = getPrimeFactors(k(n));
        
        for (Integer p : pn) pk.remove(p);
        while (pk.remove(TWO)) { /**/ }
        while (pk.remove(FIVE)) { /**/ }
        
        return pk.isEmpty() ? -n : n;
    }
    
    private int k(int n) {
    
        final double max = n / E;
        final int f = (int)floor(max);
        final int c = (int)ceil(max);
        
        return (f - c) * log(n) > f * log(f) - c * log(c) ? f : c; 
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 16.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem183());
    }

}
