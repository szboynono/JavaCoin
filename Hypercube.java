/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 2: Hypercube.java
 * Student Name:   Baidi,Liu
 * Student cse account:  liubd
 * Student ID number:  211559093
 **********************************************************/
import java.util.ArrayList;
import java.util.List;

/**
 * Use two method to walk through the hyper cube.
 *
 */
public class Hypercube {

    // represents one corner in the cube
    class Corner{

        public int coordinate; // using binary for representing a corner, say 1 for 001 when n = 3

        public Corner(int coord){
            this.coordinate = coord;
        }

        // print corner according to mask [3,4,5]
        public String toString(int mask){
            char[] chs = new char[mask];
            for(int i = 0; i < mask; i++) {
                chs[mask - 1 - i] = (char)(((coordinate >> i) & 1) + '0');
            }
            return new String(chs);
        }

    }

    // recursive method
    public Corner[] recursiveWalk(int dimen){
        if(dimen == 1){
            // if the N = 1, only need to start from 0 to 1
            Corner[] path = new Corner[2];
            path[0] = new Corner(0);
            path[1] = new Corner(1);
            return path;
        }
        else{
            Corner[] M = new Corner[(int)Math.pow(2, dimen)]; // the size of path of N is 2^N
            Corner[] L = recursiveWalk(dimen - 1); // recursive search
            // obtain path of N from path of N - 1 <x0, x1, x2 ... xk>
            // solution N = <0x0, 0x1, 0x2...0xk,1xk...1x2, 1x1,1x0>
            for(int i = 0; i < L.length; ++i){
                M[i] = new Corner(L[i].coordinate);
            }
            int j = 0;
            for(int i = L.length - 1; i > -1; --i){
                M[j + L.length] = new Corner((int)(L[i].coordinate + Math.pow(2, dimen - 1)));
                j++;
            }
            return M;
        }
    }

    public Corner[] iterativeWalk(int dimen){
        Corner[] result = new Corner[(int)Math.pow(2, dimen)]; // the size of path of N is 2^N
        // initial with N = 1
        result[0] = new Corner(0);
        result[1] = new Corner(1);
        int j = 2;
        // iteration starts with 2
        for(int i = 2; i < dimen + 1; i++){
            // solution N = <0x0, 0x1, 0x2...0xk,1xk...1x2, 1x1,1x0>
            // starts from the back
            for(int k = j - 1; k > -1; --k){
                result[j] = new Corner((int)(result[k].coordinate + Math.pow(2, i - 1)));
                j++;
            }
        }
        return result;
    }

    // only for test
    public static void main(String[] args){
        Hypercube hb = new Hypercube();
        int dimen = 4;
        Corner[] M = hb.recursiveWalk(dimen);
        for(Corner c: M)
            System.out.println(c.toString(dimen));

        System.out.println("====================");

        Corner[] N = hb.iterativeWalk(dimen);
        for(Corner c: N)
            System.out.println(c.toString(dimen));
    }
}
