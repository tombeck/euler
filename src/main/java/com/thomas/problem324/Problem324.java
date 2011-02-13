/**
 * $Id$
 *
 * Copyright (c) 2011 Thomas Beckmann
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
package com.thomas.problem324;

import java.math.BigInteger;
import java.util.Arrays;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 13.02.2011
 */
public class Problem324 implements Problem {

    static class IntMatrix {

        final int m;
        final int n;
        final int[][] mx;

        public IntMatrix(int n, int[]... mx) {

            this.mx = mx;
            this.m = mx.length;
            this.n = n;
        }

        public IntMatrix(int[]... mx) {

            this(mx.length, mx);
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
            
            return new IntMatrix(other.n, ret);
        }
        
        public IntMatrix modMultiply(IntMatrix other, int mod) {
            
            if (this.n != other.m) throw new IllegalArgumentException();
            
            final int[][] ret = new int[this.m][other.n];
            
            for (int i = 0; i < this.m; ++i) {
                for (int j = i; j < other.n; ++j) {
                    for (int k = 0; k < this.n; ++k) {
                        ret[i][j] = ret[j][i] = (int)((ret[i][j] + (long)this.mx[i][k] * other.mx[k][j]) % mod);
                    }
                }
            }
            
            return new IntMatrix(other.n, ret);
        }

        public IntMatrix modPow(long exp, int mod) {
        
            if (exp == 1) return this;
            if ((exp & 1) != 0) return this.modMultiply(this.modPow(exp - 1, mod), mod);
            
            final IntMatrix tmp = this.modPow(exp >> 1, mod);
            
            return tmp.modMultiply(tmp, mod);
        }
        
        public IntMatrix modPow(BigInteger exp, int mod) {
            
            if (exp.equals(BigInteger.ONE)) return this;
            if (exp.and(BigInteger.ONE).equals(BigInteger.ONE)) return this.modMultiply(this.modPow(exp.subtract(BigInteger.ONE), mod), mod);
            
            final IntMatrix tmp = this.modPow(exp.shiftRight(1), mod);
            
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final int[][] matrix = new int[1 << 9][1 << 9];
        
        fill(new int[3][3], 0, matrix);
        
        IntMatrix m = new IntMatrix(matrix).modPow(2, 100000007);
        
        int[] a = new int[1 << 9]; a[0] = 1;
        int[] res;
        
        for (int j = 0; ; ++j) {
            int[] b = a.clone();
            res = new IntMatrix(a.length, a).multiply(m).mx[0];
            
            for (int i = 0; i < res.length; ++i) {
                if (res[i] + a[i] > 0) a[i] = 1;
            }
            if (Arrays.equals(a, b)) break;
        }
        
        int count = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > 0) ++count;
        }

        int[][] n = new int[count][count];
        
        for (int i1 = 0, i2 = 0; i1 < m.mx.length; ++i1) {
            if (a[i1] > 0) {
                for (int j1 = 0, j2 = 0; j1 < m.mx[i1].length; ++j1) {
                    if (a[j1] > 0) {
                        n[i2][j2] = m.mx[i1][j1];
                        ++j2;
                    }
                }
                ++i2;
            }
        }

        IntMatrix m3 = new IntMatrix(n);
        System.out.println(m3);
        IntMatrix m2 = m3.modPow(BigInteger.TEN.pow(99).multiply(BigInteger.valueOf(5)), 100000007);
        for (int i = 0; i < 99; ++i) {
            System.out.println(i);
            m2 = m2.modPow(BigInteger.TEN.pow(100), 100000007);
        }

        return m2.at(0, 0);
    }

    private int makeBottomProfile(int[][] board) {
        
        int profile = 0;
        
        for (int i = 0; i < 9; ++i) {
            
            int x = i % 3;
            int y = i / 3;
            
            if (board[x][y] == 0) profile |= 1 << i;
        }

        return profile;
    }
    
    private int makeTopProfile(int[][] board) {
        
        int profile = 0;
        
        for (int i = 0; i < 9; ++i) {
            
            int x = i % 3;
            int y = i / 3;
            
            if (board[x][y] == 1) profile |= 1 << i;
        }

        return profile;
    }

    private void fill(int[][] board, int i, int[][] matrix) {
        
        if (i == 9) {
            
            final int bottom = makeBottomProfile(board);
            final int top = makeTopProfile(board);
            
            matrix[bottom][top] += 1;

            return;
        }
        
        int x = i % 3;
        int y = i / 3;
        
        if ((y > 0 && board[x][y - 1] == 4) && (x > 0 && board[x - 1][y] == 3)) return;
        if (y > 0 && board[x][y - 1] == 4) {
            if (x > 0 && board[x - 1][y] == 3) return;
            board[x][y] = 2;
            fill(board, i + 1, matrix);
        } else if (x > 0 && board[x - 1][y] == 3) {
            board[x][y] = 5;
            fill(board, i + 1, matrix);
        } else {
            board[x][y] = 0;
            fill(board, i + 1, matrix);
            board[x][y] = 1;
            fill(board, i + 1, matrix);
            if (x < 2) {
                board[x][y] = 3;
                fill(board, i + 1, matrix);
            }
            if (y < 2) {
                board[x][y] = 4;
                fill(board, i + 1, matrix);
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem324());
    }

}
