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
package com.thomas.problem2xx.problem21x;

import static java.lang.Math.sqrt;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 23.12.2009
 */
public class Problem210 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 23.12.2009
     */
    @Override
    public Long solve() {

        final long n = 1000000000 / 8;

        long sum = 0;

        for (long y = n, yy = y * y, x = n, max = n * n; y > 0; yy -= 2 * y-- - 1) {
            if (yy < max) max -= 2 * x++ + 1;
            sum += x;
        }
        
        return 2 * n * (46 * n - 5) + 6 + 4 * (long)(n * sqrt(2)) + 8 * sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem210());
    }

}
