/**
 * $Id$
 *
 * Copyright (c) 2015 Thomas Beckmann
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

package com.thomas.problem3xx.problem38x;

import static com.thomas.util.PrimeUtils.isPrime;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 20.03.2015
 */
public class Problem387 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        long sum = 0;
        
        for (long harshad = 1; harshad < 10; ++harshad) {
            sum += allHarshad(harshad, harshad, 13);
        }

        return sum;
    }

    long allHarshad(long harshad, long digitSum, long remainingDigits) {
        
        long sum = 0;
        
        if (remainingDigits > 1) {
            for (int d = 0; d < 10; ++d) {
                
                final long nextHarshad = harshad * 10 + d;
                final long nextDigitSum = digitSum + d;
                
                if (nextHarshad % nextDigitSum == 0) {
                    sum += allHarshad(nextHarshad, nextDigitSum, remainingDigits - 1);
                }
            }
        }
        if (isPrime(harshad / digitSum)) {
            for (long d = 1; d < 10; d += 2) {
        
                final long x = harshad * 10 + d;
                
                if (isPrime(x)) {
                    sum += x;
                }
            }
        }
        
        return sum;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem387());
    }

}
