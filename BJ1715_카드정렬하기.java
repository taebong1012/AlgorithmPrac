package com.Week12;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ1715_카드정렬하기 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        PriorityQueue<Integer> cards = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            cards.add(sc.nextInt());
        }

        int cnt = 0;

        while(cards.size() > 1) {
            int card1 = cards.poll();
            int card2 = cards.poll();

            cnt += card1 + card2;
            cards.add(card1 + card2);
        }

        System.out.println(cnt);

    }

}
