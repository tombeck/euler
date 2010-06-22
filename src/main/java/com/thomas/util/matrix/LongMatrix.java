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
package com.thomas.util.matrix;

import java.util.Arrays;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class LongMatrix {

    public static LongMatrix one(int m) {
        
        final long[][] ret = new long[m][m];
        
        for (int i = 0; i < m; ++i) {
            ret[i][i] = 1;
        }
        
        return new LongMatrix(ret);
    }
    
    private final long[][] mx;
    private final int m;
    private final int n;

    public LongMatrix(int n, long[]... mx) {

        this.mx = mx;
        this.m = mx.length;
        this.n = n;
    }

    public LongMatrix(long[]... mx) {

        this(mx.length, mx);
    }

    public LongMatrix multiply(LongMatrix other) {
    
        if (this.n != other.m) throw new IllegalArgumentException();
        
        final long[][] ret = new long[this.m][other.n];
        
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < other.n; ++j) {
                for (int k = 0; k < this.n; ++k) {
                    ret[i][j] += this.mx[i][k] * other.mx[k][j];
                }
            }
        }
        
        return new LongMatrix(other.n, ret);
    }
    
    public LongMatrix modMultiply(LongMatrix other, long mod) {
        
        if (this.n != other.m) throw new IllegalArgumentException();
        
        final long[][] ret = new long[this.m][other.n];
        
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < other.n; ++j) {
                for (int k = 0; k < this.n; ++k) {
                    
                    final long prod = (ret[i][j] + modMultiply(this.mx[i][k] / X, this.mx[i][k] % X, other.mx[k][j] / X, other.mx[k][j] % X, mod)) % mod;
                    
                    if (prod < 0) {
                        System.out.println(String.format("%s, %s, %s, %s", ret[i][j], this.mx[i][k], other.mx[k][j], mod));
                        System.exit(0);
                    }
                    ret[i][j] = prod;
                }
            }
        }
        
        return new LongMatrix(other.n, ret);
    }
    
    private static final long X = 100000L;
    private static final long XX = 10000000000L;
    
    private long modMultiply(long a, long b, long c, long d, long mod) {
    
        return (((a * c) % mod) * (XX % mod) + ((a * d + b * c) % mod) * (X % mod) + ((b * d) % mod)) % mod;  
    }
    
    public LongMatrix mod(long p) {
    
        final long[][] ret = new long[this.m][this.n];
        
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < this.n; ++j) {
                ret[i][j] = this.mx[i][j] % p;
            }
        }
        
        return new LongMatrix(this.n, ret);
    }
    
    public LongMatrix modPow(long exp, long mod) {
    
        if (exp == 0) return one(this.m);
        if (exp == 1) return this.mod(mod);
        if ((exp & 1) != 0) return this.modMultiply(this.modPow(exp - 1, mod), mod);
        
        final LongMatrix tmp = this.modPow(exp >> 1, mod);
        
        return tmp.modMultiply(tmp, mod);
    }
    
    public long at(int i, int j) {
        
        return this.mx[i][j];
    }
    
    @Override
    public String toString() {
    
        final StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < this.m; ++i) {
            builder.append(Arrays.toString(this.mx[i])).append("\n");
        }
        
        return builder.toString();
    }
    
}
