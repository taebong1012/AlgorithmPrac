package com;

import java.util.Scanner;

public class BJ10974_모든순열 {	//순열 
	
	private static int N;
	private static int[] input, numbers;
	private static boolean[] isSelected;
	private static StringBuilder sb;

	public static void main(String[] args) {

		sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		input = new int[N];
		numbers = new int[N];
		isSelected = new boolean[N];
		
		
		for(int i = 0; i < N; i++) {
			input[i] = i+1;
		}
		
		perm(0);
		
		sc.close();
		System.out.println(sb);
	}
	
	private static void perm(int cnt) {
		
		if(cnt == N) {
			for(int i = 0; i < N; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				numbers[cnt] = input[i];
				perm(cnt+1);
				isSelected[i] = false;
			}
		}
		
	}

}
