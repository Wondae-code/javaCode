package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1931 {
    static class Meeting implements Comparable<Meeting>{
        int start, end;

        Meeting(int start, int end){
            super();
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.end != o.end ? this.end - o.end : this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            meetings[i] = new Meeting(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        List<Meeting> result = getSchedule(meetings);

        System.out.println(result.size());
    }

    private static List<Meeting> getSchedule(Meeting[] meetings) {
        ArrayList<Meeting> result = new ArrayList<>();
        // 회의 목록을 종료시간 오름차순, 같다면 시작시간 오름차순 정렬
        Arrays.sort(meetings);
        // 종료가 가장 빠른 회의 추가
        result.add(meetings[0]);
        for (int i = 1; i < meetings.length; i++) {
            if(result.get(result.size() - 1).end <= meetings[i].start){
                result.add(meetings[i]);
            }
        }
        return result;
    }
}
