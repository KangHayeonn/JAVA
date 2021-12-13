// 팰린드롬 만들기 (백준 1254번)

/* [ 알고리즘 ]
 * 
 * 1. 뒤에서 붙이기 위해 substring을 이용해 문자열을 나눠서 체크
 * 2. 팰린드롬인지 체크하기 위해 이분탐색 사용
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1254 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int answer = str.length();
		
		for(int i=0; i<str.length(); i++) {
			if(Palindrome(str.substring(i))) {
				break;
			}
			answer++;
		}
		System.out.print(answer);
	}
	
	public static boolean Palindrome(String str) {
		int left = 0;
		int right = str.length()-1;
		while(left < right) {
			char leftC = str.charAt(left);
			char rightC = str.charAt(right);
			
			if(leftC!=rightC) return false;
			
			if(leftC==rightC) {
				left++;
				right--;
			}
		}
		return true;
	}
}
