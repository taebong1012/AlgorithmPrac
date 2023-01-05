package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ19640_RuleOfToilet {

    private static class Employee implements Comparable<Employee> {
        boolean isDeka; //이 객체가 데카인지 확인
        int days, hurry;    //근무 일수, 급한 정도

        public Employee(boolean isDeka, int days, int hurry) {
            this.isDeka = isDeka;
            this.days = days;
            this.hurry = hurry;
        }

        @Override
        public int compareTo(Employee o) {

            //같은 근무일수 일 경우
            if(this.days - o.days == 0) {
                return this.hurry - o.hurry;
            }
            else
                return this.days - o.days;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //임시 화장실에 대기하고 있는 사원의 수
        int M = Integer.parseInt(st.nextToken());   //사장이 지시한 새로운 줄의 수
        int K = Integer.parseInt(st.nextToken());   //급한 정도



    }
}
