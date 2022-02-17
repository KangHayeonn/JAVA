// 신규 아이디 (프로그래머스 LEVEL1 - Kakao)


package programmers;

public class NewId {
	public static void main(String args[]) {
		String new_id = "...!@BaT#*..y.abcdefghijklm";
		System.out.println(solution(new_id));
	}
	public static String solution(String new_id) {
		String answer = new_id.toLowerCase(); // 1단계
		answer = answer.replaceAll("[^a-z\\d\\-_.]", "").replaceAll("\\.{2,}","."); // 2단계 & 3단계
		// ^ : 정규식에서 not을 의미 , \\. : 마침표, {2,} : 2회 이상 ( {} -> 횟수 또는 범위를 나타냄 )
		
		/*
		if(answer.charAt(0) == '.') {
			answer = answer.substring(1);
		}
		*/
		
		answer = answer.replaceAll("^[.]|[.]$", ""); // 4단계
		// ^ : 문자열의 시작, $ : 문자열의 끝, | : or 연산 
		
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
