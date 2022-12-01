package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17143_낚시왕 {

    private static int R, C;
    private static Shark[][] sharks, sharksTmp;
    private static int [][] d = {{0,0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()) + 1;   //map을 1부터 사용하기 위해서
        C = Integer.parseInt(st.nextToken()) + 1;
        int M = Integer.parseInt(st.nextToken());   //상어의 수

        int cnt = 0;  //낚시왕이 잡은 상어의 수
        sharks = new Shark[R][C];

        //M개의 상어 정보
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());   //1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
            int size = Integer.parseInt(st.nextToken());
            sharks[r][c] = new Shark(speed, direction, size);
        }

        //낚시왕 활동 시작!
        for(int fisher = 1; fisher < C; fisher++) {


            //2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
            for(int deep = 1; deep < R; deep++) {
                if(sharks[deep][fisher] != null) {
                    cnt += sharks[deep][fisher].size;
                    sharks[deep][fisher] = null;    //잡음 처리
                    break;
                }
            }

            //상어가 동시에 이동하는 것을 구현하기 위한 배열
            //낚시왕이 한번 이동할때마다 초기화 됨
            sharksTmp = new Shark[R][C];

            //3. 상어가 이동한다.
            for(int r = 1; r < R; r++) {
                for(int c = 1; c < C; c++) {

                    if(sharks[r][c] != null) {
                        int direction = sharks[r][c].direction;
                        int speed = sharks[r][c].speed;
                        int size = sharks[r][c].size;
                        int nr = r; //이동 후 행
                        int nc = c; //이동 후 열

                        //a. 상어가 위아래로 이동
                        if(direction == 1 || direction == 2) {
                            speed %= 2*(R-1) - 2;
                        }
                        //b. 상어가 좌우로 이동
                        else {
                            speed %= 2*(C-1) - 2;
                        }

                        //이동해야하는 거리만큼 이동시켜주기
                        for(int s = 0; s < speed; s++) {
                            nr += d[direction][0];
                            nc += d[direction][1];

                            //맵안에 있지 않다면
                            if(!isIn(nr, nc)) {
                                direction = changeDirection(direction); //방향 바꿔주기
                                nr += d[direction][0] * 2;  //이동한 것 다시 되돌려주고 이동해야했던 것 이동
                                nc += d[direction][1] * 2;
                            }
                        }

                        //임시 map에 상어 저장해주기
                        //a. 해당 위치에 다른 상어가 있다면 사이즈 비교해서 더 큰 애로 넣기
                        if(sharksTmp[nr][nc] != null) {
                            if(sharksTmp[nr][nc].size < size) {
                                sharksTmp[nr][nc] = new Shark(speed, direction, size);
                            }
                        }
                        //b. 아무것도 없다면 그냥 상어 넣어도 됨.
                        else {
                            sharksTmp[nr][nc] = new Shark(speed, direction, size);
                        }
                    }

                }
            }

            //sharkTmp에 있던 상어들을 shark로 복사시키자
            for(int i = 1; i < R; i++) {
                for(int j = 1; j < C; j++) {
                    Shark tmp = sharksTmp[i][j];
                    sharks[i][j] = tmp;
                }
            }

        }

        System.out.println(cnt);

    }   //main 끝

    private static void print(Shark[][] a) {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    private static boolean isIn(int r, int c) {
        if(r > 0 && c > 0 && r < R && c < C)
            return true;
        else
            return false;
    }

    private static int changeDirection(int d) {
        switch (d) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
            default:
                return -1;

        }
    }

    private static class Shark {
        int speed, direction, size;

        public Shark(int speed, int direction, int size) {
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        @Override
        public String toString() {
            return "[" + "s: " + speed + ", d: " + direction + ", z: " + size + "]";
        }
    }
}
