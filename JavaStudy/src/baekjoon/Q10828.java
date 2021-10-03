package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class Q10828 {

	static public void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int len = input.nextInt();
		Stack<Integer> stack = new Stack<>();
		StringBuffer output = new StringBuffer();
		
		for(int i=0; i<len; i++) {
			String str = input.next();
			
			switch(str) {
			
			case "push":
				stack.push(input.nextInt());
				break;
				
			case "pop":
				if(stack.empty()) output.append(-1).append('\n');
				else output.append(stack.pop()).append('\n');
				break;
			
			case "size":
				output.append(stack.size()).append('\n');
				break;
			
			case "empty":
				if(stack.empty()) output.append(1).append('\n');
				else output.append(0).append('\n');
				break;
			
			case "top":
				if(stack.empty()) output.append(-1).append('\n');
				else output.append(stack.peek()).append('\n');
				break;
			}
		}
		input.close();
		System.out.println(output);
	}
}
