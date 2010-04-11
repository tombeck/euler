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
package com.thomas.problem277;

import static com.thomas.util.rational.LongRational.ONE;
import static com.thomas.util.rational.LongRational.ZERO;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.rational.LongRational;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 07.02.2010
 */
class Problem277 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 07.02.2010
     */
    @Override
    public BigInteger solve() {

        final LongRational[] a = U(D(D(D(U(d(d(d(D(D(U(D(D(d(d(D(d(D(d(d(D(D(U(D(D(d(U(U(D(d(new LongRational[] {ONE, ZERO}))))))))))))))))))))))))))))));
        
        final long x = (a[0].numerator() % a[0].denominator());
        final long y = (a[0].denominator() - (a[1].numerator() % a[0].denominator()));
        final long z = (a[0].denominator());
        
        long i = 1;

        for (long m = x; m % z != y; m += x) i += 1;

        final BigInteger m = BigInteger.valueOf(1000000000000000L).multiply(BigInteger.valueOf(a[0].denominator())).subtract(BigInteger.valueOf(a[1].numerator())).divide(BigInteger.valueOf(a[0].numerator())).subtract(BigInteger.valueOf(i)).divide(BigInteger.valueOf(a[0].denominator())).add(BigInteger.ONE);

        return BigInteger.valueOf(a[0].numerator()).multiply(BigInteger.valueOf(i).add(m.multiply(BigInteger.valueOf(a[0].denominator())))).add(BigInteger.valueOf(a[1].numerator())).divide(BigInteger.valueOf(a[0].denominator()));
    }

    private LongRational[] D(LongRational[] a) {
        
        return new LongRational[] {new LongRational(3, 1).multiply(a[0]), new LongRational(3, 1).multiply(a[1])};
    }
    
    private LongRational[] U(LongRational[] a) {
        
        return new LongRational[] {new LongRational(3, 4).multiply(a[0]), new LongRational(3, 4).multiply(a[1]).add(new LongRational(-1, 2))};
    }
    
    private LongRational[] d(LongRational[] a) {
        
        return new LongRational[] {new LongRational(3, 2).multiply(a[0]), new LongRational(3, 2).multiply(a[1]).add(new LongRational(1, 2))};
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 07.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem277());
    }

}
