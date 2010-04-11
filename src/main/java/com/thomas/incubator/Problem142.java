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
package com.thomas.incubator;

import static com.thomas.Util.gcd;

import java.util.ArrayList;
import java.util.List;

import com.thomas.Util;
import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 13.02.2010
 */
class Problem142 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws InterruptedException 
     * @since 13.02.2010
     */
    @Override
    public Object solve() throws InterruptedException {

        final List<int[]> triplets = new ArrayList<int[]>();
        
        for (int u = 1; u < 18; ++u) {
            for (int v = (u & 1) + 1; v < u; v += 2) { // opposite parity
                if (gcd(u, v) == 1) { // relatively prime
                
                    final int x = u * u - v * v;
                    final int y = 2 * u * v;
                    final int z = u * u + v * v;

                    triplets.add(new int[] {x*x, y*y, z*z});
                }
            }
        }
        for (int[] i : triplets) {
            for (int[] j : triplets) {
                Thread.sleep(1);
                long lcm = Util.lcm(i[0], j[2]);
                long mi = lcm / i[0];
                long mj = lcm / j[2];
                long[] i_ = new long[] {i[0] * mi, i[1] * mi, i[2] * mi};
                long[] j_ = new long[] {j[0] * mj, j[1] * mj, j[2] * mj};
                for (int[] k : triplets) {
                    System.out.println(k);
                    lcm = Util.lcm(i_[1], k[1]);
                    mi =  mj = lcm / i_[1];
                    long mk = lcm / k[1];
                    i_ = new long[] {i_[0] * mi, i_[1] * mi, i_[2] * mi};
                    j_ = new long[] {j_[0] * mj, j_[1] * mj, j_[2] * mj};
                    long[] k_ = new long[] {k[0] * mk, k[1] * mk, k[2] * mk};
                    if (j_[0] == k_[0]) {
                        long z = ((j_[1] - i_[1]) / 2);
                        long y = z + i_[1];
                        long x = y + j_[0];
                        System.out.println((x + y) + ", " + (x - y) + ", " + (x + z) + ", " + (x - z) + ", " + (y + z) + ", " + (y - z));
                        System.out.println(x + y + z);
                    }
                }
            }
        }

        return null;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 13.02.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem142());
    }

}
