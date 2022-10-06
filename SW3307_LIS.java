package algoSecond.day1006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3307_LIS {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] LIS = new int[N];
			
			int max = 0;
			for(int i=0; i < N; i++) {
				LIS[i] = 1;
				for(int j = 0; j < i; j++) {
					if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
						LIS[i] = LIS[j] + 1;
					}
				}
				max = Math.max(max, LIS[i]);
			}
			
			System.out.println("#" + tc + " " + max);
			
		}	//테스트 케이스 끝 
		
		
	}	//Main 끝 

}
