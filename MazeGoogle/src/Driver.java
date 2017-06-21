import java.util.*;

/**
 * Created by eschlotter on 29/03/2017.
 */

public class Driver {
    private static int[][] newMaze; // copy of maze that can be accessed when recurring
    private static int[][] weight; //weight array
    private static int total; //total
    private static int endY, endX;     // Ending X and Y values of maze
    private static int width, height; //being able to get the width and height within method


    public static void main(String[] args) {
        int[][] mz = {
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int[][] mz1 = {
                {0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0} };

        int[][] mz2 = {
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 1, 0},
                {1, 1, 1, 0}};

        System.out.println(answer(mz2));
    }

    public static int answer(int[][] maze) {

        //Initializing the global variables
        newMaze = maze;
        height = newMaze.length;
        width = newMaze[0].length;
        endY = newMaze.length-1;
        endX = newMaze[0].length-1;
        weight = new int[height][width];
        total = 0; // The solution to the newMaze

        for(int i=0;i<maze.length;i++) {
            for(int j=0;j<maze[0].length;j++) {

                if ((i == 0 && j == 0) || maze[i][j] == 1) {
                    if(maze[i][j] == 1)newMaze[i][j]=0; //if wall then check without wall

                    //change weight back to normal
                    for (int row = 0; row < maze.length; row++) {
                        for (int col = 0; col < maze[0].length; col++) {
                            weight[row][col] = Integer.MAX_VALUE; //Weight max to make it easier
                        }
                    }

                    weight[0][0] = 1; //Make sure start is 1
                    recursiveSolve(0, 0); // Check weight of each again

                    if(i>0||j>0)newMaze[i][j]=1; //change back to 1
                    else { //If on first loop then optimize
                        for (int row = 0; row < maze.length; row++){
                            for (int col = 0; col < maze[0].length; col++) {
                                if(weight[row][col]>=2000)maze[row][col]=2; //no need to check weight over 2000
                            }
                        }
                    }
                }

            }
        }

        return total;
    }

    public static boolean recursiveSolve(int y, int x) {

        if (y == endY && x == endX) { //If at the end
            if(total==0 || total>weight[y][x])total = weight[y][x]; //If total is 0 or more than weight of end then save it
            return true;
        }

        int count = weight[y][x];
        int way = 0; // variable holding the way cost of each
        List<int[]> queue = new LinkedList<>();

        if (y != 0){ // Checks if not on left edge
            if (newMaze[y - 1][x] == 0) way=1; // If not a wall then cost is 1 else 1000
            else way=1000;

            if(weight[y - 1][x] > count+way){weight[y - 1][x] = count+way; //save weight if smaller than existing
            int[] t = {y-1,x};
            queue.add(t);} //add to list
        }
        if (x != width - 1){ // Checks if not on bottom edge
            if(newMaze[y][x + 1]==0)way=1;
            else way=1000;

            if(weight[y][x + 1] > count+way){ weight[y][x + 1] = count+way;
            int[] t = {y,x+1};
            queue.add(t);}
        }
        if (y != height - 1){ // Checks if not on right edge
            if(newMaze[y + 1][x]==0)way=1;
            else way=1000;

            if(weight[y + 1][x] > count+way){ weight[y + 1][x] = count+way;
            int[] t = {y + 1,x};
            queue.add(t);}
        }
        if (x != 0){  // Checks if not on top edge
            if(newMaze[y][x - 1]==0)way=1;
            else way=1000;

            if(weight[y][x - 1] > count+way){ weight[y][x - 1] = count+way;
            int[] t = {y,x-1};
            queue.add(t);}
        }

        //Check neighbours
        while(!queue.isEmpty()){
            int lowest = Integer.MAX_VALUE;
            int num=0;
            for(int i = 0; i<queue.size(); i++){ //Find smallest number in queue
                if(weight[queue.get(i)[0]][queue.get(i)[1]]<lowest){ //If lower than lowest
                    lowest = weight[queue.get(i)[0]][queue.get(i)[1]]; // Save weight
                    num = i; //Save position in queue
                }
            }
            recursiveSolve(queue.get(num)[0],queue.get(num)[1]); // Recur on lowest neighbour
            queue.remove(num); //Remove from queue
        }
        return false;
    }
}

