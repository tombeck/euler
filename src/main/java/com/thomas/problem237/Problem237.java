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
package com.thomas.problem237;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem237 implements Problem {

    static int[][] INITIAL = {
        /*         0000 0011 0110 1001 1100 1111P 1111N
        /*0000 */ {   0,   1,   1,   0,   1,    0,    1},
        /*0011 */ {   0,   0,   0,   1,   0,    1,    0},
        /*0110 */ {   0,   0,   0,   1,   0,    0,    0},
        /*1001 */ {   1,   1,   1,   0,   1,    0,    1},
        /*1100 */ {   0,   0,   0,   1,   0,    1,    0},
        /*1111P*/ {   0,   0,   0,   1,   0,    1,    0},
        /*1111N*/ {   1,   1,   0,   0,   1,    0,    1},
    };
    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        return count(0, 0, 1000000000000L, 0, new Integer[100][7][7]);
    }

    final long count(int start, int end, long steps, int depth, Integer[][][] cache) {
        
        if (steps == 1) return INITIAL[start][end];
        
        if ((steps & 1) == 0) {
            
            if (cache[depth][start][end] != null) return cache[depth][start][end];
            
            long count = 0;
            
            long mid = steps / 2;
            
            for (int i = 1; i < 7; ++i) {
                count += count(start, i, mid, depth + 1, cache) * count(i, end, steps - mid, depth + 1, cache);
                count %= 100000000L;
            }
            
            return cache[depth][start][end] = (int)count;
            
        } else {
            
            long count = 0;
            
            for (int i = 1; i < 7; ++i) {
                if (INITIAL[i][end] > 0) {
                    count += count(start, i, steps - 1, depth + 1, cache);
                    count %= 100000000L;
                }
            }
            
            return (int)count;
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem237());
    }

}
