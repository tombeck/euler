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
package com.thomas.problem2xx.problem26x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 25.10.2010
 */
public class Problem260 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final int max = 1000;
        
        final boolean [][] d1 = new boolean[max + 1][max + 1];
        final boolean [][] d2 = new boolean[max + 1][max + 1];
        final boolean [][] d3 = new boolean[max + 1][max + 1];
        
        int sum = 0;
        
        for (int x = 0; x <= max; ++x) {
            for (int y = x; y <= max; ++y) {
                if (!d1[x][y]) {
                    for (int z = y; z <= max; ++z) {
                        if (!(d1[x][y] || d1[x][z] || d1[y][z] || d2[x][z-y] || d2[y][z-x] || d2[z][y - x] || d3[y - x][z-y])) {
                            d1[x][y] = d1[x][z] = d1[y][z] = d2[x][z-y] = d2[y][z-x] = d2[z][y - x] = d3[y - x][z-y] = true;
                            sum += x + y + z;
                        }
                    }
                }
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem260());
    }

}
