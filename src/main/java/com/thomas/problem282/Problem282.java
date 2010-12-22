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
package com.thomas.problem282;

import static com.thomas.util.NumberUtils.modPow;
import static com.thomas.util.NumberUtils.pow;
import static com.thomas.util.NumberUtils.totient;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.03.2010
 */
public class Problem282 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 14.03.2010
     */
    @Override
    public Long solve() {

        final int mod = pow(14, 8);
        
        long sum = 1;
        
        sum += (2 + 4) - 3;
        sum += (2 * 5) - 3;
        sum += pow(2, 6) - 3;
        sum += mod2UpArrow2(7, mod) - 3;
        sum += mod2UpArrow2Infinity(mod) - 3;
        sum += mod2UpArrow2Infinity(mod) - 3;

        return sum % mod;
    }

    private long mod2UpArrow2(int b, int m) {
        
        if (b == 1) return 2 % m;
        
        int p = 0;
        
        while ((m >> p & 1) == 0) ++p;

        final int t = totient(m >> p);
        
        return modPow(2, (mod2UpArrow2(b - 1, t) - p) % t + p, m);
    }
    
    private long mod2UpArrow2Infinity(int m) {
        
        if (m == 1) return 0;

        int p = 0;
        
        while ((m >> p & 1) == 0) ++p;

        final int t = totient(m >> p);
        
        return modPow(2, (mod2UpArrow2Infinity(t) - p) % t + p, m);
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem282());
    }

}
