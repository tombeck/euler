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
package com.thomas.problem0xx.problem3x;

import static com.thomas.Util.gcd;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.01.2010
 */
public class Problem39 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 14.01.2010
     */
    @Override
    public Integer solve() {

        final int max = 1000;
        final int[] solutions = new int[max + 1];
        
        for (int u = 1; u < 16; ++u) {
            for (int v = 1; v < u; ++v) {
                if (gcd(u * u - v * v, 2 * u * v) == 1) {
                    for (int len = 2 * u * (u + v), p = len; p <= max; p += len) {
                        ++solutions[p];
                    }
                }
            }
        }
        
        int p = 0;
        
        for (int i = 1; i <= max; ++i) {
            if (solutions[i] > solutions[p]) p = i;
        }
        
        return p;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem39());
    }

}
