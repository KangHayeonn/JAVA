// ��ü���� ��� (���α׷��ӽ� LEVEL2 - Kakao)

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
		answer = 0; // ���������� �Լ� �ȿ��� �ʱ�ȭ ���Ѿ� ���α׷��ӽ����� ���
		
		permutation("", visited, 0, 8, 8, data);
		
		return answer;	
	}
	// ����
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
			int num = datas[i].charAt(4)-'0'; // ���� -> ���� ��ȯ
			
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
