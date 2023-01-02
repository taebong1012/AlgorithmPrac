package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110_공유기설치 {

    private static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //집의 개수
        int C = Integer.parseInt(st.nextToken());   //공유기의 개수

        houses = new int[N];

        for(int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);    //이분탐색을 하기 위한 정렬

        int min = 1;    //라우터 최소 거리
        int max = houses[N-1] - houses[0] + 1;  //라우터 최대 거리

        while(min < max) {
            int middle = (min + max) / 2;

            //1. 설치할 수 있는 공유기의 개수가 설치해야하는 개수보다 적을 경우
            if(cntInstall(middle) < C) {
                max = middle;   //거리 좁히기
            } else {
                min = middle + 1;   //거리 늘리기
            }

        }

        System.out.println(max - 1);
    }   //Main 끝

    //공유기를 몇개 설치할 수 있는지 체크
    private static int cntInstall(int distance) {

        int cnt = 1;
        int lastHouse = houses[0];

        for(int i = 1; i < houses.length; i++) {
            int curHouse = houses[i];

            if(curHouse - lastHouse >= distance) {
                cnt++;
                lastHouse = curHouse;
            }
        }

        return cnt;
    }
}
