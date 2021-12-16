// 가장 긴 증가하는 부분 수열

/* [ 알고리즘 ]
 * 
 * 1. 초기 dp를 모두 1로 설정 (각 인덱스가 적어도 본인을 포함해 최소 수열의 크기 1이므로)
 * 2. 각 배열마다 자신보다 인덱스가 작은 값들을 기준으로 본인보다 더 작은 값이 있으면 해당 dp값을 +1 해줌
 * 3. 그 중 가장 max 값을 2번의 인덱스의 dp값으로 설정
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11053 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] A = new int[count];
		
		for(int i=0; i<count; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[count];
		int[] list = new int[count];
		Arrays.fill(dp, 1);
	
		for(int i=0; i<count; i++) {
			if(list[i] == 0) {
				list[i] = A[i];
				for(int j=i-1; j>=0; j--) {
					if(list[i] > list[j]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
					}
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<count; i++) {
			if(dp[i] > max) max = dp[i];
		}
		System.out.print(max);
	}
}
