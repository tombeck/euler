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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.thomas.util.Euler;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 17.01.2010
 */
class Problem152 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 17.01.2010
     */
    @Override
    public Object solve() {

        final int n = 45;
        
        final BigInteger den = getDenominator(n);
        final BigInteger[] nom = new BigInteger[n + 1];
        final BigInteger[] sum = new BigInteger[n + 1];
        
        sum[n] = nom[n] = den.divide(BigInteger.valueOf(n * n));
        for (int i = n - 1; i >= 2; --i) {
            nom[i] = den.divide(BigInteger.valueOf(i * i));
            sum[i] = nom[i].add(sum[i + 1]);
        }

        return getCount(sum, nom, den.divide(BigInteger.valueOf(2)), BigInteger.ZERO, 2, n);
    }

    private int getCount(BigInteger[] sum, BigInteger[] nom, BigInteger maxNom, BigInteger curNom, int i, int n) {
        
        int count = 0;
        
        int min = min(nom, maxNom, i, n, curNom);
        int max = max(sum, maxNom, i, n, curNom);
        
        for (int j = min; j <= Math.min(max, n); ++j) {
//            if (curNom.add(sum[j]).compareTo(maxNom) < 0) break;
            
//            final BigInteger nextNom = curNom.add(nom[j]);
//            final int c = nextNom.compareTo(maxNom);
//            
//            if (c < 0) count += getCount(sum, nom, maxNom, nextNom, j + 1, n);
//            else if (c == 0) count += 1;
//            else break;
            count += getCount(sum, nom, maxNom, curNom.add(nom[j]), j + 1, n);
        }
        
        return count;
    }
    
    private int min(BigInteger[] nom, BigInteger maxNom, int first, int last, BigInteger curNom) {
    
        while (first < last) {
            int mid = (first + last) / 2;

            int c = curNom.add(nom[mid]).compareTo(maxNom);
            if (c > 0) first = mid + 1;
            else last = mid + 1;
        }
        
        return first;
    }
    
    private int max(BigInteger[] sum, BigInteger maxNom, int first, int last, BigInteger curNom) {
        
        while (first < last) {
            int mid = (first + 1 + last) / 2;

            int c = curNom.add(sum[mid]).compareTo(maxNom);
            if (c < 0) last = mid - 1;
            else first = mid;
        }
        
        return last;
    }
    
    private BigInteger getDenominator(int n) {
        
        final Map<Long, Integer> map = new HashMap<Long, Integer>();
        
        for (int i = 2; i <= n; ++i) {
            
            long prev = 1;
            int count = 0;
            
            for (long p : PrimeUtils.getPrimeFactors(i * i)) {
                if (p == prev) ++count;
                else {
                    
                    final Integer c = map.get(prev);
                    
                    if (c == null) map.put(prev, count);
                    else map.put(prev, Math.max(c, count));
                    count = 1;
                }
                prev = p;
            }
            Integer c = map.get(prev);
            if (c == null) map.put(prev, count);
            else map.put(prev, Math.max(c, count));
        }
        
        BigInteger den = BigInteger.ONE;
        
        for (Entry<Long, Integer> entry : map.entrySet()) {
            den = den.multiply(BigInteger.valueOf(entry.getKey()).pow(entry.getValue()));
        }
        
        return den;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 17.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem152());
    }

}
