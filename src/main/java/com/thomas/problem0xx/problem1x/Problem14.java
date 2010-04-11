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
package com.thomas.problem0xx.problem1x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 17.10.2009
 */
class Problem14 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 30.12.2009
     */
    @Override
    public Integer solve() {

        final int max = 1000000;
        final int[] cache = new int[max];
        
        cache[1] = 1;
        
        int i_max = 1;
        int len_max = 1;
        
        for (int i = 2; i < max; ++i) {
            
            int len = 1;
            long n = i;
            
            for (; n >= i; ++len) {
                n = (n & 1) == 0 ? n >> 1 : 3 * n + 1;
            }
            len += cache[(int)n];
            cache[i] = len;

            if (len > len_max) {
                i_max = i;
                len_max = len;
            }
        }
        
        return i_max;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 17.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem14());
    }

}
