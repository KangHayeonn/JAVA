// 신규 아이디 추천 (프로그래머스 LEVEL1)

package codingtest_9th_4th;

public class Q3 {
	static public void main(String args[]) {
		Q3 s = new Q3();
		System.out.println(s.solution("...!@BaT#*..y.abcdefghijklm"));
	}
	
	public String solution(String new_id) {
		String answer = "";
		String step1 = new_id.toLowerCase();
		System.out.println(step1);
		String match = "[^xfe.-_0-9a-z\\s]";
		step1 = step1.replaceAll(match, "");
		System.out.println(step1);
		return answer;
	}
}
