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
import java.util.List;

import com.thomas.util.Euler;
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
     * 2323218898901780889985862615189951490
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws InterruptedException 
     * @since 14.03.2010
     */
    @Override
    public Object solve() throws InterruptedException {

        for (int p : PrimeUtils.primes(190)) {
            int q = (int)Math.sqrt(p);
            System.out.println(p + ", " + q + ", " + (q * q) + ", " + ((q + 1) * (q + 1)));
        }
//        final List<BigInteger> primes = new ArrayList<BigInteger>();
//        
//        BigInteger b = ONE;
//
//        for (int p : Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53)) {
//            final BigInteger prime = BigInteger.valueOf(p);
//            b = b.multiply(prime);
//            primes.add(prime);
//        }
//
//        return PrimeUtils.getPrimeFactors(psr(primes, 0, ONE, b, 0, ONE).longValue());
        
        return null;
    }

    private BigInteger psr(List<BigInteger> primes, int i, BigInteger l, BigInteger g, int j, BigInteger max) throws InterruptedException {
        
//        if (i <= 20) System.out.println(i);
//        if (i == 30) Thread.sleep(1);
        
        if (l.compareTo(g) >= 0) return max;
        if (i == primes.size()) {
            if (l.compareTo(max) > 0) {
//                System.out.println(l);
                return l;
            }
            return max;
        }
        
        final BigInteger prime = primes.get(i++);
        
        max = psr(primes, i, l.multiply(prime), g.divide(prime), j + 1, max);
        max = psr(primes, i, l, g, j, max);
        
        return max;
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
