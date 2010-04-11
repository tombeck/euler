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
package com.thomas.incubator;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 29.12.2009
 */
class Problem158 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 29.12.2009
     */
    @Override
    public Object solve() {

        int sum = 0;
        
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                for (int k = 0; k < 26; ++k) {
                    if ((i < j && !(j < k)) || (!(i < j) && j < k)) ++sum; 
                }
            }
        }

        return sum;
//        final long[][][] cache2 = new long[18 + 1][2][25];
//
//        for (int i = 0; i < 25; ++i) {
//            cache2[1][0][i] = 1;
//            cache2[1][1][i] = 0;
//        }
//        for (int i = 0; i < 25; ++i) {
//            for (int j = 0; j < 25; ++j) {
//                if (i < j) ++cache2[2][1][j];
//                else ++cache2[2][0][j];
//            }
//        }
//        for (int i = 0; i < 25; ++i) {
//            for (int j = 0; j < 25; ++j) {
//                for (int k = 0; k < 25; ++k) {
//                    if (!(i < j) && !(j < k)) ++cache2[3][0][k];
//                    else if ((i < j && !(j < k)) || (!(i < j) && j < k)) ++cache2[3][1][k]; 
//                }
//            }
//        }
//        
//        final long[][][] cache = new long[26 + 2][2][26];
//
//        long max = 0;
//        
//        for (int len = 1; len <= 25; ++len) {
//            max = Math.max(max, count(cache, len + 1, 1, 0, cache2));
//        }
//        
//        return max;
    }

    private long count(long[][][] cache, int len, int n, int ch, long[][][] cache2) {
    
        if (len == 1) return 1 - n;
        
        long count = cache[len][n][ch];
        
        if (count == 0) {
            for (int c = 0; c < 25; ++c) {
                if (c < ch) {
                    count += count(cache, len - 1, 1 - n, c, cache2);
                } else {
                    count += count(cache, len - 1, n, c, cache2);
                }
            }
        }
        
        return cache[len][n][ch] = count;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 29.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem158());
    }

}
