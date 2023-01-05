package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ19640_화장실의규칙 {

    private static class Employee {
        boolean isDeka; //이 객체가 데카인지 확인
        int days, hurry, lineNum;    //근무 일수, 급한 정도, M개의 줄 중에 몇번째 줄에 서있었는지(0~M-1)

        public Employee(boolean isDeka, int days, int hurry, int lineNum) {
            this.isDeka = isDeka;
            this.days = days;
            this.hurry = hurry;
            this.lineNum = lineNum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //임시 화장실에 대기하고 있는 사원의 수
        int M = Integer.parseInt(st.nextToken());   //사장이 지시한 새로운 줄의 수
        int K = Integer.parseInt(st.nextToken());   //화장실에 도착했을 때 자신의 앞에 서있던 사원의 수


        Queue<Employee>[] lines = new LinkedList[M]; //사람들이 M개의 줄에 서 있음
        for(int i = 0; i < M; i++) {
            lines[i] = new LinkedList<Employee>();
        }

        //N명의 사원들을 M개의 줄로 나눠세움
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            boolean isDeka = false;
            int days = Integer.parseInt(st.nextToken());
            int hurry = Integer.parseInt(st.nextToken());
            int lineNum = i%M;

            if(i == K) {    //지금 나눠세우는 사람이 데카라면 true 처리
                isDeka = true;
            }

            lines[lineNum].add(new Employee(isDeka, days, hurry, lineNum));
        }

        //각 줄의 선두에 있는 사람의 집합
        PriorityQueue<Employee> firsts = new PriorityQueue<Employee>(
                (o1, o2) -> o1.days == o2.days ?
                        (o1.hurry == o2.hurry ?
                                (o1.lineNum - o2.lineNum)
                                : o1.hurry - o2.hurry)
                        : o1.days - o2.days
        );

        //선두 사원들 초기 세팅
        for(int i = 0; i < M; i++) {
            if(lines[i].size() > 0) {
                firsts.add(lines[i].poll());
            } else break;
        }

        int cnt = 0;
        while(true) {
            ++cnt;
            Employee curEmployee = firsts.poll();

            //데카가 화장실에 들어갔으면 while문 중단
            if(curEmployee.isDeka) break;

            if(lines[curEmployee.lineNum].size() > 0) {
                firsts.add(lines[curEmployee.lineNum].poll());
            }

        }

        System.out.println(cnt-1);

    }   //main 끝

}
