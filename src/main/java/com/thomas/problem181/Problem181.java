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
package com.thomas.problem181;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 02.04.2010
 */
class Problem181 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 02.04.2010
     */
    @Override
    public Long solve() {

        final int w = 40;
        final int b = 60;
        final long[][][][] cache = new long[w + 1][b + 1][w + b + 1][w + 1];

        return count(cache, w, b, b + w, w);
    }

    private long count(long[][][][] cache, int w, int b, int max, int maxw) {
    
        if (b + w == 0) return 1;
        
        if (cache[w][b][max][maxw] == 0) {
            for (int i = max(0, max - b); i <= min(w, maxw); ++i) {
                cache[w][b][max][maxw] += count(cache, w - i, b - (max - i), max, i);
            }
            for (int sum = max - 1; sum > 0; --sum) {
                for (int i = max(0, sum - b); i <= min(w, sum); ++i) {
                    cache[w][b][max][maxw] += count(cache, w - i, b - (sum - i), sum, i);
                }
            }
        }
        
        return cache[w][b][max][maxw];
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 02.04.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem181());
    }

}
