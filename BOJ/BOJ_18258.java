package SSAFY;

import java.io.*;

public class BOJ_18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue Q = new Queue();

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "push" -> Q.push(Integer.parseInt(command[1]));
                case "pop" -> sb.append(Q.pop() + "\n");
                case "empty" -> sb.append(Q.empty() + "\n");
                case "size" -> sb.append(Q.size() + "\n");
                case "front" -> sb.append(Q.front() + "\n");
                case "back" -> sb.append(Q.back() + "\n");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}

class Queue{
    int[] arr;
    int back = 0;
    int front = 0;

    Queue(){
        arr = new int[5];
    }

    void push(int v){
        if(back == arr.length){
            int[] temp = new int[back*2];
            System.arraycopy(arr,0,temp,0,back);
            arr = temp;
        }
        arr[back] = v;
        back++;
    }

    int pop(){
        if(back == front) return -1;
        else{
            int val = arr[front];
            front++;
            return val;
        }
    }

    int empty(){
        if(back == front) return 1;
        else return 0;
    }

    int size(){
        return back - front;
    }

    int front(){
        if(back == front) return -1;
        return arr[front];
    }

    int back(){
        if(back == front) return -1;
        return arr[back - 1];
    }
}
