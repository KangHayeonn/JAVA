// 최소, 최대 (백준 10818번)
package baekjoon;

import java.util.Scanner;


public class Q10818 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int max = sc.nextInt();
		int min = max;
		
		for(int i=1; i<count; i++) {
			int num = sc.nextInt();
			if(num>max) max = num;
			if(num<min) min = num;
		}
		System.out.println(min + " " +max);
		sc.close();
	}
}
