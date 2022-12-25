package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ9466_텀프로젝트 {

    private static int n, cnt;
    private static int[] students;
    private static boolean[] isVisited;
    private static boolean[] isSearched;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()) + 1;   //0번 인덱스는 사용하지 않기 위해서 +1

            students = new int[n];
            isVisited = new boolean[n]; //dfs로 탐색을 하면서 거쳤던 노드인지
            isSearched = new boolean[n];    //main 함수를 기준으로 사용했던 노드인지

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            cnt = 0;    //팀을 이룬 학생의 수
            for(int i = 1; i < n; i++) {
                dfs(i);
            }

            sb.append((n-1)-cnt + "\n");

        }

        System.out.println(sb);

    }   //main 끝

    private static void dfs(int cur) {

        isVisited[cur] = true;
        int chosen = students[cur]; //현재 학생이 고른 학생번호

        //1. 다음 노드가 방문하지 않은 곳이라면 더 깊이 탐색
        if(!isVisited[chosen]) {
            dfs(chosen);
        }
        //2. 다음 노드가 방문했었던 곳이라면 팀 완성
        else {
            if(!isSearched[chosen]) {
                ++cnt;  //팀에 속하지 않는 학생 수 증가
                for(int i=chosen; i != cur; i = students[i])
                    ++cnt;
            }
        }

        isSearched[cur] = true; //사용처리

    }
}
