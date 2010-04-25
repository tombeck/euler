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
package com.thomas.problem220;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 06.12.2009
 */
public class Problem220 implements Problem {

    static enum Direction { 
        
        UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0);
        
        final int x;
        final int y;
        
        private Direction(int x, int y) {
            
            this.x = x;
            this.y = y;
        }
        
        public Direction add(Direction other) {
            
            switch(this) {
            case UP:
                switch (other) {
                case UP: return UP;
                case DOWN: return DOWN;
                case LEFT: return LEFT;
                case RIGHT: return RIGHT;
                }
            case DOWN:
                switch (other) {
                case UP: return DOWN;
                case DOWN: return UP;
                case LEFT: return RIGHT;
                case RIGHT: return LEFT;
                }
            case LEFT:
                switch (other) {
                case UP: return LEFT;
                case DOWN: return RIGHT;
                case LEFT: return DOWN;
                case RIGHT: return UP;
                }
            case RIGHT:
                switch (other) {
                case UP: return RIGHT;
                case DOWN: return LEFT;
                case LEFT: return UP;
                case RIGHT: return DOWN;
                }
            }

            throw new IllegalArgumentException();
        }
        
    }
    
    static class Turtle {
    
        final int x;
        final int y;
        final Direction direction;
        final long steps;

        private Turtle(int x, int y, Direction direction, long steps) {

            this.x = x;
            this.y = y;
            this.direction = direction;
            this.steps = steps;
        }
        
        public Turtle() {

            this(0, 0, Direction.UP, 0);
        }
        
        public Turtle left() {
        
            switch (this.direction) {
            case UP: return new Turtle(this.x, this.y, Direction.LEFT, this.steps);
            case DOWN: return new Turtle(this.x, this.y, Direction.RIGHT, this.steps);
            case LEFT: return new Turtle(this.x, this.y, Direction.DOWN, this.steps);
            case RIGHT: return new Turtle(this.x, this.y, Direction.UP, this.steps);
            }

            throw new IllegalArgumentException();
        }
        
        public Turtle right() {
            
            switch (this.direction) {
            case UP: return new Turtle(this.x, this.y, Direction.RIGHT, this.steps);
            case DOWN: return new Turtle(this.x, this.y, Direction.LEFT, this.steps);
            case LEFT: return new Turtle(this.x, this.y, Direction.UP, this.steps);
            case RIGHT: return new Turtle(this.x, this.y, Direction.DOWN, this.steps);
            }

            throw new IllegalArgumentException();
        }
        
        public Turtle forward() {
        
            return new Turtle(this.x + this.direction.x, this.y + this.direction.y, this.direction, this.steps +  1);
        }
        
        public Turtle add(Turtle other) {
        
            switch (this.direction) {
            case UP: return new Turtle(this.x + other.x, this.y + other.y, this.direction.add(other.direction), this.steps + other.steps);
            case DOWN: return new Turtle(this.x - other.x, this.y - other.y, this.direction.add(other.direction), this.steps + other.steps);
            case LEFT: return new Turtle(this.x - other.y, this.y + other.x, this.direction.add(other.direction), this.steps + other.steps);
            case RIGHT: return new Turtle(this.x + other.y, this.y - other.x, this.direction.add(other.direction), this.steps + other.steps);
            }

            throw new IllegalArgumentException();
        }
        
        @Override
        public String toString() {
            
            return this.x + "," + this.y;
        }
        
    }
    
    static enum Instruction {
        a, b, L, R, F;
        
        static Instruction[][] SET = {{a, R, b, F, R}, {L, F, a, L, b}};
    }
    
    static final long STEPS = 1000000000000L;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() throws Exception {

        final Turtle[][] cache = new Turtle[2][51];
        
        for (int i = 0; i < 50; ++i) {
            for (int j = 0; j < 2; ++j) {
                cache[j][i] = new Turtle();
                for (Instruction c : Instruction.SET[j]) {
                    switch (c) {
                    case a: if (i > 0) cache[j][i] = cache[j][i].add(cache[0][i - 1]); break;
                    case b: if (i > 0) cache[j][i] = cache[j][i].add(cache[1][i - 1]); break;
                    case F: cache[j][i] = cache[j][i].forward(); break;
                    case L: cache[j][i] = cache[j][i].left(); break;
                    case R: cache[j][i] = cache[j][i].right(); break;
                    }
                }
            }
        }
        
        return parse(0, new Turtle().forward(), 49, cache);
    }

    private Turtle parse(int s, Turtle turtle, int depth, Turtle[][] cache) {
        
        if (depth < 0) return turtle;
        
        for (Instruction c : Instruction.SET[s]) {
            switch (c) {
            case a: if (depth > 0) {
                
                final Turtle tmp = turtle.add(cache[0][depth - 1]);
                
                turtle = tmp.steps > STEPS ? parse(0, turtle, depth - 1, cache) : tmp;
            } break;
            case b: if (depth > 0) {
                
                final Turtle tmp = turtle.add(cache[1][depth - 1]);
                
                turtle = tmp.steps > STEPS ? parse(1, turtle, depth - 1, cache) : tmp;
            } break;
            case F: turtle = turtle.forward(); break;
            case L: turtle = turtle.left(); break;
            case R: turtle = turtle.right(); break;
            }
            if (turtle.steps >= STEPS) break;
        }
        
        return turtle;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 06.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem220());
    }

}
