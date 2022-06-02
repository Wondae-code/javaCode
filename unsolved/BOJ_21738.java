import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class BOJ_21738 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, S, P; // 전체 불록의 수, 지지대 블록 수, 펭귄의 위치
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        S = Integer.parseInt(raw[1]);
        P = Integer.parseInt(raw[2]);
        // 얼음 구조 -> 기본 트리
        Node root = new Node(0, -1);
        for (int i = 1; i <= S; i++) {
            root.children.add(new Node(i, root.level+1));
        }

        heights = new ArrayList<>();

        // 값 집어 넣기
        for (int i = 0; i < N - 1; i++) {
            raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);

            for (Node n: root.children) {
                Node p = traverse(n, src);
                if(p != null){
                    p.children.add(new Node(dst, p.level+1));
                    if(dst == P){
                        // P와 같은 값일 떄 level 집어 넣기
                        heights.add(p.level+1);
                    }
                    break;
                }
            }
        }

        // 제일 짧은 길이 두개 뽑기
        Collections.sort(heights);
        int Ans = N - (heights.get(0) + heights.get(1) + 1);

        System.out.println(Ans);
    }
    static ArrayList<Integer> heights;

    private static Node traverse(Node cur, int val) {
        // 현재 노드의 값이 찾는 값이면 리턴
        if (cur.src == val) {
            return cur;
        }

        // 자식 중에서 찾기 -> 자식이 없지 않으면,
        if (cur.children.size() != 0) {
            for (Node n : cur.children) {
                Node temp = traverse(n, val);
                if(temp != null){
                    return temp;
                }
            }
        }
        return null;
    }

    private static class Node {
        int src, level;
        ArrayList<Node> children;

        public Node(int src, int level) {
            this.src = src;
            this.level = level;
            this.children = new ArrayList<>();
        }
    }
}
