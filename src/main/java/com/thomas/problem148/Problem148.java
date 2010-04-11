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
package com.thomas.problem148;

import static com.thomas.util.NumberUtils.pow;
import static java.lang.Math.ceil;
import static java.lang.Math.log;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 28.12.2009
 */
class Problem148 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 28.12.2009
     */
    @Override
    public Long solve() {

        final int max = 1000000000;
        final int exp = (int)ceil(log(max) / log(7));
        final long[] sum = new long[exp];
        
        sum[0] = 1;
        for (int i = 1; i < exp; ++i) sum[i] = 28 * sum[i - 1]; 

        return sum(sum, max) / 2;
    }

    private long sum(long[] sum, int max) {
        
        if (max <= 7) return max * (max + 1);
        
        final int exp = (int)ceil(log(max) / log(7)) - 1;
        final int pow = pow(7, exp);
        final int i = max / pow;

        return (i + 1) * (i * sum[exp] + sum(sum, max % pow));
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 28.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem148());
    }

}
