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

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 21.03.2010
 */
class Problem129 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 21.03.2010
     */
    @Override
    public Object solve() {

        System.out.println(NumberUtils.totient(7 * 9));
        System.out.println(NumberUtils.totient(41 * 9));
        
        System.out.println(BigInteger.TEN.pow(240).subtract(BigInteger.ONE).divide(BigInteger.valueOf(9)).mod(BigInteger.valueOf(41)));
        System.out.println(PrimeUtils.getPrimeFactors(240));
        System.out.println(PrimeUtils.getPrimeFactors(36));
        return null;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 21.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem129());
    }

}
