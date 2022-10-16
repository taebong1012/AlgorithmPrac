package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2178_미로탐색 {	//BFS
	
	private static int N, M;
	private static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	private static int[][] map;
	private static int[][] cntMap;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cntMap = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		bfs();
		
		System.out.println(cntMap[N-1][M-1]);
		
	}	//main 끝 
	
	private static void bfs() {
		
		Queue<Point> q = new LinkedList<Point>();
		visited = new boolean[N][M];
		
		visited[0][0] = true;
		cntMap[0][0] = 1;
		
		q.offer(new Point(0, 0, 1));
		
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;
			
			for(int d = 0; d < 4; d++) {
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nCnt = cnt + 1;
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					q.add(new Point(nr, nc, nCnt));
					visited[nr][nc] = true;
					cntMap[nr][nc] = nCnt;
				}
				
			}
		}
	}
	
	private static boolean isIn(int nr, int nc) {
		if(nr >= 0 && nc >= 0 && nr < N && nc < M)
			return true;
		else
			return false;
	}

	private static class Point {
		int r, c, cnt;
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
}
