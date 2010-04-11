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
package com.thomas.problem0xx.problem2x;

import java.util.Arrays;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 20.10.2009
 */
class Problem26 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 11.01.2010
     */
    @Override
    public Integer solve() {

        final int limit = 1000;
        final int[] pos = new int[limit];
        
        int length_max = 0;
        int d_max = 0;
        for (int d = 2; d < limit; ++d) {
            
            int n = 1;
            int index = 0;
            
            Arrays.fill(pos, 0);
            for (; pos[n = (10 * n) % d] == 0; pos[n] = index++) { /**/ }
            final int l = index - pos[n];
            if (l > length_max) {
                length_max = l;
                d_max = d;
            }
        }
        
        return d_max;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 20.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem26());
    }

}
