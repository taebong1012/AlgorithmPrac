package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW1767_프로세서연결하기 {

    private static int answer, N, cnt;
    private static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[][] map; //0: 빈 공간, 1: 코어, 9: 선
    private static ArrayList<Node> core;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            answer = Integer.MAX_VALUE;
            core = new ArrayList<Node>();   //코어의 좌표 들어있는 리스트 초기화

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 1) {
                        core.add(new Node(i, j));
                    }
                }
            }

            System.out.println("core리스트의 사이즈: " + core.size());
            connect(0);

            sb.append("#" + tc + " " + answer + "\n");
        }   //테스트 케이스 끝

        System.out.println(sb);

    }   //main 끝

    private static void connect(int index) {
        if(index == core.size()-1) {

            System.out.println("엥?");
            answer = Math.min(answer, cnt);

            return;
        }

        //1. 지금 코어가 벽면에 붙어있다면 다음 코어로
        if(core.get(index).r == 0 || core.get(index).r == N-1 || core.get(index).c == 0 || core.get(index).c == N-1) {
            System.out.println("코어벽면 O");
            connect(index + 1);
        }
        //2. 코어가 벽면에 붙어있지 않다면
        else {
            System.out.println("코어벽면 X");
            loop:
            for(int d = 0; d < 4; d++) {

                int originR = core.get(index).r;  //현재 코어의 행
                int originC = core.get(index).c;  //현재 코어의 열
                int r = originR + direction[d][0];  //전선 확장
                int c = originC + direction[d][1];  //전선 확장

                while(isIn(r,c)) {
                    cnt++;
                    System.out.println("cnt: " + cnt);
                    print();
                    if(map[r][c] != 0) {
                        //백트래킹
                        int od = oppositeD(d);
                        while(r != originR && c != originC) {
                            cnt--;
                            r = r + direction[od][0];
                            c = c + direction[od][1];
                            map[r][c] = 0;
                        }
                        continue loop;
                    } else {
                        r = r + direction[d][0];
                        c = c + direction[d][1];
                        map[r][c] = 9;
                    }
                }

                connect(index + 1);

                //백트래킹
                makeOrigin(r, c, originR, originC, d);
                print();
            }
        }
    }

    //(originR, originC)부터 (r, c)까지 온 길을 다시 0으로 되돌리기
    private static void makeOrigin(int r, int c, int originR, int originC, int d) {

        int od = oppositeD(d);

        while(r != originR && c != originC) {
            map[r][c] = 0;
            r = r + direction[od][0];
            c = c + direction[od][1];
            cnt--;
        }
    }

    //d방향의 반대방향으로 전환
    private static int oppositeD(int d) {
        switch (d) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            case 3:
                return 1;
            default:
                return -1;
        }
    }
    private static boolean isIn(int r, int c) {
        if(r >= 0 && c >= 0 && r < N && c < N) return true;
        else return false;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static void print() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
