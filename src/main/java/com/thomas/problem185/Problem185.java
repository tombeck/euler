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
package com.thomas.problem185;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 21.11.2009
 */
public class Problem185 implements Problem {

    private static final int[][][] GUESSES = {
        {{1<<1,1<<8,1<<4,1<<1,1<<2,1<<3,1<<6,1<<4,1<<5,1<<4,1<<3,1<<2,1<<4,1<<5,1<<8,1<<9}, {3}}, //4
        {{1<<3,1<<0,1<<4,1<<1,1<<6,1<<3,1<<1,1<<1,1<<1,1<<7,1<<2,1<<2,1<<4,1<<6,1<<3,1<<5}, {3}}, //3
        {{1<<4,1<<2,1<<9,1<<6,1<<8,1<<4,1<<9,1<<6,1<<4,1<<3,1<<6,1<<0,1<<7,1<<5,1<<4,1<<3}, {3}}, //2
        {{1<<7,1<<8,1<<9,1<<0,1<<9,1<<7,1<<1,1<<5,1<<4,1<<8,1<<9,1<<0,1<<8,1<<0,1<<6,1<<7}, {3}}, //1
        {{1<<9,1<<7,1<<4,1<<2,1<<8,1<<5,1<<5,1<<5,1<<0,1<<7,1<<0,1<<6,1<<8,1<<3,1<<5,1<<3}, {3}}, //1
        {{1<<1,1<<7,1<<4,1<<8,1<<2,1<<7,1<<0,1<<4,1<<7,1<<6,1<<7,1<<5,1<<8,1<<2,1<<7,1<<6}, {3}}, //0
        {{1<<8,1<<6,1<<9,1<<0,1<<0,1<<9,1<<5,1<<8,1<<5,1<<1,1<<5,1<<2,1<<6,1<<2,1<<5,1<<4}, {3}}, //0
        {{1<<5,1<<8,1<<5,1<<5,1<<4,1<<6,1<<2,1<<9,1<<4,1<<0,1<<8,1<<1,1<<0,1<<5,1<<8,1<<7}, {3}}, //0
        {{1<<2,1<<3,1<<2,1<<6,1<<5,1<<0,1<<9,1<<4,1<<7,1<<1,1<<2,1<<7,1<<1,1<<4,1<<4,1<<8}, {2}}, //4
        {{1<<2,1<<6,1<<1,1<<5,1<<2,1<<5,1<<0,1<<7,1<<4,1<<4,1<<3,1<<8,1<<6,1<<8,1<<9,1<<9}, {2}}, //4
        {{1<<2,1<<6,1<<5,1<<9,1<<8,1<<6,1<<2,1<<6,1<<3,1<<7,1<<3,1<<1,1<<6,1<<8,1<<6,1<<7}, {2}}, //3
        {{1<<5,1<<2,1<<5,1<<1,1<<5,1<<8,1<<3,1<<3,1<<7,1<<9,1<<6,1<<4,1<<4,1<<3,1<<2,1<<2}, {2}}, //2
        {{1<<6,1<<4,1<<4,1<<2,1<<8,1<<8,1<<9,1<<0,1<<5,1<<5,1<<0,1<<4,1<<2,1<<7,1<<6,1<<8}, {2}}, //1
        {{1<<4,1<<5,1<<1,1<<3,1<<5,1<<5,1<<9,1<<0,1<<9,1<<4,1<<1,1<<4,1<<6,1<<1,1<<1,1<<7}, {2}}, //1
        {{1<<5,1<<6,1<<1,1<<6,1<<1,1<<8,1<<5,1<<6,1<<5,1<<0,1<<5,1<<1,1<<8,1<<2,1<<9,1<<3}, {2}}, //1
        {{1<<8,1<<1,1<<5,1<<7,1<<3,1<<5,1<<6,1<<3,1<<4,1<<4,1<<1,1<<1,1<<8,1<<4,1<<8,1<<3}, {1}}, //3
        {{1<<3,1<<8,1<<4,1<<7,1<<4,1<<3,1<<9,1<<6,1<<4,1<<7,1<<2,1<<9,1<<3,1<<0,1<<4,1<<7}, {1}}, //2
        {{1<<6,1<<9,1<<1,1<<3,1<<8,1<<5,1<<9,1<<1,1<<7,1<<3,1<<1,1<<2,1<<1,1<<3,1<<6,1<<0}, {1}}, //1
        {{1<<6,1<<3,1<<7,1<<5,1<<7,1<<1,1<<1,1<<9,1<<1,1<<5,1<<0,1<<7,1<<7,1<<0,1<<5,1<<0}, {1}}, //1
        {{1<<3,1<<1,1<<7,1<<4,1<<2,1<<4,1<<8,1<<4,1<<3,1<<9,1<<4,1<<6,1<<5,1<<8,1<<5,1<<8}, {1}}, //1
        {{1<<4,1<<8,1<<9,1<<5,1<<7,1<<2,1<<2,1<<6,1<<5,1<<2,1<<1,1<<9,1<<0,1<<3,1<<0,1<<6}, {1}}, //0
        {{1<<2,1<<3,1<<2,1<<1,1<<3,1<<8,1<<6,1<<1,1<<0,1<<4,1<<3,1<<0,1<<3,1<<8,1<<4,1<<5}, {0}},
    };

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 20.03.2010
     */
    @Override
    public String solve() {

        final int[] initial = {1023,1023,1023,1023,1023,1023,1023,1023,1023,1023,1023,1023,1023,1023,1023,1023};

        return toString(test(initial, GUESSES.length));
    }

