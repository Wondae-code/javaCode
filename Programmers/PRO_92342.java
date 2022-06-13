import java.util.Arrays;

public class PRO_92342 {
    // 1. 어피치가 n발을 쏘고 라이언이 n발을 쏜다
    // 2. k점에 어피치 a발, 라이언 b발을 맞췄을 때, 더 많은 화살을 맞춘 선수가 k점을 가져간다.
    // 단, 화살 수가 같은 경우는 어피치가 가져간다.
    // 3. 최종 점수를 계산 하고 우승자를 결정한다. 만약 점수가 같다면, 어피치가 우승한다.

    // 가장 낮은 점수를 맞힌 개수가 같은 경우, 작은 점수를 더 맞힌 것을 리턴한다.
    static int N, maxGap; // 화살 수, 가장 큰 차이를 저장하기 위한 점수 차이
    static int[] info, ryan, Ans; // 어피치가 맞춘 화살 순서, 라이언이 맞출 순서

    public static void main(String[] args) {
        // info[i] = 10-i 점을 맞춘 횟수
        info = new int[]{0,0,0,0,0,0,0,0,3,4,3};
        ryan = new int[info.length];
        Ans = new int[info.length];

        N = Arrays.stream(info).sum();

        Case(0, N);

        // 이기는 경우가 없다면
        if(maxGap == 0){
            // -1 리턴
            System.out.println(-1);
        }else{
            System.out.println(Arrays.toString(Ans));
        }
    }

    private static void Case(int idx, int k){
        // 경우의 수의 인덱스가 넘을 떄
        if(idx == info.length){
            return;
        }

        // 화살을 다 썼을 때
        if(k == 0){
            aScore = 0;
            rScore = 0;
            // 라이언이 어피치를 이겼을 경우
            if(isWin()){
                int gap = rScore - aScore;
                if(gap > maxGap){
                    maxGap = gap;
                    //System.out.println(rScore + " " + aScore);
                    //System.out.println(Arrays.toString(ryan));
                    System.arraycopy(ryan, 0, Ans, 0, ryan.length);
                }
                // 점수가 같으면 적은 점수를 더 맞힌 쪽을 고름
                else if(gap == maxGap){
                    // Ans와 ryan의 합을 비교함
                    if(sumOfWeight()){
                        System.arraycopy(ryan, 0, Ans, 0, ryan.length);
                    }
                }
            }
            return;
        }

        // 10 - idx 점을 맞췄을 때
        ryan[idx]++;
        Case(idx, k-1);
        // 10 - idx 점을 못 맞췄을 때
        ryan[idx]--;
        Case(idx+1, k);
    }

    private static boolean sumOfWeight() {
        int aTemp = 0, rTemp = 0;
        for (int i = 0; i < info.length; i++) {
            aTemp += Ans[i] * (i+1);
            rTemp += ryan[i] * (i+1);
        }

        return rTemp > aTemp;
    }

    static int rScore, aScore;

    private static boolean isWin(){
        for (int i = 0; i < info.length; i++) {
            if(ryan[i] == 0 && info[i] == 0) continue;
            if(ryan[i] > info[i]){
                rScore += (10 - i);
            }else{
                aScore += (10 - i);
            }
        }

        return (rScore > aScore);
    }
}
