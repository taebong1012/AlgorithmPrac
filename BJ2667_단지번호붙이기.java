package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2667_단지번호붙이기 {	//DFS 
	
	private static int N, areaCnt;
	private static int[] dr = {-1, 0, 1, 0}, dc = { 0, -1, 0, 1}, aptCnt;
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {	//DFS
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		aptCnt = new int[N*N];	//아파트 영역 개수 최대 N*N개라고 가정 
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					areaCnt++;	//단지 수 증가 
					dfs(new Point(i, j));
				}
			}
		}
		
		System.out.println(areaCnt);	//단지 수 출력 
		
		Arrays.sort(aptCnt);
		for(int i = 0; i < aptCnt.length; i++) {
			if(aptCnt[i] != 0)
				System.out.println(aptCnt[i]);
		}
		
		
		
	}	//main 끝 
	
	private static void dfs(Point p) {
		
		visited[p.r][p.c] = true;
		aptCnt[areaCnt]++;	//index번째 영역에 아파트 개수 추가 
		
		for(int d = 0; d < 4; d++) {
			int nr = p.r + dr[d];
			int nc = p.c + dc[d];
			
			if(isIn(nr, nc) && map[nr][nc] == 1 && !visited[nr][nc]) {
				dfs(new Point(nr, nc));
			}
		}
		
	}
	
	private static boolean isIn(int nr, int nc) {
		if(nr >= 0 && nc >= 0 && nr < N && nc < N)
			return true;
		else
			return false;
	}
	
	private static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

}
