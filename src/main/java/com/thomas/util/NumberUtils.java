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
package com.thomas.util;

import static com.thomas.util.PrimeUtils.getPrimeFactors;
import static java.math.BigInteger.ONE;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 06.11.2009
 */
public class NumberUtils {

    public static double round(double x, int p) {
    
        final long e = pow(10L, p);
        
        return (double)Math.round(x * e) / e;
    }
    
    public static BigInteger sqrt(BigInteger n) {
        
        BigInteger[] x = {n, n.add(ONE).shiftRight(1)};
        
        while (!x[0].equals(x[1])) {
            x = new BigInteger[] {x[1], n.divide(x[1]).add(x[1]).shiftRight(1)};
        }

        return x[0];
    }
    
    public static int pow(int a, int b) {
    
        if (b == 0) return 1;
        if ((b & 1) != 0) return a * pow(a, b - 1);
        
        final int tmp = pow(a, b >> 1);
        
        return tmp * tmp;
    }
    
    public static long pow(long a, int b) {
        
        if (b == 0) return 1;
        if ((b & 1) != 0) return a * pow(a, b - 1);
        
        final long tmp = pow(a, b >> 1);
        
        return tmp * tmp;
    }
    
    public static BigInteger pow(BigInteger a, BigInteger b) {
        
        if (b.equals(BigInteger.ZERO)) return BigInteger.ONE;
        if (b.testBit(0)) return a.multiply(pow(a, b.subtract(BigInteger.ONE)));
        
        final BigInteger tmp = pow(a, b.shiftRight(1));
        
        return tmp.multiply(tmp);
    }
    
    public static int modPow(int a, int b, int m) {
        
        if (b == 0) return 1;
        if ((b & 1) != 0) return (a * modPow(a, b - 1, m)) % m;
        
        final int tmp = modPow(a, b >> 1, m);
        
        return (tmp * tmp) % m;
    }
    
    public static long modPow(long a, long b, long m) {
        
        if (b == 0) return 1;
        if ((b & 1) != 0) return (a * modPow(a, b - 1, m)) % m;
        
        final long tmp = modPow(a, b >> 1, m);
        
        return (tmp * tmp) % m;
    }
    
