// 최대힙 (백준 11279번)

package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q11279 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		List<Integer> numList = new ArrayList<Integer>();
				
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<count; i++) {
			int input = sc.nextInt();
			if(input==0) {
				int output = max(numList);
				sb.append(output).append("\n");
				numList.remove(Integer.valueOf(output));
			}
			else numList.add(input);
		}
		System.out.println(sb);
		sc.close();
	}
	
	public static int max(List<Integer> numList) {
		int max = 0;
		for(Integer i: numList) {
			if(max < i) max = i;
		}
		return max;
	}
}
