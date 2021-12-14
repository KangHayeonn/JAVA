// 계단 오르기 (백준 2579번)

/* [ 알고리즘 ]
 * 
 * 1. 제일 마지막 n 으로 갈 수 있는 경우의 수는 2가지
 *   (1) n-2 를 거쳐 가는 방법
 *   (2) n-3을 거쳐 n-1까지 거치고 가는 방법
 * 2. 각각의 경우 중 최댓값을 마지막 n에 더해준다.
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2579 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int[] step = new int[count+1];
		
		for(int i=1; i<count+1; i++) {
			step[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dpList = new int[count+1];
		dpList[1] = step[1];
		if(count > 1) dpList[2] = step[1] + step[2]; // count==1일 때, 런타임 에러 (ArrayIndexOutOfBounds) 발생
		int answer = DP(count, step, dpList);
		System.out.print(answer);
	}
	public static int DP(int N, int[] step, int[] dpList) {

		if(dpList[N] == 0 && N >= 3) {
			dpList[N] = Math.max(DP(N-2, step, dpList), DP(N-3, step, dpList) + step[N-1]) + step[N]; 
		}
		return dpList[N];
	}
}
