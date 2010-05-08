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
import static java.lang.Math.sqrt;

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

    public static int moebius(int n) {
        
        if (n == 1) return 1;
        
        int k = 0;
        
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                n /= i;
                if (n % i == 0) return 0;
                ++k;
            }
        }
        if (n > 1) ++k;

        return (k & 1) == 0 ? 1 : -1;
    }
    
    public static int moebiusTransform(int[] a, int n) {
    
        int sum = 0;
        
        for (int d = 1; d <= n; ++d) {
            if (n % d == 0) {
                sum += a[d];
            }
        }
        
        return sum;
    }
    
    public static int inverseMoebiusTransform(int[] a, int n) {
        
        int sum = 0;
        
        for (int d = 1; d <= n; ++d) {
            if (n % d == 0) {
                sum += moebius(n / d) * a[d];
            }
        }
        
        return sum;
    }
    
    public static int pow(int a, int b) {
    
        if (b == 0) return 1;
        if ((b & 1) != 0) return a * pow(a, b - 1);
        
        int tmp = pow(a, b >> 1);
        
        return tmp * tmp;
    }
    
    public static long pow(long a, int b) {
        
        if (b == 0) return 1;
        if ((b & 1) != 0) return a * pow(a, b - 1);
        
        long tmp = pow(a, b >> 1);
        
        return tmp * tmp;
    }
    
    public static int modPow(int a, int b, int m) {
        
        if (b == 0) return 1;
        if ((b & 1) != 0) return (a * modPow(a, b - 1, m)) % m;
        
        int tmp = modPow(a, b >> 1, m);
        
        return (tmp * tmp) % m;
    }
    
    public static long modPow(long a, int b, long m) {
        
        if (b == 0) return 1;
        if ((b & 1) != 0) return (a * modPow(a, b - 1, m)) % m;
        
        long tmp = modPow(a, b >> 1, m);
        
        return (tmp * tmp) % m;
    }
    
    public static int radical(int n) {
        
        final int max = (int)sqrt(n);
        
        int rad = 1;

        for (int i = 2; i <= max; i++) {
            if (n % i == 0) {
                rad *= i;
                do n /= i;
                while (n % i == 0);
            }
        }
        if (n > 1) rad *= n;

        return rad;
    }
    
    public static int totient(int n) {
        
        int totient = n;
        
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                totient -= totient / i;
                do n /= i;
                while (n % i == 0);
            }
        }
        if (n > 1) totient -= totient / n;
        
        return totient;
    }
    
    public static long totient(long n) {
        
        long totient = n;
        
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                totient -= totient / i;
                do n /= i;
                while (n % i == 0);
            }
        }
        if (n > 1) totient -= totient / n;
        
        return totient;
    }
    
    public static int numberOfDivisors(int n) {
        
        int product = 1;
        
        for (int i = 2; i * i <= n; i++) {
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
    
    public static int sumOfDivisors(int n) {
    
        int product = 1;
        
        for (int i = 2; i * i <= n; i++) {
            int sum = 1;
            if (n % i == 0) {
                do {
                    sum = sum * i + 1;
                    n /= i;
                } while (n % i == 0);
                product *= sum;
            }
        }
        if (n > 1) product *= n + 1;
        
        return product;
    }
    
    public static int sumOfProperDivisors(int n) {
    
        return sumOfDivisors(n) - n;
    }
    
    public static int[] sumsOfProperDivisors(int max) {
        
        final int[] sums = new int[max];
        
        sums[0] = sums[1] = 0;

        for (int n = 1, d; (d = 2 * n) < max; ++n) {
            for (int j = d; j < max; j += n) {
                sums[j] += n;
            }
        }
        
        return sums;
    }
    
    public static boolean isPerfect(int n) {
    
        return sumOfProperDivisors(n) == n;
    }
    
    public static boolean isAbundant(int n) {
        
        return sumOfProperDivisors(n) > n;
    }
    
    public static boolean isAmicable(int a) {
        
        final int b = sumOfProperDivisors(a);
        
        return a != b && sumOfProperDivisors(b) == a;
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
    
    private NumberUtils() {
        //
    }

    
    public static void main(String[] args) {

        System.out.println(sumOfDivisors(24));
    }
    
}
