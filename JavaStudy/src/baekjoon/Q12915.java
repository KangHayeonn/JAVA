// 대회 개최 (백준 12915번)

/* [ 알고리즘 ]
 * 
 * 1. E개의 난이도에서 먼저 뽑아줌 (없으면 EM에서 뽑아줌) -> E, EM 모두 0개 일 경우 break;
 * 2. 1번이 성공한 경우 M의 난이도에서 뽑아줌 (없으면 EM과 HM중에서 뽑아줌)
 * 3. 2번의 경우 M의 난이도가 없을 경우 EM과 HM중 갯수가 더 많은 것에서 뽑아줌 -> 갯수가 동일할 경우 EM에서 뽑아줌
 * 4. M, EM, HM이 모두 0개일 경우 break;
 * 5. 2-3번이 성공한 경우 H의 난이도에서 뽑아줌 (없으면 HM에서 뽑아줌) -> H, HM 모두 0개일 경우 break;
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q12915 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] level = new int[5]; // E EM M MH H
		for(int i=0; i<5; i++) {
			level[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean E, M, H ;
		E = M = H = false;
		int sum = 0;
		
		while(true) {
			if(level[0] > 0) { // 난이도 E의 문제
				level[0]--;
				E = true;
			} else if(level[1] > 0) {
				level[1]--;
				E = true;
			} else {
				break;
			}
			
			if(E) {
				if(level[2] > 0) { // 난이도 M의 문제
					level[2]--;
					M = true;
				} else if(level[1] > 0 || level[3] > 0) {
					if(level[1] >= level[3]) {
						level[1]--;
						M = true;
					} else {
						level[3]--;
						M = true;
					}
				} else {
					break;
				}
			}
			
			if(E && M) { // 난이도 H의 문제
				if(level[4] > 0) {
					level[4]--;
					H = true;
				} else if(level[3] > 0) {
					level[3]--;
					H = true;
				} else {
					break;
				}
			}
			
			if(E && M && H) sum++;
			
			E = M = H = false;
		}
		
		System.out.println(sum);
	}
}
