package algoSecond.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1263_PeopleNetwork {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] nodeInfo = new int[N][N];	//간선 정보 저장: 1: 존재, 0: 없음 
			int[][] cnt = new int[N][N];
			int[] nodeCnt = new int[N];
			
			int INF = 999;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					nodeInfo[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && nodeInfo[i][j] == 0) {
						nodeInfo[i][j] = INF;
					}
				}
			}
			
			for(int k = 0; k < N; k++) {	//경유지 
				for(int i = 0; i < N; i++) {	//출발지 
					
					if(i == k) continue;
					
					for(int j = 0; j < N; j++) {	//도착지 
						if(j == i || j == k) continue;
						
						if(nodeInfo[i][j] > nodeInfo[i][k] + nodeInfo[k][j]) {
							nodeInfo[i][j] = nodeInfo[i][k] + nodeInfo[k][j];
							cnt[i][j] += nodeInfo[i][k] + nodeInfo[i][j];
						}
						
					}
					
				}
			}
			
			int answer = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += nodeInfo[i][j];
				}
				answer = Math.min(answer, sum);
			}
			
			System.out.println("#" + tc + " " + answer);
			
		}	//테스트 케이스 끝 
		
	}	//main 끝 

}
