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
package com.thomas.problem80;

import static com.thomas.util.Digit.sumOfDigits;
import static java.lang.Integer.highestOneBit;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.DOWN;

import java.math.BigDecimal;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.10.2009
 */
class Problem80 implements Problem {

    private static final BigDecimal TWO = valueOf(2);

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 06.04.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int i = 1, n = 1; n < 100; ++i) {
            for (int j = n + 1, k = (n += 2 * i + 1); j < k; ++j) {
                sum += sumOfDigits(sqrt(j, 100).toPlainString().replaceAll("\\.", "").substring(0, 100));
            }
        }
        
        return sum;
    }
    
    private BigDecimal sqrt(int n, int scale) {
        
        final BigDecimal s = valueOf(n);
        
        BigDecimal sqrt = valueOf(highestOneBit(n) >> 1); // initial guess
        
        for (BigDecimal i; !(i = s.divide(sqrt, scale, DOWN).add(sqrt).divide(TWO, scale, DOWN)).equals(sqrt); sqrt = i) { /**/ }

        return sqrt;
    }

    /**
     * TODO Method documentation 40886
     * 
     * @param args
     * @author Thomas
     * @since 31.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem80());

    }

}