    private int[] test(int[] state, int index) {

        if (index == 0) return state;
        
        final int[][] guess = GUESSES[--index];
        final int[] indexes = new int[16];
        final int[] mask = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        final int[] digits = guess[0];
        
        int hits = 0;
        int len = 0;
        for (int i = state.length; i-- > 0; ) {
            if ((state[i] & digits[i]) != 0) {
                if (state[i] == digits[i]) {
                    mask[i] = 0;
                    ++hits;
                }
                else indexes[len++] = i;
            }
        }
        switch (guess[1][0] - hits) {
        case 0: {
            final int[] res = test(applyMask(state, guess[0], mask), index);
            if (res != null) return res;
            break;
        }
        case 1: {
            for (int i0 = 0; i0 < len; ++i0) {
                mask[indexes[i0]] = 0;
                final int[] res = test(applyMask(state, guess[0], mask), index);
                if (res != null) return res;
                mask[indexes[i0]] = -1;
            }
            break;
        }
        case 2: {
            for (int i0 = 1; i0 < len; ++i0) {
                mask[indexes[i0]] = 0;
                for (int i1 = 0; i1 < i0; ++i1) {
                    mask[indexes[i1]] = 0;
                    final int[] res = test(applyMask(state, guess[0], mask), index);
                    if (res != null) return res;
                    mask[indexes[i1]] = -1;
                }
                mask[indexes[i0]] = -1;
            }
            break;
        }
        case 3: {
            for (int i0 = 2; i0 < len; ++i0) {
                mask[indexes[i0]] = 0;
                for (int i1 = 1; i1 < i0; ++i1) {
                    mask[indexes[i1]] = 0;
                    for (int i2 = 0; i2 < i1; ++i2) {
                        mask[indexes[i2]] = 0;
                        final int[] res = test(applyMask(state, guess[0], mask), index);
                        if (res != null) return res;
                        mask[indexes[i2]] = -1;
                    }
                    mask[indexes[i1]] = -1;
                }
                mask[indexes[i0]] = -1;
            }
            break;
        }
        default:
            break;
        }

        return null;
    }

    private int[] applyMask(int[] state, int[] digits, int[] mask) {
        
        final int[] clone = state.clone();
        
        for (int i = state.length; i-- > 0; ) {
            clone[i] &= (digits[i] ^ mask[i]);
        }
        
        return clone;
    }

    private String toString(int[] state) {
        
        final StringBuilder builder = new StringBuilder();
        
        for (int i = 0, l = state.length; i < l; ++i) {
            builder.append(Integer.numberOfTrailingZeros(state[i]));
        }
        
        return builder.toString();
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 21.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem185());
    }

}
