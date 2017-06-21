/**
 * Created by eschlotter on 25/05/2017.
 */
public class Driver {
    public static void main(String [] args) {
        System.out.println(answer("27"));
    }

    public static int answer(String n) {
        long num = Long.parseLong(n);
        int a = solve(num,0);
        return a;
    }

    public static int solve(long num, int total){
        if (num == 1) return total;

        else if(num % 2 == 0){
            num /= 2;
        }

        else if( ((num-1) % 3 < 2) || (num-1) == 2 ){
            num -= 1;
        }

        else {
            num += 1;
        }
        return solve(num, total + 1);
    }
}
