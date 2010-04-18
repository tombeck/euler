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
package com.thomas.problem0xx.problem9x;

import static com.thomas.Util.gcd;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 19.12.2009
 */
public class Problem91 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 28.02.2010
     */
    @Override
    public Integer solve() {

        final int max = 50;
        
        int sum = max * max;

        for (int x1 = 0; x1 <= max; ++x1) {
            for (int x2 = x1 == 0 ? 1 : 0; x2 <= max; ++x2) {
                if (gcd(x1, x2) == 1) {
                    for (int[] x = {0, 0}; (x[0] += x1) <= max && (x[1] += x2) <= max; ) {
                        for (int[] y = {x[0], x[1]}; (y[0] += x2) <= max && (y[1] -= x1) >=   0; ) ++sum;
                        for (int[] y = {x[0], x[1]}; (y[0] -= x2) >=   0 && (y[1] += x1) <= max; ) ++sum;
                    }
                }
            }
        }
        
        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 19.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem91());
    }

}
