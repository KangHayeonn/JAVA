// 수박수박수박수박수박수? (프로그래머스 LEVEL1)

package codingtest_9th_1st;


public class Q1 {

	public static void main(String[] args) {
		Q1 s = new Q1();
		System.out.println(s.solution(4));
	}
	
	public String solution(int n) {
		String answer="";
		for(int i=1; i<=n; i++) {
			if(i%2==1) answer += "수";
			else answer += "박";
		}
		return answer;
	}
}
