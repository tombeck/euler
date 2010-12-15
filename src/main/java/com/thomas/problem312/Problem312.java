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
package com.thomas.problem312;

import static com.thomas.util.NumberUtils.modPow;
import static com.thomas.util.NumberUtils.pow;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 11.12.2010
 */
public class Problem312 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final int n = 10000;
        
        long c1 = (2 * modPow(12L, ((modPow(3, n - 2, 24 * pow(13, 3)) + (24 * pow(13, 3) - 5)) % (24 * pow(13, 3))) / 2,  pow(13, 4))) % pow(13, 4);
        long c2 = (2 * modPow(12L, ((3 * modPow(3L, (48 * c1 + (48 * pow(13, 4) - 3)) % (48 * pow(13, 4)), 8 * pow(13, 5)) + (24 * pow(13, 5) - 5)) % (24 * pow(13, 5))) / 2, pow(13, 6))) % pow(13, 6);
        long c3 = (8 * modPow(12L, ((3 * modPow(3L, (48 * c2 + (48 * pow(13, 6) - 3)) % (48 * pow(13, 6)), 8 * pow(13, 7)) + (24 * pow(13, 7) - 3)) % (24 * pow(13, 7))) / 2, pow(13, 8))) % pow(13, 8);
        
        return c3;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem312());
    }

}
