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
package com.thomas.problem0xx.problem6x;

import static com.thomas.util.Digit.sumOfDigits;
import static java.math.BigInteger.ONE;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 23.11.2009
 */
class Problem65 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {
        
        BigInteger[] num = {BigInteger.valueOf(2), BigInteger.valueOf(3)}; // n1, n2
        
        for (int n = 3; n <= 100; ++n) {
            /*
             * continued fraction of e = [2;1,2,1,1,4,1,1,6,1,...,1,(n / 3) * 2,1,...]
             * and num(n) = a(n)*num(n-1)+num(n-2)
             */
            num = new BigInteger[] {num[1], a(n).multiply(num[1]).add(num[0])};
        }
        
        return sumOfDigits(num[1]);
    }
    
    private BigInteger a(int n) {
    
        return n % 3 == 0 ? BigInteger.valueOf((n / 3) * 2) : ONE;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem65());
    }

}
