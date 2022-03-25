// 단체사진 찍기 (프로그래머스 LEVEL2 - Kakao)

package TestPractice;

public class GroupPhoto {
	static String[] str = {"A", "C", "F", "J", "M", "N", "R", "T"};
	// static int answer = 0;
	static int answer;
	
	public static void main(String args[]) {
		String[] data = {"N~F=0", "R~T>2"};
		System.out.println(solution(2, data));
	}
	public static int solution(int n, String[] data) {
		boolean[] visited= new boolean[8];
		answer = 0; // 전역변수를 함수 안에서 초기화 시켜야 프로그래머스에서 통과
		
		permutation("", visited, 0, 8, 8, data);
		
		return answer;	
	}
	// 순열
	public static void permutation(String output, boolean[] visited, int depth, int n, int r, String[] datas) {
		if(depth == r) {
			if(check(output, datas)) answer++;
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				String newOutput= output + str[i];
				permutation(newOutput, visited, depth+1, n, r, datas);
				visited[i] = false;
			}
		}
	}
	public static boolean check(String output, String[] datas) {
		for(int i=0; i<datas.length; i++) {
			int posA = output.indexOf(datas[i].charAt(0));
			int posB = output.indexOf(datas[i].charAt(2));
			
			int distance = Math.abs(posB-posA)-1;
			char op = datas[i].charAt(3);
			int num = datas[i].charAt(4)-'0'; // 문자 -> 숫자 변환
			
			switch(op) {
				case '=' :
					if(!(distance == num)) return false; break;
				case '<' :
					if(!(distance < num)) return false; break;
				case '>' :
					if(!(distance > num)) return false; break;
				default: break;
			}
		}
		
		return true;
	}
}
