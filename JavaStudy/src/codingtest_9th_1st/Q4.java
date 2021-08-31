// 스킬트리 (프로그래머스 LEVEL2)

// ver1 : 런타임 에러 (테스트 3,5,7,8,10) -> 성공
// check 함수의 매개변수 부분을 바꿔줌
package codingtest_9th_1st;

public class Q4 {
	public static void main(String[] args) {
		String[] skill_tree = {"C", "D", "CB", "BDA"};
		Q4 s = new Q4();
		System.out.println(s.solution("CBD", skill_tree));
	}
	public int solution(String skill, String[] skill_tree) {
		int answer = 0;
		for(int i=0; i < skill_tree.length; i++) {
			String str = skill_tree[i].replaceAll("[^"+skill+"]", "");
			int count = check(skill, str);
			if(count==1) answer++;
			// "[^CBD]" -> "[^"+skill+"]"   (변수로 치환하는 법)
		}
        return answer;
	}
	public int check(String skill, String str) {
		if(str.length()==0) {
			return 1;
		}
		if(skill.charAt(0)==str.charAt(0)) {
			StringBuffer strChange = new StringBuffer(str);
			StringBuffer skillChange = new StringBuffer(skill);
			strChange.deleteCharAt(0);
			skillChange.deleteCharAt(0);
			//return check(strChange.toString(), skillChange.toString());
			return check(skillChange.toString(), strChange.toString());
		}
		else return 0;
		
	}
}
