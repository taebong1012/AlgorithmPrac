package algoSecond.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17070_MovePipe {

	private static int N, cnt;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//집의 크기
		
		//집 상태 입력 받기 
		map = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(1, 2, 1);	//1: 가로, 2: 세로, 3: 대각선
		System.out.println(cnt);
		
	}
	
	private static void move(int r, int c, int dir) {
		
		if(r == N && c == N) {
			++cnt;
		}
		
		//1. 가로 
		if(dir == 1) {
			//1-1. 한번더 가로로 움직임 
			if(c+1 <= N && map[r][c+1] != 1) {
				move(r, c+1, 1);
			}
		}
		//2. 세로 
		else if(dir == 2) {
			//2-1. 한번 더 세로로 움직임 
			if(r+1 <= N && map[r+1][c] != 1) {
				move(r+1, c, 2);
			}
		}
		//3. 대각선 
		else if(dir == 3) {
			//3-1. 가로로 움직이기 
			if(c+1 <= N && map[r][c+1] != 1) {
				move(r, c+1, 1);
			}
			//3-2. 세로로 움직이기 
			if(r+1 <= N && map[r+1][c] != 1) {
				move(r+1, c, 2);
			}
		}
		
		//가로든 세로든 대각선이든 대각선으로는 모두 움직임 가능 
		if(c+1 <= N && r+1 <= N &&
				map[r][c+1] != 1 && map[r+1][c] != 1 && map[r+1][c+1] != 1) {
			move(r+1, c+1, 3);
		}
		
	}

}
