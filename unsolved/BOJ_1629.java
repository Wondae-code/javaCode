import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ_1629 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        long A = Long.parseLong(inputs[0]);
        long B = Long.parseLong(inputs[1]);
        long C = Long.parseLong(inputs[2]);

        System.out.println(pow(A, B, C));
    }

    private static long pow(long A, long B, long C){
        if(B == 1){
            return A % C;
        }
        long temp = pow(A, B/2, C);

        if(B % 2 == 1){
            return (temp * temp % C) * A % C;
        }else{
            return temp * temp % C;
        }
    }
}
