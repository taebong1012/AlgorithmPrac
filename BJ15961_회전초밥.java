package com.day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ15961_회전초밥 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//초밥벨트에 놓인 접시의 수 
		int d = Integer.parseInt(st.nextToken());	//초밥의 가짓 수 
		int k = Integer.parseInt(st.nextToken());	//연속해서 먹는 접시의 수 
		int c = Integer.parseInt(st.nextToken());	//쿠폰 번호 
		
		int[] sushi = new int[N+k-1];
		int[] visited = new int[d+1];	//중복된 개수를 저장할 배열 
		Arrays.fill(visited, 0);
		
		for(int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		//회전초밥이니까 뒤에 k-1개를 더 추가해줌 
		for(int i = N; i < N+k-1; i++) {
			sushi[i] = sushi[i-N];
		}
		
		int max = 0;	//최대 가지수 
		int left = 0;
		int right = k;
		int cnt = 0;
		
		for(int i = 0; i < right; i++) {
			if(visited[sushi[i]] == 0) {	//배열에 있는 스시가 아니라면 
				cnt++;
			}
			visited[sushi[i]]++;
		}
		if(visited[c] == 0) {
			cnt++;
		}
		
		max = cnt;
		
		while(right < sushi.length) {
			
			int newSushi = sushi[right++];
			int deleteSushi = sushi[left++];
			
			if(visited[c] == 0) {	//쿠폰으로 받았을 경우 일단 안받았다고 가정 
				cnt--;
			}
			
			//삭제 
			visited[deleteSushi]--;
			if(visited[deleteSushi] == 0) {
				cnt--;
			}
			
			//추가 
			if(visited[newSushi] == 0) {
				cnt++;
			}
			visited[newSushi]++;
			
			//쿠폰 스시가 지금 먹은거에 없다면 추가 
			if(visited[c] == 0) {
				cnt++;
			}
			
			max = Math.max(max, cnt);
			
		}
		
		System.out.println(max);
		
	}	//main 끝 

}
