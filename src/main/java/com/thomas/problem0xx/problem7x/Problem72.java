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
package com.thomas.problem0xx.problem7x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 28.10.2009
 */
class Problem72 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 13.03.2010
     */
    @Override
    public Long solve() {

        final int max = 1000000;
        final int[] n = new int[max + 1];

        for (int i = 0; i <= max; ++i) n[i] = i;

        long sum = 0;

        for (int i = 2; i <= max; ++i) {
            if (n[i] == i) {
                for (int j = i; j <= max; j += i) n[j] -= n[j] / i;
            }
            sum += n[i];
        }

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 13.03.2010
     */
    public static void main(String[] args) {
        
        Euler.run(new Problem72());
    }

}
