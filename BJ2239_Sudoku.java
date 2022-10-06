package algoSecond.day1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ2239_Sudoku {
	
	private static int[][] map = new int[9][9];
	private static ArrayList<Point> zeros;
	
	private static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		zeros = new ArrayList<Point>();
		
		//입력 
		for(int i = 0; i < 9; i++) {
			String input = br.readLine();
			for(int j = 0; j < 9; j++) {
				map[i][j] = input.charAt(j) - '0';
				if(map[i][j] == 0) zeros.add(new Point(i, j));
			}
		}
		
		sudoku(0);	//파라미터: 횟수 
		
	}

	private static void sudoku(int cnt) {
		
		if(cnt == zeros.size()) {	//모든칸 채워지면 출력 
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		Point p = zeros.get(cnt);	//채울 좌표 
		int r = p.r;
		int c = p.c;
		
		boolean[] num = new boolean[10];
		
		//가로 탐색 
		for(int i = 0; i < 9; i++) {
			if(map[r][i] != 0) {
				num[map[r][i]] = true;
			}
		}
		
		//세로 탐색 
		for(int i = 0; i < 9; i++) {
			if(map[i][c] != 0) {
				num[map[i][c]] = true;
			}
		}
		
		//3x3 탐색 
		int startX = (r/3)*3;
		int startY = (c/3)*3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(map[startX+i][startY+j] != 0) {
					num[map[startX+i][startY+j]] = true;
				}
			}
		}
		
		//num에 false인 숫자들을 넣음 
		for(int i = 1; i < 10; i++) {
			if(!num[i]) {
				map[r][c] = i;
				sudoku(cnt+1);
				map[r][c] = 0;
			}
		}
		
	}

}
