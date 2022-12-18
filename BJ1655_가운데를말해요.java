package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> large = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            //사이즈가 같다면
            if(small.size() == large.size())
                small.add(input);
            //사이즈가 다르다면(small의 사이즈가 더 크다면)
            else
                large.add(input);

            //small에 넣은 후의 가장 큰 값이 large의 가장 작은 값보다 클때
            if(!large.isEmpty() && small.peek() > large.peek()) {
                small.add(large.poll());
                large.add(small.poll());
            }

            sb.append(small.peek() + "\n");
        }
        System.out.println(sb);
    }
}
