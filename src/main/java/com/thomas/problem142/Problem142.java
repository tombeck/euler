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
package com.thomas.problem142;

import static com.thomas.Util.gcd;

import java.util.ArrayList;
import java.util.Arrays;
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
     * let a > b > 0
     * let a + b = r_ab  =>  b = r_ab - a
     * let a - b = s_ab  =>  b = a - s_ab,    r_ab > s_ab > 0
     * 
     * => r_ab - a = a - s_ab  =>  2a = r_ab + s_ab and 2b = r_ab - s_ab  =>  parity(r_ab) = parity(s_ab)
     * 
     * let x > y > z > 0
     * 
     * 2x = r_xy + s_xy and 2y = r_xy - s_xy
     * 2x = r_xz + s_xz and 2z = r_xz - s_xz
     * 2y = r_yz + s_yz and 2z = r_yz - s_yz
     * 
     * r_xy + s_xy = r_xz + s_xz
     * r_xy - s_xy = r_yz + s_yz
     * r_xz - s_xz = r_yz - s_yz
     * 
     *  =>
     *  
     * r_xy - r_xz        + s_xy - s_xz        = 0
     * r_xy        - r_yz - s_xy        - s_yz = 0
     *        r_xz - r_yz        - s_xz + s_yz = 0
     *
     *    1     -1      0      1     -1      0
     *    1      0     -1     -1      0     -1
     *    0      1     -1      0     -1      1
     *
     *    1     -1      0      1     -1      0
     *   -1      0      1      1      0      1
     *    0      1     -1      0     -1      1
     *
     *    1     -1      0      1     -1      0
     *    0     -1      1      2     -1      1
     *    0      1     -1      0     -1      1
     *    
     *    1     -1      0      1     -1      0
     *    0      1     -1      0     -1      1
     *    0     -1      1      2     -1      1
     *    
     *    1      0     -1      1     -2      1        
     *    0      1     -1      0     -1      1
     *    0      0      0      2     -2      2
     *    
     *    1      0     -1      1     -2      1        
     *    0      1     -1      0     -1      1
     *    0      0      0     -1      1     -1
     *
     * r_xy   r_xz   r_yz   s_xy   s_xz   s_yz
     *    1      0     -1      0     -1      0
     *    0      1     -1     -1      0      0      
     *    0      0      0      1     -1      1
     *    
     * r_xy = r_yz + s_xz   (i * c1)^2 = (i * a1)^2 + (i * b1)^2
     * r_xz = r_yz + s_xy   (j * c2)^2 = (j * a2)^2 + (j * b2)^2
     * s_xz = s_xy + s_yz   (k * c3)^2 = (k * a3)^2 + (k * b3)^2
     * 
     * (i * a1)^2 = (j * a2)^2
     * (i * b1)^2 = (k * c3)^2
     * (j * b2)^2 = (k * a3)^2
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws InterruptedException 
     * @since 13.02.2010
     */
    @Override
    public Object solve() throws InterruptedException {

        final List<long[]> triplets = new ArrayList<long[]>();
        
        for (int u = 1; u < 40; ++u) {
            for (int v = (u & 1) + 1; v < u; v += 2) { // opposite parity
                if (gcd(u, v) == 1) { // relatively prime
                
                    final int a = u * u - v * v; // odd
                    final int b = 2 * u * v; // even
                    final int c = u * u + v * v; // odd

                    long[] triplet = {c, b, a};
                    for (long[] t : triplets) {
                        test(triplet, t);
                    }
                    triplets.add(triplet);
                    triplet = new long[] {c, a, b};
                    for (long[] t : triplets) {
                        test(triplet, t);
                    }
                    triplets.add(triplet);
                }
            }
        }

        return null;
    }

    private boolean sameParity(long a, long b) {
        
        return (a & 1) == (b & 1);
    }
    
    private void test(long[] triplet, long[] t) {

        long lcm = Util.lcm(triplet[1], t[1]);
        long i = lcm / triplet[1];
        long j = lcm / t[1];
        
        long[] t1 = {triplet[0] * i, triplet[1] * i, triplet[2] * i};
        long[] t2 = {t[0] * j, t[1] * j, t[2] * j};
        long[] t3 = {t1[2], t2[2], t1[2] * t1[2] - t2[2] * t2[2]};
        
        long r_xy = t1[0];
        long s_xy = t2[2];
        long r_xz = t2[0];
        long s_xz = t1[2];
        long r_yz = t1[1];
        long s_yz = t3[2];
        if (isSquare(t3[2]) && t3[0] < t2[0] && sameParity(r_xy, s_xy) && sameParity(r_xz, s_xz) && sameParity(r_yz, s_yz)) {
            t3[2] = (long)Math.sqrt(t3[2]);
            System.out.println(Arrays.toString(t1) + Arrays.toString(t2) + Arrays.toString(t3));
        }
    }
    private boolean isSquare(long n) {
    
        long sqr = (long)Math.sqrt(n);
        
        return sqr * sqr == n;
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
