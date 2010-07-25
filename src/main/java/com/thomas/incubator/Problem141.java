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
package com.thomas.incubator;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 03.04.2010
 */
class Problem141 implements Problem {

    /**
     * q * d + r = m^2 = n, 0 < r < d < q
     * 
     * q * r = d^2 => q = d^2 / r,   r = d^2 / q
     * 
     * q^2 * d + d^2 = m^2 * q   <=>   d * (q^2 + d) = m^2 * q
     * 
     * d^3 + r^2 = m^2 * r   <=>   d^3 = r * (m^2 - r)
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 03.04.2010
     */
    @Override
    public Long solve() {

        final long max = 1000000;
        final long max2 = max * max;
        long sum = 0;

//        for (long r = 1; r < max - 1; ++r) {
//            for (long d = r + 1, d2, q, n; (n = (q = (d2 = d * d) / r) * d + r) < max2; ++d) {
//                if (d2 % r == 0) {
//                    if (NumberUtils.isSquare(n)) {
//                        sum += n;
//                        System.out.println(String.format("%s * %s + %s = %s", q, d, r, n));
//                        break;
//                    }
//                }
//            }
//        }
        for (int d = 2; d < max; ++d) {
            final long d2 = (long)d * d;
            final int min = (int)((max / 2.0 - Math.sqrt(Math.pow(max / 2.0, 2) - (double)(d2 * d) / (max * max))) * max) + 1;
            for (int r = min; r < d; ++r) {
                if (d2 % r == 0) {
                    final long q = d2 / r;
                    final long n = q * d + r;
                    if (NumberUtils.isSquare(n)) {
                        sum += n;
                        System.out.println(String.format("%s * %s + %s = %s^2", q, d, r, (int)Math.sqrt(n)));
                        break;
                    }
                }
            }
        }
//        for (long m = 1, n = 1; m < max; n += (m++ << 1) + 1)  {
//            for (long d = (long)Math.ceil(Math.cbrt(n + 1)), q; (q = n / d) > d; ++d) {
//                
//                final long r = n % d;
//                
//                if (q * r == d * d) {
//                    sum += n;
//                    System.out.println(String.format("%s * %s + %s = %s", d, q, r, n));
//                    break;
//                }
//            }
//        }
        
        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 03.04.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem141());
    }

}
