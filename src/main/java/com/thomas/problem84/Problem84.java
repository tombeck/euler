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
package com.thomas.problem84;

import java.util.Arrays;
import java.util.Comparator;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 02.01.2010
 */
public class Problem84 implements Problem {

    private static final int GO = 0;
    private static final int CC1 = 2;
    private static final int CC2 = 17;
    private static final int CC3 = 33;
    private static final int CH1 = 7;
    private static final int CH2 = 22;
    private static final int CH3 = 36;
    private static final int JAIL = 10;
    private static final int G2J = 30;
    private static final int C1 = 11;
    private static final int E3 = 24;
    private static final int H2 = 39;
    private static final int R1 = 5;
    private static final int R2 = 15;
    private static final int R3 = 25;
    private static final int U1 = 12;
    private static final int U2 = 28;
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @throws Exception
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 02.01.2010
     */
    @Override
    public Object solve() throws Exception {

        final double[][] prob = new double[40][40];
        final int sides = 4;
        final int sidesr2 = sides * sides;
        
        for (int i = 0; i < 40; ++i) {
            for (int d1 = 1; d1 <= sides; ++d1) {
                for (int d2 = 1; d2 <= sides; ++d2) {
                    
                    final int j = (i + d1 + d2) % 40;
                    
                    double p = 1.0 / sidesr2;
                    
                    if (d1 == d2 && i != JAIL) {
                        prob[i][JAIL] += p / sidesr2;
                        p = (p * (sidesr2 - 1)) / sidesr2;
                    }
                    switch (j) {
                    default:
                        prob[i][j]    += p;
                        break;
                    case G2J:
                        prob[i][JAIL] += p;
                        break;
                    case CC1:
                    case CC2:
                    case CC3:
                        prob[i][j]    += ((p) * 14) / 16;
                        prob[i][GO]   += ((p) *  1) / 16;
                        prob[i][JAIL] += ((p) *  1) / 16;
                        break;
                    case CH1:
                        prob[i][j]    += ((p) *  6) / 16;
                        prob[i][GO]   += ((p) *  1) / 16;
                        prob[i][JAIL] += ((p) *  1) / 16;
                        prob[i][C1]   += ((p) *  1) / 16;
                        prob[i][E3]   += ((p) *  1) / 16;
                        prob[i][H2]   += ((p) *  1) / 16;
                        prob[i][R1]   += ((p) *  1) / 16;
                        prob[i][R2]   += ((p) *  2) / 16;
                        prob[i][U1]   += ((p) *  1) / 16;
                        prob[i][j-3]  += ((p) *  1) / 16;
                        break;
                    case CH2:
                        prob[i][j]    += ((p) *  6) / 16;
                        prob[i][GO]   += ((p) *  1) / 16;
                        prob[i][JAIL] += ((p) *  1) / 16;
                        prob[i][C1]   += ((p) *  1) / 16;
                        prob[i][E3]   += ((p) *  1) / 16;
                        prob[i][H2]   += ((p) *  1) / 16;
                        prob[i][R1]   += ((p) *  1) / 16;
                        prob[i][R3]   += ((p) *  2) / 16;
                        prob[i][U2]   += ((p) *  1) / 16;
                        prob[i][j-3]  += ((p) *  1) / 16;
                        break;
                    case CH3:
                        prob[i][j]    += ((p) *  6) / 16;
                        prob[i][GO]   += ((p) *  1) / 16;
                        prob[i][JAIL] += ((p) *  1) / 16;
                        prob[i][C1]   += ((p) *  1) / 16;
                        prob[i][E3]   += ((p) *  1) / 16;
                        prob[i][H2]   += ((p) *  1) / 16;
                        prob[i][R1]   += ((p) *  1) / 16;
                        prob[i][R1]   += ((p) *  2) / 16;
                        prob[i][U1]   += ((p) *  1) / 16;
                        prob[i][j-3]  += ((((p) *  1) / 16) * 14) / 16;
                        prob[i][GO]   += ((((p) *  1) / 16) *  1) / 16;
                        prob[i][JAIL] += ((((p) *  1) / 16) *  1) / 16;
                        break;
                    }
                }
            }
        }
        
        double[] v1 = new double[40];
        
        v1[GO] = 1.0;
        
        for (int n = 0; n < 300; ++n) {
            
            final double[] v2 = new double[40];
            for (int i = 0; i < 40; ++i) {
                for (int j = 0; j < 40; ++j) {
                    v2[i] += v1[j] * prob[j][i];
                }
            }
            v1 = v2;
        }
        int[][] s = new int[40][];
        for (int i = 0; i < 40; ++i) {
            s[i] = new int[] {i, (int)(v1[i] * 10000)};
        }
        Arrays.sort(s, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {

                return o2[1] - o1[1];
            }
            
        });

        return String.format("%02d%02d%02d", s[0][0], s[1][0], s[2][0]);
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 02.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem84());
    }

}
