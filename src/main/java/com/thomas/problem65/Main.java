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
package com.thomas.problem65;

import static com.thomas.util.Digit.sumOfDigits;
import static java.math.BigInteger.valueOf;

import java.math.BigInteger;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 23.11.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.11.2009
     */
    public static void main(String[] args) {

        BigInteger prev = valueOf(2); // n = 1
        BigInteger cur = valueOf(3);  // n = 2
        
        for (int n = 3; n <= 100; ++n) {
            
            /*
             * continued fraction of e = [2;1,2,1,1,4,1,1,6,1,...,1,(n / 3) * 2,1,...]
             * and num(n+1) = a(n+1)*num(n)+num(n-1)
             */
            final BigInteger next = valueOf(n % 3 == 0 ? (n / 3) * 2 : 1).multiply(cur).add(prev);
            
            prev = cur;
            cur = next;
        }
        
        System.out.println(sumOfDigits(cur.toString()));
    }

}
