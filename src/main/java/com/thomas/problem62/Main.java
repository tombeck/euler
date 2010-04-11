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
package com.thomas.problem62;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thomas.util.Digit;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.11.2009
 */
public class Main {

    private static final Comparator<BigInteger> COMP = new Comparator<BigInteger>() {

        @Override
        public int compare(BigInteger o1, BigInteger o2) {

            return getHash(o1).subtract(getHash(o2)).signum();
        }
        
        private BigInteger getHash(BigInteger n) {
            
            BigInteger hash = BigInteger.ONE;

            while(!n.equals(BigInteger.ZERO)) {
                BigInteger[] tmp = n.divideAndRemainder(BigInteger.TEN);
                n = tmp[0];
                hash = hash.multiply(BigInteger.valueOf(Digit.PRIME[tmp[1].intValue()]));
            }

            return hash;
        }
        
    };
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.11.2009
     */
    public static void main(String[] args) {

        int low = 1; 

        for (int l = 1; l < 13; ++l) {
            int high = first(l);
            List<BigInteger> cubes = new ArrayList<BigInteger>();
            for (int n = low; n < high; ++n) {
                cubes.add(BigInteger.valueOf(n).pow(3));
            }
            Collections.sort(cubes, COMP);
            
            final List<BigInteger> buffer = new ArrayList<BigInteger>();
            
            BigInteger prev = BigInteger.ZERO;
            for (BigInteger cube : cubes) {
                if (COMP.compare(cube, prev) == 0) {
                    buffer.add(cube);
                } else {
                    checkBuffer(buffer);
                    buffer.clear();
                    buffer.add(cube);
                }
                prev = cube;
            }
            checkBuffer(buffer);
            System.out.println(low + ", " + high);
            low = high;
        }
    }

    private static int first(int l) {
    
        int ret = (int)Math.ceil(Math.exp((l * Math.log(10)) / 3));
        
        return (l % 3 == 0) ? ret - 1 : ret;
    }
    
    private static void checkBuffer(List<BigInteger> buffer) {
        
        if (buffer.size() >= 5) System.out.println(buffer);
    }

}
