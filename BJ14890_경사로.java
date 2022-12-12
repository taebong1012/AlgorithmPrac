package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14890_경사로 {

    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int answer = 0;

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //가로 줄 탐색
        for(int i = 0; i < N; i++) {

            int flat = 1;

            for(int j = 1; j < N; j++) {
                int cur = map[i][j]; //현재 땅의 높이
                int prev = map[i][j-1]; //이전 땅의 높이

                //1. 평지일 경우
                if(cur == prev) {
                    ++flat;
                }

                //2. 오르막길을 놓아야하는 경우
                else if(cur - prev == 1) {
                    if(flat >= L) {
                        flat = 1;
                    } else {
                        break;
                    }
                }

                //3. 내리막길을 놓아야하는 경우
                else if(prev - cur == 1) {
                    boolean isPossible = true;

                    //현재 칸을 포함하여 L길이의 평지가 확보되어있는지 확인
                    for(int k = 0; k < L; k++) {
                        if(!isIn(i, j+k) || cur != map[i][j+k]) {
                            isPossible = false;
                            break;
                        }
                    }

                    if(isPossible) {
                        j += L - 1;
                        flat = 0;
                    } else {
                        break;
                    }
                }

                //4. 높이의 차가 1 이상일 경우
                else break;

                //열의 마지막까지 도달했을 경우 길 횟수 증가
                if(j == N-1) answer++;

            }
        }

        //세로 줄 탐색
        for(int j = 0; j < N; j++) {

            int flat = 1;

            for(int i = 1; i < N; i++) {
                int cur = map[i][j]; //현재 땅의 높이
                int prev = map[i-1][j]; //이전 땅의 높이

                //1. 평지일 경우
                if(cur == prev) {
                    ++flat;
                }

                //2. 오르막길을 놓아야하는 경우
                else if(cur - prev == 1) {
                    if(flat >= L) {
                        flat = 1;
                    } else {
                        break;
                    }
                }

                //3. 내리막길을 놓아야하는 경우
                else if(prev - cur == 1) {
                    boolean isPossible = true;

                    //현재 칸을 포함하여 L길이의 평지가 확보되어있는지 확인
                    for(int k = 0; k < L; k++) {
                        if(!isIn(i+k, j) || cur != map[i+k][j]) {
                            isPossible = false;
                            break;
                        }
                    }

                    if(isPossible) {
                        i += L - 1;
                        flat = 0;
                    } else {
                        break;
                    }
                }

                //4. 높이의 차가 1 이상일 경우
                else break;

                //열의 마지막까지 도달했을 경우 길 횟수 증가
                if(i == N-1) answer++;

            }
        }

        System.out.println(answer);

    }   //main 끝

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

}
