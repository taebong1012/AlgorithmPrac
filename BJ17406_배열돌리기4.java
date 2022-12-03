package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17406_배열돌리기4 {

    private static int N, M, K, answer = Integer.MAX_VALUE;
    private static boolean isSelected[];
    private static Rotate[] rotates, selectedRotates;
    private static int[][] map, copiedMap;


    private static class Rotate {
        int r, c, s;

        public Rotate(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //배열의 행
        M = Integer.parseInt(st.nextToken());   //배열의 열
        K = Integer.parseInt(st.nextToken());   //회전 연산의 개수

        map = new int[N][M];
        copiedMap = new int[N][M];
        rotates = new Rotate[K];

        selectedRotates = new Rotate[K];
        isSelected = new boolean[K];

        //배열에 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //회전연산 입력
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            rotates[i] = new Rotate(r, c, s);

        }

        //회전 연산은 모두 한 번씩 사용해야 하며, 순서는 임의로 정해도 되므로 순열을 이용하여 순서 정하기
        perm(0);

        System.out.println(answer);

    }   //main 끝

    private static void getMin() {

        for(int i = 0; i < copiedMap.length; i++) {
            int sum = 0;
            for(int j = 0; j < copiedMap[i].length; j++) {
                sum += copiedMap[i][j];
            }
            answer = Integer.min(answer, sum);
        }

    }

    private static void doRotate(int r, int c, int s) {

        if(s == 0) return;  //종료 조건

        int[] leftUp = {r-s, c-s};
        int[] rightUp = {r-s, c+s};
        int[] rightDown = {r+s, c+s};
        int[] leftDown = {r+s, c-s};
        int[] tmp = {copiedMap[rightUp[0]][rightUp[1]],
                    copiedMap[leftUp[0]][leftUp[1]],
                    copiedMap[leftDown[0]][leftDown[1]],
                    copiedMap[rightDown[0]][rightDown[1]]
                    };

        //정사각형 윗변
        for(int j = rightUp[1]; j > leftUp[1] + 1; j--) {
            copiedMap[rightUp[0]][j] = copiedMap[rightUp[0]][j-1];
        }
        copiedMap[rightUp[0]][leftUp[1]+1] = tmp[1];

        //정사각형 왼쪽변
        for(int i = leftUp[0]; i < leftDown[0] - 1; i++) {
            copiedMap[i][leftUp[1]] = copiedMap[i+1][leftDown[1]];
        }
        copiedMap[leftDown[0]-1][leftDown[1]] = tmp[2];

        //정사각형 밑변
        for(int j = leftDown[1]; j < rightDown[1] - 1; j++) {
            copiedMap[leftDown[0]][j] = copiedMap[rightDown[0]][j+1];
        }
        copiedMap[rightDown[0]][rightDown[1]-1] = tmp[3];

        //정사각형 오른쪽 변
        for(int i = rightDown[0]; i > rightUp[0] + 1; i--) {
            copiedMap[i][rightDown[1]] = copiedMap[i-1][rightUp[1]];
        }
        copiedMap[rightUp[0]+1][rightUp[1]] = tmp[0];

        doRotate(r, c, s-1);    //재귀를 이용하여 안쪽 배열들도 회전 시킴

    }

    private static void copy() {
        int tmp = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                tmp = map[i][j];
                copiedMap[i][j] = tmp;
            }
        }
    }

    private static void perm(int cnt) {
        if(cnt == K) {
            copy(); //회전 시킬 배열복사본 생성
            for(int i = 0; i < K; i++) {
                int r = selectedRotates[i].r;
                int c = selectedRotates[i].c;
                int s = selectedRotates[i].s;
                doRotate(r, c, s);
            }
            getMin();
            return;
        }

        for(int i = 0; i < K; i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                selectedRotates[cnt] = rotates[i];
                perm(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}
