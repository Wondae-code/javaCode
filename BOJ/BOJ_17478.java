package SSAFY;

//https://www.acmicpc.net/problem/17478

import java.util.Scanner;

public class BOJ_17478 {
    static String start = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
    static String question = "\"재귀함수가 뭔가요?\"";
    static String[] body =  {"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
            "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\""};
    static String answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
    static String last = "라고 답변하였지.";
    static int target; //목표로 할 숫자

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        target = sc.nextInt();
        System.out.println(start);
        Recursive(0);
    }

    static void Recursive(int i){ // i : 현재 위치, n : 목표
        Indent(i);
        System.out.println(question);
        if(i != target){
            for (String word : body) {
                Indent(i);
                System.out.println(word);
            }
            Recursive(i+1);
        }else{
            Indent(i);
            System.out.println(answer);
        }
        Indent(i);
        System.out.println(last);
    }

    static void Indent(int n){ // 문장 앞의 '_'를 놓기 위한 메서드
        for (int i = 0; i < n*4; i++) {
            System.out.print("_");
        }
    }
}
