// ������ (���� 1043��)

/* [ �˰��� ]
 * 
 * 1. ������ �˰� �ִ� ������� �迭�� ����
 * 2. �� ������ ��Ƽ�� �ִ� ������� �������� �׷��� ������� (����� �׷���) & ���� ��Ƽ�� �ִ� ������� ���� ����
 * 3. 1���� �������� 2���� ���� DFS �� ���� ������ �ƴ� ������� ��� üũ
 * 4. ������ �ƴ� ����� party�� �Ѹ��̶� ������ �� ��Ƽ�� ������ ����� �� ���� ��
 * 5. 4���� �ش����� �ʴ� ��츸 count����
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1043 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ����� ��
		int M = Integer.parseInt(st.nextToken()); // ��Ƽ�� ��
		st = new StringTokenizer(br.readLine());
		int count = Integer.parseInt(st.nextToken()); // ������ �ƴ� ����� ��
		boolean[] trueKnow = new boolean[N+1];

		for(int i=0; i<count; i++) {
			int x = Integer.parseInt(st.nextToken());
			trueKnow[x] = true;
		}
		
		int[][] party = new int[M][1]; // party�� ������ ��� �� �Ѹ� ����
		int[][]	graph = new int[N+1][N+1]; // ���� ��Ƽ�� ������ ����� 1, �ƴ� ���� 0
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int[] list = new int[number];
			
			for(int j=0; j<number; j++) {
				int person = Integer.parseInt(st.nextToken());
				list[j] = person;
				if(j==0) {
					party[i][0] = person;
				}
			}
			
			for(int x=0; x<list.length; x++) {
				int person1 = list[x];
				
				for(int y=0; y<list.length; y++) {
					int person2 = list[y];
					
					if(person1!=person2) { // ����� �׷��� (���� ��Ƽ�� ������� ��� ����)
						graph[person1][person2] = 1;
						graph[person2][person1] = 1;
					}
				}
			}
		}
		
		// ������ �ƴ� ����� �������� DFS ����
		for(int i=1; i<trueKnow.length; i++) {
			if(trueKnow[i]) {
				trueKnow = DFS(graph, trueKnow, N, M, i);
			}
		}
		
		int answer = 0;
		for(int i=0; i<party.length; i++) {
			int person = party[i][0];
			
			if(!trueKnow[person]) answer = answer+1;
		}

		System.out.println(answer);
	}
	
	
	public static boolean[] DFS(int[][] graph, boolean[] trueKnow, int N, int M, int V) {
		Stack<Integer> stack = new Stack<>();
		boolean check;
		stack.push(V);
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			check = false;
			
			for(int i=1; i<graph[0].length; i++) {
				if(graph[x][i] == 1 && !trueKnow[i]) {
					trueKnow[i] = true;
					stack.push(i);
					check = true;
					break;
				}
			}
			
			if(!check) stack.pop();
		}
		
		return trueKnow;
	}
}
