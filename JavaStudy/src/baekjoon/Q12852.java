// 1로 만들기 2 (백준 12852번)

/* [ 알고리즘 ]
 * 
 * 1. dp 이용해서 이전 값을 저장
 * 2. 1부터 시작해서 N까지 연산을 채워나감
 * 3. 3으로 나눈값, 2로 나눈값, 1을 뺀 값중에 연산의 수가 제일 작을 경우 그것을 저장
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q12852 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 자연수 N
		
		int[] dp = new int[N+1]; // 인덱스가 숫자를 의미 (1~N)
		String[] calc = new String[N+1]; // 연산 리스트
		
		dp[1] = 0; calc[1] = "1";
		// dp[2] = 1; calc[2] = "2" + " " + "1";
		// dp[3] = 1; calc[3] = "3" + " " + "1";
		
		for(int i=2; i<=N; i++) {
			dp[i] = 1000001;
		}
		
		int check = 0; // 1: -1, 2: %2, 3: %3일 경우
		
		for(int i=2; i<=N; i++) { 
			
			if(i%3==0) {
				if(dp[i/3]+1 < dp[i]) {
					dp[i] = dp[i/3]+1;
					check = 3;
				}
			}
			
			if(i%2==0) {
				if(dp[i/2]+1 < dp[i]) {
					dp[i] = dp[i/2]+1;
					check = 2;
				}
			}

			if(dp[i-1]+1 < dp[i]) {
				dp[i] = dp[i-1] + 1;
				check = 1;
			}
			
			switch(check) {
				case 1: calc[i] = i + " " + calc[i-1]; break;
				case 2: calc[i] = i + " " + calc[i/2]; break;
				case 3: calc[i] = i + " " + calc[i/3]; break;
				default : break;
			}
			// System.out.println(i + " -> " +dp[i]);
		}
		
		System.out.println(dp[N]);
		System.out.println(calc[N]);
		
	}
}