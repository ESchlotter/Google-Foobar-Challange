import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by eschlotter on 24/02/2017.
 */
public class Driver {
    public static void main(String args[]){
        int h = 6; //HEIGHT
        int[] q = new int[]{4,2};//LIST
        int [] a =answer(h,q);
        int [] b =newanswer(h,q);
        for(int i=0 ; i<a.length;i++){
            System.out.println("ANSWER A: "+a[i] +" ANSWER B: "+b[i]);
        }

    }

    /** Old Answer, brute forces it
     * @param h
     * @param q
     * @return
     */
    public static int[] answer(int h, int[] q) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i=0;i<q.length; i++) {
            map.put(q[i],null);
        }
        tree(0,0,h,map,0,0); // start tree method
        int total = (int)Math.pow(2,h)-1;
        map.replace(total,-1); //if one of the initial numbers equals total number of nodes then turn it into -1

        //turning hash into array
        int[] p = new int[q.length]; //answer array
        int c = 0;
        for(int x:map.values()){
            p[c++]=x;
        }
        return p;
    }

    /**
     * @param next - keep track of node numbers used so far
     * @param level - how far along are we
     * @param height - height of tree
     * @param map - hashmap storing answers and initial flux converters
     * @param left - left node
     * @param right - right node
     * @return node number
     */
    private static int tree(int next, int level, int height, LinkedHashMap map,int left,int right) {
        if(level==height){
            return 0; // if level matches height return next
        }

        left  = tree(next, level+1, height, map, left, right); // find the node number for left
        if(next<left)next=left; //make sure next doesn't get overwritten
        right = tree(next, level+1, height, map, left, right); // find the node number for right
        if(next<right)next=right;

        next+=1; // set current node

        map.replace(left,next); //look for key equal to left/right and replace with node number
        map.replace(right,next);


        return next;
    }


    /**
     * Best Answer / Halves the number over and over again
     * @param h
     * @param q
     * @return
     */
    public static int[] newanswer(int h, int[] q) {

        int total = (int)Math.pow(2,h) - 1; //calculate total number of nodes
        int[] p = new int[q.length]; //initialise answer array

        for (int i = 0 ; i < q.length ; i++) {
            if (q[i] < total) //if less than total find
                p[i] = find(total,q[i],total-1);
            else
                p[i] = -1;
        }

        return p;
    }

    /**
     * Recurring method that starts at the top and
     * quickly makes it's way down by halving the number of nodes
     *
     * @param level - int which tells us the number of the current node
     * @param target - our target from q(remains unchanged)
     * @param remaining - remaining number of nodes
     * @return
     */
    public static int find(int level, int target,int remaining) {

        remaining/=2; //halving the remaining number of nodes
        int right = level-1; //right node is always -1
        int left = level-1-remaining; //left is always the current level - remaining

        if(right == target || left == target)
            return level; //if target is found return above node

        if(target<left) //check which side to go through
            return find(left,target,remaining);
        else
            return find(right,target,remaining);

    }


}
