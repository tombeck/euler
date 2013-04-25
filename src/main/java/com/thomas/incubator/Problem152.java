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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.NumberUtils;
import com.thomas.util.PrimeUtils;

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
     * 35 > 1 in    4137 ms
     * 36 > 1 in    7813 ms
     * 37 > 1 in   14481 ms
     * 38 > 1 in   26867 ms
     * 40 > 1 in   16319 ms
     * 41 > 1 in   30301 ms
     * 42 > 1 in   58079 ms
     * 43 > 1 in  125921 ms
     * 44 > 1 in  262897 ms
     * 45 > 3 in  436402 ms
     * 46 > 3 in 1898423 ms
     * 
     * a/b + x/y = 1/2 <=> 2(ay + xb) = yb
     * 
     * 1/a^2 + 1/b^2 = (a^2+b^2)/(a^2*b^2)
     * 
     * 1/a^2 + 1/(a + 1)^2 = (2a(a+1)+1)/(a(a+1))^2 = 2/(a(a+1))+1/((a(a+1))^2)
     * 
     * 1/a + 1/b + 1/c = (bc + ac + ab) / abc
     * 
     * nicht 32
     * nicht 64
     * wenn 16, dann auch einer von (48 oder 80)
     * wenn 48, dann auch einer von (16 oder 80)
     * wenn 80, dann auch einer von (16 oder 48)
     * wenn 25, dann auch einer von (50 oder 75)
     * wenn 50, dann auch 25
     * wenn 75, dann auch 25
     * 
     * jede Primzahl muss mehr als einmal vorkommen
     * 
     * 23
     * 29
     * 31
     * 37
     * 41
     * 43
     * 46
     * 47
     * 53
     * 58
     * 59
     * 61
     * 62
     * 67
     * 69
     * 71
     * 73
     * 74
     * 79
     * 
     * 
     * 2 4 8 16
     * 3 6 9 12 18 24 27 36 48 54 72
     * 5 10 15 20 25 30 40 45 50 60 75 80
     * 7 14 21 28 35 42 56 63 70
     * 11 22 33 44 55 66 77
     * 13 26 39 52 65 78
     * 17 34 51 68
     * 19 38 57 76
     * 23 46 69
     * 29 58
     * 31 62
     * 37 74
     * 41
     * 43
     * 47
     * 53
     * 59
     * 61
     * 67
     * 71
     * 73
     * 79
     *  
     * 4 8 12 16 20 24 28 32 36 40 44 48 52 56 60 64 68 72 76 80
     * 9 18 27 36 45 54 63 72
     * 25 50 75
     * 49
     *  
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 17.01.2010
     */
    @Override
    public Object solve() {

        final int n = 45;
        final int[] primes = NumberUtils.toIntArray(PrimeUtils.primes(n+1));
        final Boolean[] state = new Boolean[n+1];
        
        checkPrimes(primes, primes.length-1, state);
        
        return null;
//        for (int p : PrimeUtils.primes(80)) {
//            for (int i = p * p * p * p; i <= 80; i += p * p * p * p) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }
//        
//        if (true) return null;
////        final int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10,
////                11, 12, 13, 14, 15,     17, 18, 19, 20,
////                21, 22, 23, 24,     26, 27, 28, 29, 30,
////                31,     33, 34, 35, 36, 37, 38, 39, 40,
////                41, 42, 43, 44, 45, 46, 47,     49,    
////                51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
////                61, 62, 63,     65, 66, 67, 68, 69, 70,
////                71, 72, 73, 74,     76, 77, 78, 79    
////                };
//        
//        final int[] numbers = {2, 3, 4,    6, 7,    9,    
//                11, 12, 13, 14, 15,     17, 18, 19,    
//                21, 22, 23, 24,     26, 27, 28, 29, 30,
//                31,     33, 34, 35, 36, 37, 38, 39, 40,
//                41, 42, 43, 44, 45, 46, 47,     49,    
//                51, 52, 53, 54, 55, 56, 57, 58, 59,    
//                61, 62, 63,     65, 66, 67, 68, 69, 70,
//                71, 72, 73, 74,     76, 77, 78, 79    
//                };
//        
//        BigInteger lcm = BigInteger.ONE;
//        
//        for (int i : numbers) {
//            lcm = lcm(BigInteger.valueOf(i*i), lcm);
//        }
//        System.out.println(lcm.divide(BigInteger.valueOf(2)));
//        System.out.println();
//        for (int i : numbers) {
//            BigInteger x = lcm.divide(BigInteger.valueOf(i*i));
////            if (!x.mod(BigInteger.valueOf(100)).equals(BigInteger.ZERO)) {
//                System.out.println(i + ": " + lcm.divide(BigInteger.valueOf(i*i)));
////            }
//        }
//        findSum(new int [] {56, 25, 64, 96, 61, 84, 24, 25, 16, 24, 25}, 0, 0, new TreeSet<Integer>());
//        for (Set<Integer> s : ALL) {
//            System.out.println(s);
//        }
//        return null;
////        final int max = 80;
////        final double[][] f = new double[max - 1][2];
////        
////        double prod = 1;
////        
////        for (int i = f.length; i-- > 0; ) {
////            f[i][0] = exp(1.0 / ((i + 2) * (i + 2)));
////            System.out.println((i + 2) + " " + f[i][0]);
////            prod *= f[i][0];
////            f[i][1] = prod;
////        }
////        System.out.println(exp(0.5));
////        
////        return count(f, 0, 1.0, exp(0.5));
    }

    static Comparator<Boolean[]> STATE_COMPARATOR = new Comparator<Boolean[]>() {

        @Override
        public int compare(Boolean[] o1, Boolean[] o2) {

            int i = 0;

            for (;i < o1.length; ++i) {
                if (i >= o2.length) return 1;
                int c = asInt(o1[i]) - asInt(o2[i]);
                if (c != 0) return c;
            }

            return o2.length > i ? -1 : 0;
        }
        
        private int asInt(Boolean b) {
            
            if (b == null) return 0;
            if (Boolean.TRUE.equals(b)) return 1;
            return -1;
        }
        
    };
    
    public void checkPrimes(int[] primes, int i, Boolean[] state) {
        
        if (i < 0) {
            System.out.println(Arrays.toString(state));
            return;
        }
        
        final int prime = primes[i];
        final int[] powers = new int[(state.length-1) / prime];
        for (int j = 0; j < powers.length; ++j) {
            powers[j] = prime * (j+1);
        }
        Set<Boolean[]> states = new TreeSet<Boolean[]>(STATE_COMPARATOR);
        
        subset: for (int k = 1 << powers.length; --k > 0; ) {
            
            final int[] subset = new int[Integer.bitCount(k)];
                
            BigInteger kgv = BigInteger.ONE;
            
            for (int s = 0, l = 0; l < powers.length; ++l) {
                if (((1 << l) & k) != 0) {
                    if (Boolean.FALSE.equals(state[powers[l]])) continue subset;
                    subset[s++] = powers[l];
                    kgv =  kgv.multiply(BigInteger.valueOf(powers[l]*powers[l])).divide(kgv.gcd(BigInteger.valueOf(powers[l]*powers[l])));
                } else if (Boolean.TRUE.equals(state[powers[l]])) continue subset;
            }
            BigInteger sum = BigInteger.ZERO;
            for (int s : subset) {
                sum = sum.add(kgv.divide(BigInteger.valueOf(s*s)));
            }
            Boolean[] newState = state.clone();
            for (int j = 0; j < powers.length; ++j) {
                newState[powers[j]] = Boolean.FALSE;
            }
            
            boolean dividable = prime == 2 ? sum.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO) : sum.mod(BigInteger.valueOf(prime*prime)).equals(BigInteger.ZERO);
            if (dividable) {
                
                //System.out.println(Arrays.toString(powers) + " " + Arrays.toString(subset) + " " + dividable);
                for (int s : subset) {
                    newState[s] = Boolean.TRUE;
                }
            }
            states.add(newState);
        }
        for (Boolean[] newState : states) {
            checkPrimes(primes, i-1, newState);
        }
    }
    
    static List<Set<Integer>> ALL = new ArrayList<Set<Integer>>();
    void findSum(int [] numbers, int i, int sum, Set<Integer> exp) {
        
        if (i == numbers.length) {
            if (sum % 100 == 0) {
//                System.out.println(exp + " = " + sum);
                for (Iterator<Set<Integer>> iter = ALL.iterator(); iter.hasNext(); ) {
                    Set<Integer> s = iter.next();
                    if (s.containsAll(exp)) return;
                    if (exp.containsAll(s)) {
                        iter.remove();
                    }
                }
                ALL.add(exp);
            }
        } else {
            Set<Integer> s = new TreeSet<Integer>(exp);
            findSum(numbers, i+1, sum, s);
            s.add(i);
            findSum(numbers, i+1, sum+numbers[i], s);
        }
    }
    
    private BigInteger lcm(BigInteger a, BigInteger b) {
    
        return a.divide(a.gcd(b)).multiply(b);
    }
    
    private int count(double[][] fractions, int i, double cur, double target) {

        if (cur == target) return 1;
        
        int count = 0;
        
        for (; i < fractions.length && cur * fractions[i][0] > target; ++i);
        for (; i < fractions.length && cur * fractions[i][1] >= target; ++i) {
            count += count(fractions, i + 1, cur * fractions[i][0], target);
        }

        return count;
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
