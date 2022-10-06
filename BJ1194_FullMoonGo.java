package algoSecond.day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1194_FullMoonGo {
	
	private static int N, M;
	private static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	private static char[][] map;
	private static boolean[][][] visited;
	
	private static class Point {
		int r, c, key, cnt;

		public Point(int r, int c, int key, int cnt) {
			this.r = r;
			this.c = c;
			this.key = key;	//키 뭐 갖고 있는지 2진수 -> 10진수
			this.cnt = cnt;
			//fedcba
			//#1. a랑 b를 가지고 있으면 000011 -> 3
			//#2. d랑 c를 가지고 있으면 001100 -> 12
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][64];  
		Point start = new Point(-1, -1, -1, -1);
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '0') {
					start = new Point(i, j, 0, 0);
				}
			}
		}
		
		System.out.println(bfs(start));
		
	}
	
	private static int bfs(Point p) {
		
		Queue<Point> queue = new LinkedList<Point>();
		visited[p.r][p.c][p.key] = true;
		queue.offer(p);
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(map[cur.r][cur.c] == '1') return cur.cnt;	//도착지에 도착했다면 
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				int key = cur.key;
				int cnt = cur.cnt;
				
				if(isIn(nr, nc) && map[nr][nc] != '#' && !visited[nr][nc][key]) {
					char c = map[nr][nc];	// . a A
					
					//움직인 곳에 키가 있다면 
					if(c >= 'a' && c <= 'f') {	
						key = key | 1 << (c-'a');	//febcda 중 방금 찾은 자리(2진수)에 1 넣기 (비트 OR 연산)
						visited[nr][nc][key] = true;
						queue.offer(new Point(nr, nc, key, cnt+1));
					}
					
					//움직인 곳에 문이 있다면 
					else if(c >= 'A' && c <= 'F') {
						int door = 1 << map[nr][nc] - 'A';
						if((key & door) != 0) {	//and 연산을 했는데 0이 나오면 키가 없고, 0이 아니라면 키가 있는 것 
							visited[nr][nc][key] = true;
							queue.offer(new Point(nr, nc, key, cnt+1));
						}
					}
					
					//그냥 갈 수 있는 길이라면 
					else if(c == '.' || c == '0' || c == '1') {	
						visited[nr][nc][key] = true;
						queue.offer(new Point(nr, nc, key, cnt+1));
					}

				}
				
			}
			
		}
		
		return -1;
	}
	
	private static boolean isIn(int r, int c) {
		if(r >= 0 && c >= 0 && r < N && c < M)
			return true;
		else
			return false;
	}

}
