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

import static java.lang.Math.exp;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 17.01.2010
 */
class Problem152 implements Problem {

    /**
     * TODO Method documentation
     * 
     * 35 > 1 in    4137 ms
     * 36 > 1 in    7813 ms
     * 37 > 1 in   14481 ms
     * 38 > 1 in   26867 ms
     * 40 > 1 in   16319 ms
     * 41 > 1 in   30301 ms
     * 42 > 1 in   58079 ms
     * 43 > 1 in  125921 ms
     * 44 > 1 in  262897 ms
     * 45 > 3 in  436402 ms
     * 46 > 3 in 1898423 ms
     * 
     * a/b + x/y = 1/2 <=> 2(ay + xb) = yb
     * 
     * 1/a^2 + 1/b^2 = (a^2+b^2)/(a^2*b^2)
     * 
     * 1/a^2 + 1/(a + 1)^2 = (2a(a+1)+1)/(a(a+1))^2 = 2/(a(a+1))+1/((a(a+1))^2)
     * 
     * 1/a + 1/b + 1/c = (bc + ac + ab) / abc
     * 
     * nicht 32
     * nicht 64
     * wenn 16, dann auch einer von (48 oder 80)
     * wenn 48, dann auch einer von (16 oder 80)
     * wenn 80, dann auch einer von (16 oder 48)
     * wenn 25, dann auch einer von (50 oder 75)
     * wenn 50, dann auch 25
     * wenn 75, dann auch 25
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 17.01.2010
     */
    @Override
    public Object solve() {

        final int max = 80;
        final double[][] f = new double[max - 1][2];
        
        double prod = 1;
        
        for (int i = f.length; i-- > 0; ) {
            f[i][0] = exp(1.0 / ((i + 2) * (i + 2)));
            System.out.println((i + 2) + " " + f[i][0]);
            prod *= f[i][0];
            f[i][1] = prod;
        }
        System.out.println(exp(0.5));
        
        return count(f, 0, 1.0, exp(0.5));
    }
    
    private BigInteger lcm(BigInteger a, BigInteger b) {
    
        return a.divide(a.gcd(b)).multiply(b);
    }
    
    private int count(double[][] fractions, int i, double cur, double target) {

        if (cur == target) return 1;
        
        int count = 0;
        
        for (; i < fractions.length && cur * fractions[i][0] > target; ++i);
        for (; i < fractions.length && cur * fractions[i][1] >= target; ++i) {
            count += count(fractions, i + 1, cur * fractions[i][0], target);
        }

        return count;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 17.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem152());
    }

}
