// ��ųƮ�� (���α׷��ӽ� LEVEL2)

// ver1 : ��Ÿ�� ���� (�׽�Ʈ 3,5,7,8,10) -> ����
// check �Լ��� �Ű����� �κ��� �ٲ���
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
			// "[^CBD]" -> "[^"+skill+"]"   (������ ġȯ�ϴ� ��)
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
