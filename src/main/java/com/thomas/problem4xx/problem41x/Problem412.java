/**
 * $Id$
 * 
 * Copyright (c) 2014 Thomas Beckmann
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

package com.thomas.problem4xx.problem41x;

import static com.thomas.util.NumberUtils.modInv;
import static com.thomas.util.NumberUtils.modPow;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 14.03.2014
 */
public class Problem412 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        long lc = 1;

        for (int i = 1; i <= 75000000; ++i) {
            lc = (lc * i) % 76543217L;
        }
        for (int i = 1; i < 5000; ++i) {
            lc = (lc * modInv(modPow(i, 2*i, 76543217L), 76543217L)) % 76543217L;
            lc = (lc * modInv(modPow(i+5000, 2*(5000-i), 76543217L), 76543217L)) % 76543217L;
            lc = (lc * modInv(modPow(i+10000, i, 76543217L), 76543217L)) % 76543217L;
            lc = (lc * modInv(modPow(i+14999, 5001-i, 76543217L), 76543217L)) % 76543217L;
        }
        lc = (lc * modInv(modPow(5000, 10000, 76543217L), 76543217L)) % 76543217L;
        lc = (lc * modInv(modPow(19999, 1, 76543217L), 76543217L)) % 76543217L;

        return lc;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem412());
    }

}
