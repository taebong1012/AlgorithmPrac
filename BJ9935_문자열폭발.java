package com.Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ9935_문자열폭발 {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));

            //stack의 문자열 개수가 폭탄문자열의 길이 이상일때부터 체크
            if(stack.size() >= bomb.length()) {
                boolean isMatch = true;

                for(int j = 0; j < bomb.length(); j++) {
                    //폭발 문자열과 문자가 다르다면 중단
                    if(stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }

                if(isMatch) {
                    for(int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        if(stack.size() == 0) {
            System.out.println("FRULA");
        } else {
            for(Character c : stack) {
                sb.append(c);
            }
            System.out.println(sb);
        }


    }   //Main 끝
}
