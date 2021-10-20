// 카드 (백준 1835번)

// 힌트의 역순으로 알고리즘 작성
// 덱을 이용

// [알고리즘] 
// 책상 위에 올려놓는걸 deque 맨앞에 먼저 추가
// 해당 숫자 만큼 맨뒤 숫자를 맨앞으로 이동
// 1까지 이 과정을 반복

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
			if(deque.isEmpty()) {
				deque.add(i);
				continue;
			}
			
			deque.addFirst(i);
			int count = i;
			while(count > 0) {
				deque.push(deque.pollLast());
				count--;
			}
		}
		while(deque.size()!=0) {
			if(deque.size()==1) System.out.print(deque.poll());
			else System.out.print(deque.poll() +" ");
		}
		input.close();
	}
}
