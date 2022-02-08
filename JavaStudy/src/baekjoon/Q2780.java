// 비밀번호 (백준 2780번)
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2780 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		int[] list = new int[T];
		for(int i=0; i<T; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
	}
}
