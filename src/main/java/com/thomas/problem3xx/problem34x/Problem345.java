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
package com.thomas.problem3xx.problem34x;

import static java.lang.Integer.bitCount;
import static java.lang.Math.max;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author Thomas Beckmann
 * @since 18.12.2011
 */
public class Problem345 implements Problem {

    private static final int[][] MATRIX = {
            {   7,  53, 183, 439, 863, 497, 383, 563,  79, 973, 287,  63, 343, 169, 583 },
            { 627, 343, 773, 959, 943, 767, 473, 103, 699, 303, 957, 703, 583, 639, 913 },
            { 447, 283, 463,  29,  23, 487, 463, 993, 119, 883, 327, 493, 423, 159, 743 },
            { 217, 623,   3, 399, 853, 407, 103, 983,  89, 463, 290, 516, 212, 462, 350 },
            { 960, 376, 682, 962, 300, 780, 486, 502, 912, 800, 250, 346, 172, 812, 350 },
            { 870, 456, 192, 162, 593, 473, 915,  45, 989, 873, 823, 965, 425, 329, 803 },
            { 973, 965, 905, 919, 133, 673, 665, 235, 509, 613, 673, 815, 165, 992, 326 },
            { 322, 148, 972, 962, 286, 255, 941, 541, 265, 323, 925, 281, 601,  95, 973 },
            { 445, 721,  11, 525, 473,  65, 511, 164, 138, 672,  18, 428, 154, 448, 848 },
            { 414, 456, 310, 312, 798, 104, 566, 520, 302, 248, 694, 976, 430, 392, 198 },
            { 184, 829, 373, 181, 631, 101, 969, 613, 840, 740, 778, 458, 284, 760, 390 },
            { 821, 461, 843, 513,  17, 901, 711, 993, 293, 157, 274,  94, 192, 156, 574 },
            {  34, 124,   4, 878, 450, 476, 712, 914, 838, 669, 875, 299, 823, 329, 699 },
            { 815, 559, 813, 459, 522, 788, 168, 586, 966, 232, 308, 833, 251, 631, 107 },
            { 813, 883, 451, 509, 615,  77, 281, 613, 459, 205, 380, 274, 302,  35, 805 }
    };

    private static final int MASK_MAX = (1 << MATRIX.length) - 1;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        final int[] sum = new int[MASK_MAX + 1];

        for (int colMask = 0, row = 0; colMask < MASK_MAX; row = bitCount(++colMask)) {
            for (int col = 0, mask; col < MATRIX.length; ++col) {
                if ((mask = colMask | (1 << col)) != colMask) {
                    sum[mask] = max(sum[mask], sum[colMask] + MATRIX[row][col]);
                }
            }
        }
        
        return sum[MASK_MAX];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem345());
    }

}
