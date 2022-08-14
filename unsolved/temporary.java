import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class temporary {
    static int servers = 2;
    static boolean sticky = false;
    static int[] requests = {1,2,3,4};
    public static void main(String[] args){
        System.out.println(solution(servers,sticky,requests).toString());

    }

    public static int[][] solution(int servers, boolean sticky, int[] requests) {
        ArrayList<Integer>[] tempAns = new ArrayList[servers];

        for (int i = 0; i < servers; i++){
            tempAns[i] = new ArrayList<>();
        }

        if(sticky){
            System.out.println("pass");
        }else{
            int resultIdx = 0;
            for(int i = 0; i < requests.length; i++){
                resultIdx = i % servers;
                tempAns[resultIdx].add(requests[i]);
            }
        }

        int[][] answer = new int[servers][];
        for(int i = 0; i < servers; i++){
            int arrSize = tempAns[i].size();
            answer[i] = new int[arrSize];
            for(int j = 0; j < arrSize; j++){
                answer[i][j] = tempAns[i].get(j);
            }
        }
        return answer;
    }
}