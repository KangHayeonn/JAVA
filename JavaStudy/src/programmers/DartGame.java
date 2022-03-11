// [1��] ��Ʈ ���� (���α׷��ӽ� LEVEL1 - Kakao)

package programmers;

import java.util.ArrayList;
import java.util.Stack;

public class DartGame {
	public static void main(String args[]) {
		String s = "1S2D*3T";
		System.out.println(solution(s));
	}
	public static int solution(String dartResult) {
		int answer = 0;
		int num = 0;
		ArrayList<Integer> arr = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<dartResult.length(); i++) {
			char c = dartResult.charAt(i);
			
			if(Character.isDigit(c) ) {
				if(num==0) {
					num = c - '0'; // ���ڷ� ��ȯ (0~9)
				} else { // num =1 �϶�
					num = num*10; // ���� 10 �� ���� ���
				}
			}
			
			else {
				switch(c) {
					case 'S': num= num*1; arr.add(num); break;
					case 'D': num= num*num; arr.add(num); break;
					case 'T': num= num*num*num; arr.add(num); break;
					case '*': arr.add(-1); break;
					case '#': arr.add(-2); break;
					default: break;
				}
				num=0;
			}
		}
		
		for(int i: arr) {
			if(i==-1 || i==-2) { // -1: *, -2: #
				int n1 = 0;
				int n2 = 0;
				
				if(i==-1) {
					if(stack.size()==1) {
						n1 = stack.pop() * 2;
						stack.add(n1);
					} else {
						n1 = stack.pop() * 2;
						n2 = stack.pop() * 2;
						stack.add(n2);
						stack.add(n1);
					}
				} else {
					n1 = stack.pop() * (-1);
					stack.add(n1);
				}
			} else {
				stack.add(i);
			}
		}
		
		while(!stack.isEmpty()) {
			int a = stack.pop();
			answer += a;
		}

		return answer;
	}
}
