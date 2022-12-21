package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2805_나무자르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //나무의 수
        int M = Integer.parseInt(st.nextToken());   //집으로 가져가려고 하는 나무의 길이

        int[] trees = new int[N];

        int max = 0;
        int min = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        while(min < max) {
            int mid = (max + min) / 2;  //자르는 위치 (중간)
            long sum = 0;
            for(int i = 0; i < N; i++) {
                if(trees[i] - mid > 0) sum += (trees[i] - mid);
            }

            //1. 길이의 합이 M보다 작음 -> 지금 자른 곳보다 밑을 잘라야 함!
            if(sum < M) max = mid;

                //2. 길이의 합이 M보다 큼 -> 지금 자른 곳보다 위를 잘라야 함!
            else {
                min = mid + 1;
            }

        }

        System.out.println(min - 1);

    }
}
