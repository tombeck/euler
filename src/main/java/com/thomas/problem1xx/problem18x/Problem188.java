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
package com.thomas.problem1xx.problem18x;

import static java.math.BigInteger.TEN;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 16.01.2010
 */
class Problem188 implements Problem {

    /**
     * a^^k mod 10^8 = a^(a^^(k-1)) mod 10^8
     *               = a^(a^^(k-1) mod 1250000) mod 10^8 found by experimentation for a = 1777
     *               = a^(a^^(k-1) mod 1250000 * 8) mod 10^8
     *               = a^(a^^(k-1) mod 10^8) mod 10^8
     *               
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 16.01.2010
     */
    @Override
    public BigInteger solve() {

        final BigInteger m = TEN.pow(8);
        final BigInteger a = BigInteger.valueOf(1777);
        
        BigInteger x = a;
        
        for (int k = 1; k < 1855; ++k) x = a.modPow(x, m);

        return a.modPow(x, m);
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 16.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem188());
    }

}
