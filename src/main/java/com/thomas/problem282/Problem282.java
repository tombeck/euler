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

        final long mod = pow(14L, 8);
        
        long sum = 1;
        
        for (int n = 1; n <= 6; ++n) {
            sum = modAdd(sum, modAckermann(n, mod), mod);
        }

        return sum;
    }

    private long modAckermann(int n, long mod) {
    
        return modAdd(mod2UpArrowN(n - 2, n + 3, mod), -3, mod);
    }
    
    private long mod2UpArrowN(int n, int b, long mod) {
    
        switch (n) {
        case -1:
            return modAdd(2, b, mod);
        case 0:
            return modMul(2, b, mod);
        case 1:
            return modPow(2, b, mod);
        case 2:
            return mod2UpArrow2(b, mod);
        default:
            return mod2UpArrow2(mod);
        }
    }
    
    private long mod2UpArrow2(int b, long m) {
        
        if (b == 1) return 2 % m;
        
        int p = 0;
        long r = m;
        
        for (; (r & 1) == 0; r >>= 1) ++p;

        final long t = totient(r);
        
        return (1L << p) * modPow(2, modAdd(mod2UpArrow2(b - 1, t), -p, t), r);
    }
    
    private long mod2UpArrow2(long m) {
        
        if (m == 1) return 0;

        int p = 0;
        long r = m;
        
        for (; (r & 1) == 0; r >>= 1) ++p;

        final long t = totient(r);
        
        return (1L << p) * modPow(2, modAdd(mod2UpArrow2(t), -p, t), r);
    }
    
    private long modAdd(long a, long b, long m) {
    
        return ((a % m) + (b % m)) % m; 
    }
    
    private long modMul(long a, long b, long m) {
        
        return ((a % m) * (b % m)) % m; 
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
