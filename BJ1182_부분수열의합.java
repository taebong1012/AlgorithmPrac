package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1182_부분수열의합 {	//부분집합 
	
	private static int answer, N, S;
	private static int[] input;
	private static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//주어지는 정수 원소 
		input = new int[N];
		isSelected = new boolean[N];
		
		S = Integer.parseInt(st.nextToken());	//만들어야하는 정수 
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0);
		
		System.out.println(answer);
		
	}	//main 끝 
	
	private static void subset(int index) {
		
		if(index == input.length) {
			boolean flag = false;	//공집합을 체크하기 위한 불리언 
			int sum = 0;
			for(int i = 0; i < input.length; i++) {
				if(isSelected[i]) {
					flag = true;
					sum += input[i];
				}
			}
			if(sum == S && flag) answer++;
			return;
		}
		
		isSelected[index] = true;
		subset(index+1);
		isSelected[index] = false;
		subset(index+1);
	}

}
