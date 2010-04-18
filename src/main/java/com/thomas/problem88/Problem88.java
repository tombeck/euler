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
package com.thomas.problem88;

import static java.lang.Math.min;

import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 12.12.2009
 */
public class Problem88 implements Problem {

    private static final int MAX = 12000;
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 02.04.2010
     */
    @Override
    public Integer solve() {

        final int[] ps = new int[MAX + 1];
        
        for (int k = 2; k <= MAX; ++k) ps[k] = 2 * k;
        findMinimalProductSum(ps, 0, 1, 0, MAX);

        final Set<Integer> ups = new HashSet<Integer>();

        for (int k = 2; k <= MAX; ++k) ups.add(ps[k]);

        int sum = 0;
        
        for (int n : ups) sum += n;
        
        return sum;
    }
    
    private void findMinimalProductSum(int[] ps, int i, int product, int sum, int prev) {
        
        /*
         * find the number of ones to make sum equal to product
         * and add the number of not-ones to get the size of the set
         */
        final int k = i + (product - sum);
        
        if (k >= 2 && k <= MAX && ps[k] > product) ps[k] = product;

        for (int n = min((2 * MAX) / product, prev); n > 1; --n) {
            findMinimalProductSum(ps, i + 1, product * n, sum + n, n);
        }
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 12.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem88());
    }

}
