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

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.NumberUtils;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.03.2010
 */
class Problem266 implements Problem {

    /**
     * TODO Method documentation
     * 
     * 42 primes
     * 
     * 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
     * 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
     * 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181
     * 
     * greater: 17 <= g <= 26
     * lesser : 25 >= l >= 16
     * 
     * 2074731467313813258973295663761930174 / 2601467408114061643654208773771069335
     * 2305567963945518424753102147331756070
     * 2322178340246686326128527240360393470
     * 2322603686586139881642059345359085730
     * 2323092391619676694693454691755112270
     * 2323212132197291910298742221654621170
     * 2323218028627439520241663855180334010
     * 2323218285027841537300389111299582970
     * 2323218884340250580009092219729352310
     * 2323218884340250580009092219729352310
     * 2323218898901780889985862615189951490
     * 2323218908561298733511111474936082035
     * 2323218921858619707286894168125203145
     * 2323218921876475461052305699396574695
     * 2323218933443207472763771566724863486
     * 2323218934233298386205079941055316570
     * 2323218944614584322270018016078216835
     * 2323218945249486638413101515623452918
     * 2323218947021562122734557024235572863
     * 2323218947508044464337920434523462185
     * 2323218949910263626509980668701362170
     * 2323218950435470564826515588366729785
     * 2323218950596752653151031424716658730
     * 
     * 2323218950681478446587180516177954548
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws InterruptedException 
     * @since 14.03.2010
     */
    @Override
    public Object solve() {

        final int[] primes = NumberUtils.toIntArray(PrimeUtils.primes(190));
        //ArrayUtils.reverse(primes, 0, primes.length);
        BigInteger p = BigInteger.ONE;
        
        for (int a : primes) {
            p = p.multiply(BigInteger.valueOf(a));
        }
        BigInteger max = sqrt(p);
        BigInteger[][] a = new BigInteger[primes.length][2];
        
        for (int i = 0; i < a.length; ++i) {
            a[i][0] = BigInteger.valueOf(primes[i]);
            a[i][1] = (p = p.divide(a[i][0]));
        }

//        new BigInteger("");
//        return find(a, 0, BigInteger.ONE, BigInteger.ONE, p);
        return find(a, 0, BigInteger.ONE, BigInteger.ONE, max, new HashSet<Integer>()).mod(BigInteger.TEN.pow(16));
    }

    private BigInteger find(BigInteger[][] a, int i, BigInteger cur, BigInteger best, BigInteger max, Set<Integer> res) {
        
        if (i == a.length) return best;
        
        BigInteger m = cur.multiply(a[i][0]);
        
        if (m.compareTo(max) <= 0) {
            if (m.compareTo(best) > 0) {
                best = m;
                System.out.println(best);
            }
            best = find(a, i + 1, m, best, max, res);
            if (cur.multiply(a[i][1]).compareTo(best) > 0) {
                best = find(a, i + 1, cur, best, max, res);
            }
        }
        
        return best;
    }
    
    private BigInteger sqrt(BigInteger n) {
        
        BigInteger[] x = {n, n.add(BigInteger.ONE).divide(BigInteger.valueOf(2))};
        
        while (!x[0].equals(x[1])) {
            x = new BigInteger[] {x[1], n.divide(x[1]).add(x[1]).divide(BigInteger.valueOf(2))};
        }

        return x[0];
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem266());
    }

}
