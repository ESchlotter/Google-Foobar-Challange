import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by eschlotter on 02/03/2017.
 */
public class Main {
    public static void main(String[] args) {


        System.out.println(answer(0, 63));

    }

    /**
     * Simple Answer method
     *
     * @param src - first number
     * @param dest - second
     * @return - least moves
     */

    public static int answer(int src, int dest) {

        int[] start = getPosition(src); // Change src to x and y array
        int[] end = getPosition(dest); // Change dest to x and y array

        ArrayList<int[]> next = new ArrayList<>();
        ArrayList<int[]> current = new ArrayList<>();

        next.add(start);
        int moves = 0;
        //                      ul       ur      ru      rd       dr      dl       ld       lu
        int[][] possible = {{-1, -2}, {-1, 2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}};

        while (true) {
            if (check(next,end))break; //check if finished (if end is in next)

            current = (ArrayList<int[]>) next.clone(); //reset current to contents of next
            next.clear(); //reset next

            for(int[] j : current) { // j is current array
                for (int[] i : possible) { //i is possible moves

                    if ((j[0] + i[0] >= 0) && (j[1] + i[1] >= 0)) { //check boundary
                        if ((j[0] + i[0] <= 7) && (j[1] + i[1] <= 7)) {

                            int[] s = {j[0] + i[0], j[1] + i[1]}; // add to next list
                            next.add(s);
                        }
                    }
                }
            }
            moves++; // increase moves
        }
        return moves; //return moves
    }

    /**
     * Only way to check if arraylist contains int array
     * @param next
     * @param end
     * @return
     */
    public static boolean check (ArrayList<int[]> next, int[] end){
        for(int[] i:next){
            if(Arrays.equals(i,end)) return true;
        }
        return false;
    }


    /**
     * Get x and y of number
     * @param num initial number
     * @return x and y array
     */
    public static int[] getPosition(int num){
        int[] pos = new int[2];
        pos[0]=num%8;
        pos[1]=num/8;
        return pos;
    }
}
