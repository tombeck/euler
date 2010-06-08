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
package com.thomas.problem156;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 29.12.2009
 */
public class Problem156 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 29.12.2009
     */
    @Override
    public Long solve() {

        long sum = 0;

        for (int d = 1; d < 10; ++d) {
            for (int i = 0; i < 11; ++i) {
                sum +=sum(d, 0, i, 0, 0);
            }
        }
        
        return sum;
    }

    private long sum(int d, long n, int i, int overflow, int mul) {
        
        if (i < 0) return (overflow == mul) ? n : 0;

        long sum = 0;
        
        for (int ni = (n == 0 ? 1 : 0); ni < 10; ++ni) {

            final int fi = ni * (10 - i - mul) + (10 * overflow) - (ni > d ? 10 : 0);
            
            if (-10 < fi && fi < 100) {
                sum += sum(d, n * 10 + ni, i - 1, fi, ni == d ? mul + 10 : mul);
            }
        }
        
        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 29.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem156());
    }

}
