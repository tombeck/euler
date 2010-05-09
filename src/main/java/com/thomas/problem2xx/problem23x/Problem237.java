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
package com.thomas.problem2xx.problem23x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.matrix.IntColVector;
import com.thomas.util.matrix.IntMatrix;
import com.thomas.util.matrix.IntRowVector;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem237 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final int mod = 100000000;
        final long width = 1000000000000L;
        final int[][] matrix = {
                /*         0011 0110 1001 1100 1111P 1111N
                /*0011 */ {   0,   0,   1,   0,    1,    0},
                /*0110 */ {   0,   0,   1,   0,    0,    0},
                /*1001 */ {   1,   1,   0,   1,    0,    1},
                /*1100 */ {   0,   0,   1,   0,    1,    0},
                /*1111P*/ {   0,   0,   1,   0,    1,    0},
                /*1111N*/ {   1,   0,   0,   1,    0,    1}
        };
        
        return new IntRowVector(1, 1, 0, 1, 0, 1)
                .modMultiply(new IntMatrix(matrix, 6).modPow(width - 2, mod), mod)
                .modMultiply(new IntColVector(0, 0, 1, 0, 0, 1), mod)
                .at(0, 0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem237());
    }

}
