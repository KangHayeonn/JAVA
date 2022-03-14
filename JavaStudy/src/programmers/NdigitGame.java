// n���� ���� (���α׷��ӽ� LEVEL2 - Kakao)

/* [ �˰��� ]
 * 
 * 1. ���ڸ� n������ ��ȯ�ؼ� ť�� ����
 * 2. ��ü ���ӵ��� Ƚ���� ť�� ���� ����
 * 3. ����, ť���� Ʃ���� ������ �°� answer�� �̾Ƽ� ����
 * 4. �빮�ڷ� ��ȯ�ؼ� ���(a -> A)
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
