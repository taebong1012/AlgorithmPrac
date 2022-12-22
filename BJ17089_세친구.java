package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17089_세친구 {
    private static boolean[][] isFriend;
    private static int[] friendCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        isFriend = new boolean[N+1][N+1];
        friendCnt = new int[N+1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //친구 여부 저장(무향 그래프)
            isFriend[a][b] = true;
            isFriend[b][a] = true;

            //친구 수 저장
            ++friendCnt[a];
            ++friendCnt[b];
        }

        int min = Integer.MAX_VALUE;

        for(int A = 1; A < N+1; A++) {
            for(int B = A+1; B < N+1; B++) {
                if(!isFriend[A][B]) continue;
                for(int C = B+1; C < N+1; C++) {
                    if(!isFriend[C][A] || !isFriend[C][B]) continue;

                    int sum = friendCnt[A] + friendCnt[B] + friendCnt[C] - 6;
                    min = Math.min(min, sum);
                }
            }
        }

        if(min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }
}
