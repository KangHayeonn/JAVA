// 동물원

/* [ 알고리즘 ]
 * 
 * 1. 세가지 경우의 수로 나눔
 *  (1) 사자를 한마리도 배치하지 않은 경우 (lion[n][0])
 *  (2) 사자를 왼쪽칸에 배치한 경우      (lion[n][1])
 *  (3) 사자를 오른 쪽칸에 배치한 경우    (lion[n][2])
 * 2. 이전 값을 메모이제이션 해 다음 출력값에 영향을 줌
 * 3. 각 경우의 수 값에 9901로 나눈 뒤 출력
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1309 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 우리의 세로 크기
		
		int[][] lion = new int[N][3];
		lion[0][0] = 1; // 처음 한마리도 배치되지 않을 경우의 수 한가지를 의미
		lion[0][1] = 1;
		lion[0][2] = 1;
		
		for(int i=1; i<N; i++) {
			lion[i][0] = (lion[i-1][0] + lion[i-1][1] + lion[i-1][2]) % 9901; // 9901 을 넘었을 경우 나머지를 구해줘 오버플로우를 방지.
			lion[i][1] = (lion[i-1][0] + lion[i-1][2]) % 9901;
			lion[i][2] = (lion[i-1][0] + lion[i-1][1]) % 9901;
		}
		
		int sum = 0;
		
		for(int i=0; i<3; i++) {
			sum += lion[N-1][i];
		}
		
		System.out.println((sum%9901));
	}
}