    public static int totient(int n) {
        
        if (n < 3) return 1;
        if (n < 4) return 2;
        
        int totient = n;
        
        if ((n & 1) == 0) {
            totient >>= 1;
            while (((n >>= 1) & 1) == 0);
        }
        
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                totient -= totient / i;
                while ((n /= i) % i == 0);
            }
        }
        if (n > 1) totient -= totient / n;
        
        return totient;
    }
    
    public static long totient(long n) {
        
        if (n < 3) return 1;
        if (n < 4) return 2;
        
        long totient = n;
        
        if ((n & 1) == 0) {
            totient >>= 1;
            while (((n >>= 1) & 1) == 0);
        }
        
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                totient -= totient / i;
                while ((n /= i) % i == 0);
            }
        }
        if (n > 1) totient -= totient / n;
        
        return totient;
    }
    
    public static int numberOfDivisors(int n) {
        
        if (n < 2) return 1;
        if (n < 4) return 2;
        
        int product = 1;
        
        for (; (n & 1) == 0; n >>= 1) ++product;
        
        long sqrt = (long)Math.sqrt(n) + 1;
        
        for (int i = 3; i <= sqrt; i += 2) {
            int sum = 1;
            if (n % i == 0) {
                do {
                    ++sum;
                    n /= i;
                } while (n % i == 0);
                product *= sum;
            }
        }
        if (n > 1) product *= 2;
        
        return product;
    }
    
    public static int numberOfDivisors(long n) {
        
        if (n < 2) return 1;
        if (n < 4) return 2;
        
        int product = 1;
        
        for (; (n & 1) == 0; n >>= 1) ++product;
        
        long sqrt = (long)Math.sqrt(n) + 1;
        
        for (long i = 3; i <= sqrt; i += 2) {
            int sum = 1;
            if (n % i == 0) {
                do {
                    ++sum;
                    n /= i;
                } while (n % i == 0);
                product *= sum;
            }
        }
        if (n > 1) product *= 2;
        
        return product;
    }
    
    public static int[] sumsOfProperDivisors(int max) {
        
        final int[] sums = new int[max];
        
        sums[0] = sums[1] = 0;

        for (int n = 1, d; (d = n << 1) < max; ++n) {
            for (int j = d; j < max; j += n) {
                sums[j] += n;
            }
        }
        
        return sums;
    }
    
    public static Set<Integer> divisors(int n) {
    
        final Set<Integer> divisors = new TreeSet<Integer>();
        
        divisors.add(distinctDivisors(getPrimeFactors(n), 0, 1, divisors));
        
        return divisors;
    }
    
    private static int distinctDivisors(List<Integer> factors, int first, int product, Set<Integer> divisors) {

        for (int i = first; i < factors.size(); ++i) {
            divisors.add(distinctDivisors(factors, i + 1, product * factors.get(i), divisors));
        }
        
        return product;
    }
    
    public static Set<Long> divisors(long n) {
        
        final Set<Long> divisors = new TreeSet<Long>();
        
        divisors.add(distinctDivisors(getPrimeFactors(n), 0, 1, divisors));
        
        return divisors;
    }
    
    private static long distinctDivisors(List<Long> factors, int first, long product, Set<Long> divisors) {

        for (int i = first; i < factors.size(); ++i) {
            divisors.add(distinctDivisors(factors, i + 1, product * factors.get(i), divisors));
        }
        
        return product;
    }
    
    public static boolean isPractical(int n) {
        
        int product = 1;
        
        for (int i = 2; i * i <= n; i++) {
            int sum = 1;
            if (n % i == 0) {
                if (i > product + 1) return false;
                do {
                    sum = sum * i + 1;
                    n /= i;
                } while (n % i == 0);
                product *= sum;
            }
        }
        if (n > 1 && n > product + 1) return false;
        
        return true;
    }
    
    public static int[] parseInt(String[] s) {
    
        final int[] values = new int[s.length];
        
        for (int i = 0; i < s.length; ++i) {
            values[i] = Integer.parseInt(s[i]);
        }
        
        return values;
    }
    
    public static boolean is1To9Pandigital(int i) {
        
        if (i < 123456789 || 987654321 < i) return false;
        
        final boolean[] ba = new boolean[10];

        ba[0] = true;
        
        for (; i > 9; i /= 10) {
            if (!(ba[i % 10] ^= true)) return false;
        }
        
        return !ba[i];
    }
    
    public static boolean isSquareFree(long n) {
        
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                n /= i;
                if (n % i == 0) return false;
            }
        }

        return true;
    }

    public static int nCk(int n, int k) {
        
        if (2 * k > n) k = n - k;
        
        int bc = 1;
        
        for (int i = 1; i <= k; ++i) {
            bc = (bc * ((n + 1) - i)) / i;
        }
        
        return bc;
    }
    
    public static int[] toIntArray(Collection<? extends Number> list) {
        
        final int[] array = new int[list.size()];
        
        int i = 0;
        
        for (Number l : list) array[i++] = l.intValue();
        
        return array;
    }
    
    public static BigInteger[] toBigIntegerArray(Collection<? extends Number> list) {
        
        final BigInteger[] array = new BigInteger[list.size()];
        
        int i = 0;
        
        for (Number l : list) array[i++] = BigInteger.valueOf(l.longValue());
        
        return array;
    }
    
    public static long[] toLongArray(Collection<? extends Number> list) {
        
        final long[] array = new long[list.size()];
        
        int i = 0;
        
        for (Number l : list) array[i++] = l.longValue();
        
        return array;
    }
    
    public static boolean isSquare(long n) {
        
        final long sqrt = (long)Math.sqrt(n);
        
        return sqrt * sqrt == n;
    }

    public static long modInv(long u, long v) {
        
        long u1 = 1;
        long u3 = u;
        long v1 = 0;
        long v3 = v;
        long t1;
        long t3;

        /* Remember odd/even iterations */
        int iter = 1;
        /* Step X2. Loop while v3 != 0 */
        while (v3 != 0) {
            /* Step X3. Divide and "Subtract" */
            t3 = u3 % v3;
            t1 = u1 + (u3 / v3) * v1;
            /* Swap */
            u1 = v1;
            v1 = t1;
            u3 = v3;
            v3 = t3;
            iter = -iter;
        }
        /* Make sure u3 = gcd(u,v) == 1 */
        if (u3 != 1) return 0;   /* Error: No inverse exists */
        
        /* Ensure a positive result */
        return iter < 0 ? v - u1 : u1;
    }

    private NumberUtils() {
        //
    }

    
    public static void main(String[] args) {

        System.out.println(modInv(3, 11));
    }
    
}
