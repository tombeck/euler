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
package com.thomas.problem115;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.12.2009
 */
public class Problem115 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 01.04.2010
     */
    @Override
    public Integer solve() {

        int n = 0;
        long[] red = new long[n + 1];
        long[] black = new long[n + 1];
        
        do {
            ++n;
            red = new long[n + 1];
            black = new long[n + 1];
        } while (addRed(50, n, red, black) + addBlack(50, n, red, black) <= 1000000);

        return n;
    }

    private long addRed(int min, final int len, long[] red, long[] black) {
        
        if (len < 0) return 0;
        if (len == 0) return 1;
        
        if (red[len] == 0) {
            for (int n = min; n <= len; ++n) {
                red[len] += addBlack(min, len - n, red, black);
            }
        }
        
        return red[len];
    }

    private long addBlack(int min, int len, long[] red, long[] black) {
        
        if (len < 0) return 0;
        if (len == 0) return 1;
        
        if (black[len] == 0) {
            for (int n = 1; n <= len; ++n) {
                black[len] += addRed(min, len - n, red, black);
            }
        }
        
        return black[len];
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 16.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem115());
    }

}
