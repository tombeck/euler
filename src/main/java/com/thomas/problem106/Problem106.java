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
package com.thomas.problem106;

import static java.lang.Integer.bitCount;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.12.2009
 */
public class Problem106 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 26.12.2009
     */
    @Override
    public Integer solve() {

        int count = 0;
        
        for (int i = 1; i < (1 << 12) - 1; ++i) {
            for (int j = i + 1; j < (1 << 12); ++j) {
                if ((i & j) == 0 && bitCount(i) == bitCount(j) && mixed(i, j)) {
                    ++count;
                }
            }
        }

        return count;
    }

    private boolean mixed(int i, int j) {
    
        final int[] count = {0, 0};
        final boolean[] ahead = {false, false};
        
        while (i > 0 || j > 0) {
            if ((i & 1) != 0) ++count[0];
            if ((j & 1) != 0) ++count[1];
            ahead[0] |= count[0] > count[1];
            ahead[1] |= count[1] > count[0];
            if (ahead[0] && ahead[1]) return true;
            i >>= 1;
            j >>= 1;
        }
        
        return false;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem106());
    }

}
