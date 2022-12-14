package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13904_과제 {

    private static class Assignment {
        int d, w;

        public Assignment(int d, int w) {
            this.d = d;
            this.w = w;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<Assignment> assignments = new ArrayList<Assignment>() {
        };
        int sum = 0;
        int maxDay = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            assignments.add(new Assignment(d, w));
            maxDay = Math.max(d, maxDay);
        }

        for(int day = maxDay; day >= 1; day--) {

            Assignment haveToDo = new Assignment(0, 0);

            for(Assignment a:assignments) {
                if(a.d >= day && a.w > haveToDo.w) haveToDo = a;
            }

            sum += haveToDo.w;
            assignments.remove(haveToDo);

        }

        System.out.println(sum);

    }   //main 끝

}
