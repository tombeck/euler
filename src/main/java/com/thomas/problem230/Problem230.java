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
package com.thomas.problem230;

import static com.thomas.util.ArrayUtils.append;
import static com.thomas.util.NumberUtils.pow;
import static java.lang.Character.digit;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 23.01.2010
 */
class Problem230 implements Problem {

    static String A =
        "14159265358979323846264338327950288419716939937510" +
        "58209749445923078164062862089986280348253421170679";
    static String B =
        "82148086513282306647093844609550582231725359408128" +
        "48111745028410270193852110555964462294895493038196";
    
    /**
     * TODO Method documentation 850481152593119296
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 23.01.2010
     */
    @Override
    public Long solve() {

        final int max = 17;
        
        long sum = 0;
        long[] a = {A.length(), B.length()};
        long m = (127 + 19 * max) * pow(7L, max) - 1;

        for (int i; a[i = a.length - 1] <= m; ) a = append(a, a[i - 1] + a[i]);

        for (int n = max, i = 1; n >= 0; --n) {
            for (m = (127 + 19 * n) * pow(7L, n) - 1 ; a[i] <= m; ++i) { /**/ }
            while (i > 1) {
                if (m >= a[--i - 1]) m -= a[i - 1];
                else --i;
            }
            sum = sum * 10 + digit((i == 0 ? A : B).charAt((int)m), 10);
        }

        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem230());
    }

}
