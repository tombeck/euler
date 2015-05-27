/**
 * $Id$
 *
 * Copyright (c) 2012 Thomas Beckmann
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
package com.thomas.problem3xx.problem38x;

import static com.thomas.util.NumberUtils.pow;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * |--------------------------------------------------------------------------++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-|
 * |--------------++++++++++++++-|--------------++++++++++++++-|--------------++++++++++++++-|--------------++++++++++++++-|--------------++++++++++++++-|
 * |--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|
 * |00001|00001|00001|00001|00002|00001|00001|00001|00001|00002|00001|00001|00001|00001|00002|00001|00001|00001|00001|00002|00001|00001|00001|00001|00003|
 *      
 * |--------------------------------------------------------------------------++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++-|
 * |--------------++++++++++++++-|--------------++++++++++++++-|--------------++++++++++++++-|--------------++++++++++++++-|--------------++++++++++++++-|
 * |--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|--++-|
 * |11112|11112|11112|11112|11113|11112|11112|11112|11112|11113|11112|11112|11112|11112|11113|11112|11112|11112|11112|11113|11112|11112|11112|11112|11114|
 * 
 * @author Thomas Beckmann
 * @since 06.05.2012
 */
public class Problem383 implements Problem {

    /**
     * {@inheritDoc}
     * 
     * a(n) = -1/6*(1-sqrt(6))^n*sqrt(6)+1/6*sqrt(6)*(1+sqrt(6))^n+1/2*(1-sqrt(6))^n+1/2*(1 +sqrt(6))^n
     * 
     * https://oeis.org/A180168
     * 
     * [[0, 18], [12, 20], [20, 18], [22, 4], [6, 2], [2, 0]]
     * [[0, 54], [36, 84], [76, 94], [96, 56], [74, 18], [22, 4], [6, 2], [2, 0]]
     * 
     */
    @Override
    public Object solve() {

        long[][] a = new long[][] {};
        
        for (int i = 0; i < 18; ++i) {
            a = mult(a);
        }
        long n = pow(10L, 18)/pow(5L, 18);
        long sum = 0;
        
        int diff = 0;
        
        for (long i = 0; i < n; ) {
            if (diff > 17) sum += 172186884L;
            if (diff > 16) sum += 1434890700L;
            if (diff > 15) sum += 6411304224L;
            if (diff > 14) sum += 19994463792L;
            if (diff > 13) sum += 48199966596L;
            if (diff > 12) sum += 94641856380L;
            if (diff > 11) sum += 156043200384L;
            if (diff > 10) sum += 220121133984L;
            if (diff > 9) sum += 268737329092L;
            if (diff > 8) sum += 285803380716L;
            if (diff > 7) sum += 265478062880L;
            if (diff > 6) sum += 215242018384L;
            if (diff > 5) sum += 151754939844L;
            if (diff > 4) sum += 92420487452L;
            if (diff > 3) sum += 48169163776L;
            if (diff > 2) sum += 21266981952L;
            if (diff > 1) sum += 7911203204L;
            if (diff > 0) sum += 2518066246L;
            if (diff > -1) sum += 729982338L;
            if (diff > -2) sum += 211620314L;
            if (diff > -3) sum += 61348342L;
            if (diff > -4) sum += 17784726L;
            if (diff > -5) sum += 5155778L;
            if (diff > -6) sum += 1494634L;
            if (diff > -7) sum += 433302L;
            if (diff > -8) sum += 125606L;
            if (diff > -9) sum += 36418L;
            if (diff > -10) sum += 10554L;
            if (diff > -11) sum += 3062L;
            if (diff > -12) sum += 886L;
            if (diff > -13) sum += 258L;
            if (diff > -14) sum += 74L;
            if (diff > -15) sum += 22L;
            if (diff > -16) sum += 6L;
            if (diff > -17) sum += 2L;
            diff -= 1*v5(2*i+1); // i = 2,7,12,... (x = 5,15,25,...)
            if (diff > 18) sum += 258280326L;
            if (diff > 17) sum += 2008846980L;
            if (diff > 16) sum += 8484987006L;
            if (diff > 15) sum += 25187469048L;
            if (diff > 14) sum += 58056543774L;
            if (diff > 13) sum += 109335522060L;
            if (diff > 12) sum += 173277857286L;
            if (diff > 11) sum += 235311805296L;
            if (diff > 10) sum += 276850695798L;
            if (diff > 9) sum += 283928000404L;
            if (diff > 8) sum += 254421842510L;
            if (diff > 7) sum += 199027743336L;
            if (diff > 6) sum += 135406374286L;
            if (diff > 5) sum += 79598114588L;
            if (diff > 4) sum += 40083524694L;
            if (diff > 3) sum += 17144582368L;
            if (diff > 2) sum += 6217340006L;
            if (diff > 1) sum += 1952143524L;
            if (diff > 0) sum += 565922722L;
            if (diff > -1) sum += 164059616L;
            if (diff > -2) sum += 47560698L;
            if (diff > -3) sum += 13787644L;
            if (diff > -4) sum += 3997082L;
            if (diff > -5) sum += 1158696L;
            if (diff > -6) sum += 335938L;
            if (diff > -7) sum += 97364L;
            if (diff > -8) sum += 28242L;
            if (diff > -9) sum += 8176L;
            if (diff > -10) sum += 2378L;
            if (diff > -11) sum += 684L;
            if (diff > -12) sum += 202L;
            if (diff > -13) sum += 56L;
            if (diff > -14) sum += 18L;
            if (diff > -15) sum += 4L;
            if (diff > -16) sum += 2L;
            diff += 2*v5(1*i+1); // i = 4,9,14,... (x = 5,10,15,...)
            if (diff > -18) sum += 1;
            diff -= 1*v5(2*i+2); // i = 4,9,14,... (x = 10,20,30,...)
            i += 1;
        }
        
        return sum;
    }

    long[][] mult(long[][] a) {
    
        long[][] b = new long[a.length+2][2];
        
        for (int i = 0; i < a.length; ++i) {
            b[i+1][0] = 3*a[i][0] + 2*a[i][1];
            b[i][1] = 2*a[i][0] + 3*a[i][1];
        }
        b[b.length-1][0] = 2;
        b[b.length-2][1] = 2;
        
        return b;
    }
    
    int v5(long n) {
        
        int v5 = 0;
        
        for ( ; n % 5 == 0; n /= 5) ++v5;
            
        return v5;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem383());
    }

}
