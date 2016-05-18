/**
 * $Id$
 *
 * Copyright (c) 2016 Thomas Beckmann
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

package com.thomas.problem5xx.problem53x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @since 17.05.2016
 */
public class Problem539 implements Problem {

    static final long MOD = 987654321;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        return S(1000000000000000000L);
    }

    long S(long n) {
        
        if (n < 2) return 1;
            
        if ((n % 2) == 0) return (S(n - 1) + P(n))%MOD;
            
        return (1 + ((n - 1)%MOD) * ((3 + n/2)%MOD) - 4 * S(n/2))%MOD;
    }
    
    long P(long n) {
        
        if (n == 1) return 1;
        
        return 2 * (1 + n/2 - P(n/2));
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem539());
    }

}
