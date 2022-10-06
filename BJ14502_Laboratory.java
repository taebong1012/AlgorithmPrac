package algoSecond.day1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502_Laboratory {
	
	private static int N, M, maxIndex, answer, cnt;
	private static boolean[][] visited;
	private static int[] index, selected, dr = {-1,1,0,0,}, dc = {0,0,-1,1};
	private static int[][] map, copyMap;	//0: 빈칸, 1: 벽, 2: 바이러스 

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copyMap = new int[N][M];
		visited = new boolean[N][M];
		answer = Integer.MIN_VALUE;
		
		//벽 세울 위치 고르기
		maxIndex = N*M;
		index = new int[maxIndex];
		for(int i = 0; i < maxIndex; i++) {
			index[i] = i;
		}
		selected = new int[3];
		comb(0, 0);
		
		System.out.println(answer);
	}
	
	//maxIndex개 중에 3개 
	public static void comb(int start, int cnt) {
		
		if(cnt == 3) {
			copy();
			//골라진 벽에 세우기 
			for(int i = 0; i < 3; i++) {
				int r = index[i]/M;
				int c = index[i]%M;
				
				if(map[r][c] != 0) return;	//벽을 세울 수 없으므로 리턴 
				else copyMap[r][c] = 1;
			}
			
			//바이러스퍼트리기 
			virus();
			
			//안전영역 세기
			cnt = 0;
			bfs();
			answer = Math.max(answer, cnt);
			return;
		}
		for(int i = start; i < maxIndex; i++) {
			selected[cnt] = index[i];
			comb(start + 1, cnt + 1);
		}
		
	}
	
	private static void bfs() {
		
		Queue<Point> queue = new ArrayDeque<Point>();
		visited[0][0] = true;
		queue.offer(new Point(0,0));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(isIn(nr,nc) && copyMap[nr][nc] == 2) {
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc));
				}
			}
		}
		
		cnt++;	//구역 개수 증가 
	}

	private static void virus() {
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				
				if(copyMap[r][c] == 2) {
					while(true) {
						for(int d = 0; d < 4; d++) {
							int cnt = 0;
							while(true) {
								++cnt;
								int nr = r+dr[d] * cnt;
								int nc = c+dc[d] * cnt;
								if(isIn(nr, nc) && copyMap[nr][nc] == 0) {
									copyMap[nr][nc] = 2;
								} else break;
							}
						}
					}	//while문 끝 
					
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

	private static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	private static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

}
