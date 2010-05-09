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
public class IntMatrix {

    public static IntMatrix one(int m) {
        
        final int[][] ret = new int[m][m];
        
        for (int i = 0; i < m; ++i) {
            ret[i][i] = 1;
        }
        
        return new IntMatrix(ret, m, m);
    }
    
    private final int[][] mx;
    private final int m;
    private final int n;

    public IntMatrix(int[][] mx, int m, int n) {

        this.mx = mx;
        this.m = m;
        this.n = n;
    }

    public IntMatrix(int[][] mx, int m) {

        this(mx, m, m);
    }

    public IntMatrix multiply(IntMatrix other) {
    
        if (this.n != other.m) throw new IllegalArgumentException();
        
        final int[][] ret = new int[this.m][other.n];
        
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < other.n; ++j) {
                for (int k = 0; k < this.n; ++k) {
                    ret[i][j] += this.mx[i][k] * other.mx[k][j];
                }
            }
        }
        
        return new IntMatrix(ret, this.m, other.n);
    }
    
    public IntMatrix modMultiply(IntMatrix other, int mod) {
        
        if (this.n != other.m) throw new IllegalArgumentException();
        
        final int[][] ret = new int[this.m][other.n];
        
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < other.n; ++j) {
                for (int k = 0; k < this.n; ++k) {
                    ret[i][j] = (int)((ret[i][j] + (long)this.mx[i][k] * other.mx[k][j]) % mod);
                }
            }
        }
        
        return new IntMatrix(ret, this.m, other.n);
    }
    
    public IntMatrix mod(int p) {
    
        final int[][] ret = new int[this.m][this.n];
        
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < this.n; ++j) {
                ret[i][j] = this.mx[i][j] % p;
            }
        }
        
        return new IntMatrix(ret, this.m, this.n);
    }
    
    public IntMatrix modPow(long exp, int mod) {
    
        if (exp == 0) return one(this.m);
        if (exp == 1) return this.mod(mod);
        if ((exp & 1) != 0) return this.modMultiply(this.modPow(exp - 1, mod), mod);
        
        final IntMatrix tmp = this.modPow(exp >> 1, mod);
        
        return tmp.modMultiply(tmp, mod);
    }
    
    public int at(int i, int j) {
        
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
