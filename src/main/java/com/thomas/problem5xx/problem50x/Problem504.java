/**
 * $Id$
 *
 * Copyright (c) 2015 Thomas Beckmann
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
package com.thomas.problem5xx.problem50x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 26.05.2015
 */
public class Problem504 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final int m = 100;
        final int[][] gcd = new int[m+1][m+1];
        final boolean[] isSquare = new boolean[2*m*(m - 1) + 2];
        
        for (int i = 0, ii = 0; (ii += 2*(i++) + 1) < isSquare.length; ) {
            isSquare[ii] = true;
        }
        for (int d = 1; d <= m; ++d) {
            for (int i = d; i <= m; i += d) {
                for (int j = d; j <= m; j += d) {
                    gcd[i][j] = d;
                }
            }
        }
        int sum = 0;
        
        for (int a = 1; a <= m; ++a) {
            for (int c = 1; c < a; ++c) {
                for (int b = 1; b <= m; ++b) {
                    for (int d = 1; d < b; ++d) {
                        if (isSquare[((a+c)*(b+d) - gcd[a][b] - gcd[b][c] - gcd[c][d] - gcd[d][a])/2 + 1]) {
                            sum += 4;
                        }
                    }
                    if (isSquare[b*(a+c) - gcd[a][b] - gcd[b][c] + 1]) {
                        sum += 4;
                    }
                }
                if (isSquare[2*(a*c - gcd[a][c]) + 1]) {
                    sum += 2;
                }
            }
            if (isSquare[2*a*(a - 1) + 1]) {
                sum += 1;
            }
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem504());
    }

}
