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
package com.thomas.problem1xx.problem10x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 24.02.2010
 */
class Problem109 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 24.02.2010
     */
    @Override
    public Integer solve() {

        final int[] a = {0, 1, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8, 9, 9, 10, 10, 11, 12, 12, 12, 13, 14, 14, 15, 15, 16, 16, 17, 18, 18, 18, 19, 20, 20, 21, 22, 24, 24, 25, 26, 27, 28, 30, 30, 32, 33, 34, 36, 36, 38, 39, 40, 42, 45, 48, 50, 51, 54, 57, 60};
        final int[] d = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 50};

        final int max = 99;
        final int[] s = new int[max + 1];
        
        for (int i = 0; i < a.length; ++i) {
            for (int j = i, k; j < a.length && (k = a[i] + a[j]) < s.length; ++j) ++s[k];
        }
        for (int i = 1; i < s.length; ++i) s[i] += s[i - 1];
        
        int sum = 0;
        
        for (int i = 0; i < d.length; ++i) sum += s[max - d[i]];

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 24.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem109());
    }

}
