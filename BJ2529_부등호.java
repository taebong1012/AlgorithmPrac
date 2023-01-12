package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2529_부등호 {
    private static Long min = Long.MAX_VALUE;
    private static String minString = "";

    private static Long max = Long.MIN_VALUE;
    private static String maxString = "";
    private static String[] inequality; //부등호 저장
    private static int[] num;   //숫자 저장
    private static boolean[] isUsed = new boolean[10];  //숫자가 사용됐는지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        inequality = new String[K];
        num = new int[K+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            inequality[i] = st.nextToken();
        }

        for(int i = 0; i < 10; i++) {
            Arrays.fill(isUsed, false);
            isUsed[i] = true;
            num[0] = i;
            go(1);
        }

        System.out.println(maxString);
        System.out.println(minString);

    }

    private static void go(int index) {
        if(index == num.length) {
            String stringNum = "";
            for(int i = 0; i < num.length; i++) {
                stringNum += String.valueOf(num[i]);
            }
            Long longNum = Long.parseLong(stringNum);

            if(min > longNum) {
                min = longNum;
                minString = stringNum;
            }
            if(max < longNum) {
                max = longNum;
                maxString = stringNum;
            }

            return;
        }

        for(int i = 0; i < 10; i++) {
            if(!isUsed[i] && isPossible(num[index-1], inequality[index-1], i)) {
                isUsed[i] = true;
                num[index] = i;
                go(index+1);
            } else {
                continue;
            }
            isUsed[i] = false;
        }

    }

    private static boolean isPossible(int num1, String arrow, int num2) {
        if(arrow.equals(">")) {
            if(num1 > num2) return true;
            else return false;
        }
        else {
            if(num1 < num2) return true;
            else return false;
        }

    }
}
