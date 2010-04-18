/*
 * $Id: $
 * (c) Copyright 2008 freiheit.com technologies GmbH
 *
 * Created on 11.04.2010 by Thomas Beckmann (thomas.beckmann@freiheit.com)
 *
 * This file contains unpublished, proprietary trade secret information of
 * freiheit.com technologies GmbH. Use, transcription, duplication and
 * modification are strictly prohibited without prior written consent of
 * freiheit.com technologies GmbH.
 */
package com.thomas.problem70;

import static com.thomas.Util.isPermutation;
import static com.thomas.util.NumberUtils.totient;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem70 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer solve() {

        int n_min = 10000000;
        int t_min = 1;
        
        for (int n = 10000000; n-- > 2; ) {
            int t = totient(n);
            if ((long)n * t_min < (long)n_min * t && isPermutation(n, t)) {
                n_min = n;
                t_min = t;
            }
        }
        
        return n_min;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem70());
    }

}
