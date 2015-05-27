/*
 * (c) Copyright 2015 freiheit.com technologies GmbH
 *
 * Created on 25.05.2015 by Thomas Beckmann (thomas.beckmann@freiheit.com)
 *
 * This file contains unpublished, proprietary trade secret information of
 * freiheit.com technologies GmbH. Use, transcription, duplication and
 * modification are strictly prohibited without prior written consent of
 * freiheit.com technologies GmbH.
 */
package com.thomas.problem5xx.problem50x;

import static com.thomas.util.NumberUtils.modPow;
import static com.thomas.util.PrimeUtils.primes;
import static java.lang.Math.log;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann@freiheit.com">Thomas Beckmann</a>
 * @since 25.05.2015
 */
public class Problem500 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final PriorityQueue<long[]> queue = new PriorityQueue<long[]>(500500, new Comparator<long[]>() {

            @Override
            public int compare(long[] o1, long[] o2) {

                double p1 = o1[1] * log(o1[0]);
                double p2 = o2[1] * log(o2[0]);
                
                if (p1 < p2) return -1;
                if (p1 > p2) return  1;

                return 0;
            }
            
            
        });
                        
        for (int i: primes(7376508)) {
            queue.add(new long[] {i, 1});
        }
        
        long number = 1;
        
        for (int i = 0; i < 500500; ++i) {
            long[] prime = queue.remove();
            number = (number * modPow(prime[0], prime[1], 500500507L)) % 500500507;
            prime[1] *= 2;
            queue.add(prime);
        }
        
        return number;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem500());
    }

}
