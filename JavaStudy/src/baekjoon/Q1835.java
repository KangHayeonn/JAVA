// 카드 (백준 1835번)

// 힌트의 역순으로 알고리즘 작성
// 덱을 이용

package baekjoon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Q1835 {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		Deque<Integer> deque = new LinkedList<>();
		
		for(int i=num; i>0; i--) {
			if(deque.isEmpty()) deque.add(i);
			else if(i==1) {
				deque.add(deque.pop());
				deque.push(i);
				deque.push(deque.pollLast());
			}
			else {
				deque.add(deque.pop());
				deque.push(i);
			}
		}
		while(deque.size()!=0) {
			if(deque.size()==1) System.out.print(deque.poll());
			else System.out.print(deque.poll() +" ");
		}
		input.close();
	}
}
