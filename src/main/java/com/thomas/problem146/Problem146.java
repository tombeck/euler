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
package com.thomas.problem146;

import static java.math.BigInteger.ONE;

import java.math.BigInteger;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 05.03.2010
 */
public class Problem146 implements Problem {

    static final BigInteger TWO = BigInteger.valueOf(2);
    static final BigInteger THREE = BigInteger.valueOf(3);
    static final BigInteger SEVEN = BigInteger.valueOf(7);
    static final BigInteger NINE = BigInteger.valueOf(9);
    static final BigInteger THIRTEEN = BigInteger.valueOf(13);
    static final BigInteger FIVTEEN = BigInteger.valueOf(15);
    static final BigInteger NINETEEN = BigInteger.valueOf(19);
    static final BigInteger TWENTY_ONE = BigInteger.valueOf(21);
    static final BigInteger TWENTY_FIVE = BigInteger.valueOf(25);
    static final BigInteger TWENTY_SEVEN = BigInteger.valueOf(27);
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 05.03.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (long i = 10; i < 150000000; i += 10) {
            
            final long mod7 = i % 7;
            if (i % 3 == 0) continue;
            if (mod7 != 3 && mod7 != 4) continue;
            if (i % 13 == 0) continue;
            
            final BigInteger sqr = BigInteger.valueOf(i * i);
            
            if (!isProbablePrime(sqr.add(ONE))) continue;
            if (!isProbablePrime(sqr.add(THREE))) continue;
            if (!isProbablePrime(sqr.add(SEVEN))) continue;
            if (!isProbablePrime(sqr.add(NINE))) continue;
            if (!isProbablePrime(sqr.add(THIRTEEN))) continue;
            if (isProbablePrime(sqr.add(NINETEEN))) continue;
            if (isProbablePrime(sqr.add(TWENTY_ONE))) continue;
            if (!isProbablePrime(sqr.add(TWENTY_SEVEN))) continue;

            sum += i;
        }

        return sum;
    }

    private boolean isProbablePrime(BigInteger p) {
    
        return TWO.modPow(p.subtract(ONE), p).equals(ONE);
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 05.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem146());
    }

}
