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
package com.thomas.problem239;

import static java.lang.Math.round;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem239 implements Problem {

    /**
     * 25 primes <= 100
     * 
     * {@inheritDoc}
     */
    @Override
    public Double solve() {

        return round((count(22, 75) * 2300) / factorial(100) * 1000000000000L)/1000000000000.0;
    }

    private double count(int fixed, int free) {
        
        if (fixed-- == 0) return factorial(free);
        
        double count = free * count(fixed, free);
        
        return fixed > 0 ? count + fixed * count(fixed - 1, free + 1) : count;
    }

    private double factorial(int n) {
    
        double f = 1;
        
        for (; n > 1; --n) f*= n;
        
        return f;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem239());
    }

}
