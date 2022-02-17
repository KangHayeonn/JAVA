// �ű� ���̵� (���α׷��ӽ� LEVEL1 - Kakao)


package programmers;

public class NewId {
	public static void main(String args[]) {
		String new_id = "...!@BaT#*..y.abcdefghijklm";
		System.out.println(solution(new_id));
	}
	public static String solution(String new_id) {
		String answer = new_id.toLowerCase(); // 1�ܰ�
		answer = answer.replaceAll("[^a-z\\d\\-_.]", "").replaceAll("\\.{2,}","."); // 2�ܰ� & 3�ܰ�
		// ^ : ���ԽĿ��� not�� �ǹ� , \\. : ��ħǥ, {2,} : 2ȸ �̻� ( {} -> Ƚ�� �Ǵ� ������ ��Ÿ�� )
		
		/*
		if(answer.charAt(0) == '.') {
			answer = answer.substring(1);
		}
		*/
		
		answer = answer.replaceAll("^[.]|[.]$", ""); // 4�ܰ�
		// ^ : ���ڿ��� ����, $ : ���ڿ��� ��, | : or ���� 
		
		if(answer == null || answer.isEmpty()) {
			answer = "a";
		}
		
		if(answer.length() > 15) {
			answer = answer.substring(0, 15).replaceAll("[.]$", "");
		}
		
		if(answer.length() <= 2) {
			char c = answer.charAt(answer.length()-1);
			while(answer.length() < 3) {
				answer += c;
			}
		}
		System.out.println(answer);
		return answer;
	}
}
