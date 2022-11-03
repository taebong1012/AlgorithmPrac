package com.Week07;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1094_막대기 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
			
			int X = Integer.parseInt(br.readLine());	//만들려고 하는 cm
			
			int cnt = 1;	//사용한 막대의 수 
			int remain = 64;	//이등분한 막대를 제외하고 남은 길이 
			int after = 64;
			
			while(remain != X) {
				
				after /= 2;
				
				//하나를 버려도 남은 막대들의 길이의 합이 Xcm보다 "크거나 같을" 경우 (막대 개수 유지)
				if(remain - after >= X) remain -= after;
				
				//하나를 버리면 남은 막대들의 길이의 합이 X보다 작아지는 경우 (막대 개수 하나 증가)
				else {
					//계속 이등분한게 X보다 클때 까지
					while(remain - after < X) {
						after /= 2;
						cnt++;
					}
					remain -= after;
			}
				
			
		}	//테스트 케이스 끝 
		System.out.println(cnt);
	}	//MAIN 끝 

}
