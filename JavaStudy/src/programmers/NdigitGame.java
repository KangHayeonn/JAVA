// n진수 게임 (프로그래머스 LEVEL2 - Kakao)

/* [ 알고리즘 ]
 * 
 * 1. 숫자를 n진수로 변환해서 큐에 저장
 * 2. 전체 게임돌릴 횟수로 큐의 길이 제한
 * 3. 이후, 큐에서 튜브의 순서에 맞게 answer에 뽑아서 저장
 * 4. 대문자로 변환해서 출력(a -> A)
 * 
 */
package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class NdigitGame {
	public static void main(String args[]) {
		System.out.println(solution(16,16,2,2));
	}
	public static String solution(int n, int t, int m, int p) {
		String answer = "";
		Queue<Character> q = new LinkedList<>();
		int i=0;
		
		while(q.size() < t*m) {
			String num = Integer.toString(i, n);
			
			for(int j=0; j<num.length(); j++) {
				q.add(num.charAt(j));
				if(q.size() == t*m) break;
			}
			i++;
		}

		while(!q.isEmpty()) {
			for(int j=1; j<=m; j++) {
				char c = q.poll();
				
				if(m==p) {
					if(j%m == 0) {
						answer += c;
					}
				} else {
					if(j%m == p) {
						answer += c;
					}
				}
			}
		}
		
		answer = answer.toUpperCase();
		
		return answer;
	}
}
