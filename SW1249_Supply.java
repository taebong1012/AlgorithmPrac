package algoSecond.day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SW1249_Supply {
	
	private static int N, min;
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	private static int[][] map, dp;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		
		StringBuilder answer = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			answer.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			
			//2차원 배열들 초기화 
			map = new int[N][N];
			for(int i = 0; i < map.length; i++ ) {
				String input = br.readLine();
				for(int j = 0; j < map[i].length; j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}
			
			dp = new int[N][N];
			for(int i = 0; i < dp.length; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			dp[0][0] = 0;

			visited = new boolean[N][N];
			
			bfs(0, 0);
			answer.append(min + "\n");
			
		}	//테스트 케이스 끝 
		
		System.out.println(answer);
	}	//main 끝 
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int a = tmp[0];
			int b = tmp[1];
			
			//마지막 지점에 도착했을 때 비용이 현재까지의 최소비용보다 작다면 업데이트 
			if(a == N-1 && b == N-1) {
				if(min >= dp[N-1][N-1]) min = dp[N-1][N-1];
			}
			
			if(min < dp[a][b]) continue;
			
			for(int i = 0; i < 4; i++) {
				int nx = a + dx[i];
				int ny = b + dy[i];
				if(isIn(nx, ny)) {
					if(!visited[nx][ny] || dp[nx][ny] > dp[a][b] + map[nx][ny]) {
						visited[nx][ny] = true;
						dp[nx][ny] = dp[a][b] + map[nx][ny];
						q.offer(new int[]{nx, ny});
					}
				}
			}
		}
	}
	
	private static boolean isIn(int x, int y) {
		if(x >= 0 && y >= 0 && x < N && y < N)
			return true;
		else
			return false;
	}
}


