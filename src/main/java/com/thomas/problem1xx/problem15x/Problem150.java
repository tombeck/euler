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
package com.thomas.problem1xx.problem15x;

import static java.util.Arrays.fill;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.IntGenerator;
import com.thomas.util.random.LinearCongruentialGenerator;

/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 20.12.2009
 */
public class Problem150 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 20.12.2009
     */
    @Override
    public Integer solve() {

        final int max = 1000;
        final int[][] t = new int[max][];
        final int[][] s = new int[max + 2][];
        final IntGenerator gen = new LinearCongruentialGenerator(615949, 797807, 1 << 20);
        
        for (int r = 0; r < max; ++r) {
            s[r] = new int[r + 1];
            t[r] = new int[r + 1];
            for (int c = 0; c <= r; ++c) {
                t[r][c] = gen.next() - (1 << 19);
            }
        }
        s[max] = new int[max + 1];
        s[max + 1] = new int[max + 2];
        
        int min = 0;
        
        for (int last = max; last-- > 0;) {
            fill(s[last + 1], 0);
            for (int r = last; r >= 0; --r) {
                for (int c = 0; c <= r; ++c) {
                    
                    final int sum = (s[r][c] = t[r][c] + s[r + 1][c] + s[r + 1][c + 1] - s[r + 2][c + 1]);
                    
                    if (sum < min) min = sum;
                }
            }
        }

        return min;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 20.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem150());
    }

}
