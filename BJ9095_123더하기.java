package com;

import java.util.Scanner;

public class BJ9095_123더하기 {	//다이나믹 프로그래밍 
	
	private static StringBuilder sb;
	private static int answer;
	private static int[] numbers = {1,2,3};

	public static void main(String[] args) {
		
		sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			
			int n = sc.nextInt();
			
			answer = 0;
			go(n);
			sb.append(answer + "\n");
			
			
		}	//테스트 케이스 끝 
		System.out.println(sb);
		sc.close();
	}	//main 끝 

	private static void go(int remain) {
		
		if(remain == 0) {
			answer++;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			if(numbers[i] <= remain) {
				
				remain -= numbers[i];
				go(remain);
				remain += numbers[i];
				
			}
		}
		
	}

}
