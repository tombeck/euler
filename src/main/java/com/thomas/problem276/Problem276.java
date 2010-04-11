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
package com.thomas.problem276;

import com.thomas.Util;
import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 07.03.2010
 */
class Problem276 implements Problem {

    /**
     * TODO Method documentation
     * 
     * see {@link http://www.research.att.com/~njas/sequences/A051493 }
     * see {@link http://mathworld.wolfram.com/MoebiusTransform.html }
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 07.03.2010
     */
    @Override
    public Object solve() {

        final int max = 20;//10000000;
        
        long count = 0;

        for (int p = 0; p <= max; ++p) {

            for (int a = 1, am = p / 3; a <= am; ++a) {
                for (int b = Math.max(a, (p - 2 * a) / 2 + 1), bm = (p - a) / 2; b <= bm; ++b) {
                    int c = p - (a + b);
                    if (Util.gcd(a, Util.gcd(b, c)) == 1) {
                        System.out.println(p + ": " + a + ", " + b + ", " + c);
                        ++count;
                    }
                }
            }
            //System.out.println(p + ": " + count);
        }

        return count;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 07.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem276());
    }

}
