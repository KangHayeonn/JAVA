// Ʈ��

/* [ �˰��� ]
 * 
 * 1. ����Ŭ�� �ִ��� Ȯ���ϴ� �Լ��� ����� �� (����ŬO -> Ʈ��X)
 * 2. ����Ŭ�� ���� ��� Ʈ�� ������ count ����
 * 3. ����Ŭ�� ���� ��� �н�
 * 4. ���� Ʈ�� ������ 0�� ��� No trees. ���
 * 5. Ʈ�� ������ �ϳ��� ��� There is one tree.
 * 6. Ʈ�������� 1���� Ŭ ��� A forest of T(>1) trees.�� ���
 * 
 */

// NullPointerException �߻� -> ó���� �ٷ� 0 0 ���� �Է��� ���� ���!
// ���� ���� �и�����.

/* [ �߰� ���ǻ��� ]
 * 
 * 1. 1���� �׷����� ������ No trees�� �ƴ϶� ī��Ʈ�� ���Խ�Ű�� ���� ��
 * 2. ��Ʈ ��尡 ������ ���� ���� a->a�� ���� (���̰� 1�� ����Ŭ)
 * 
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q4803 {
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int max = 0;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCount=0;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // ������ ����
			int m = Integer.parseInt(st.nextToken()); // ������ ����
			
			if(n==0 && m==0) break;

			testCount++; // �׽�Ʈ ���̽��� ������ ���� ����
			
			adj = new ArrayList[n+1];
			for(int i=1; i<n+1; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from].add(to);
				adj[to].add(from);
			}
			
			// Ʈ�� Ȯ��
			/*
			for(int i=1; i<n+1; i++) {
				for(int j=0; j<adj[i].size(); j++) {
					System.out.print(i + " -> " +adj[i].get(j)+ " ");
				}
				System.out.print("\n");
			}*/
			
			visited = new boolean[n+1];
			int count = 0;
			
			// �湮���� ���� ������ �������� cycle�� �ٽ� Ȯ���غ� -> cycle�� ������ Ʈ���̹Ƿ� �׶� ���� count ����
			for(int i=1; i<n+1; i++) {
				if(!visited[i] && !isCycle(i, 0)) count++; 
			}
			
			if(count==0) {
				sb.append("Case ").append(testCount).append(": No trees.").append("\n");
			} else if(count==1) {
				sb.append("Case ").append(testCount).append(": There is one tree.").append("\n");
			} else {
				sb.append("Case ").append(testCount).append(": A forest of ").append(count).append(" trees.").append("\n");
			}
		}
		
		System.out.print(sb);
		return;
	}
	
	//DFS �̿� (true: ����Ŭ , false: Ʈ��)
	public static boolean isCycle(int curr, int parent) {
		visited[curr] = true;
		
		for(int next : adj[curr]) {
			if(!visited[next]) {
				if(isCycle(next, curr)) { // �׷����� ����Ŭ�� �ִ��� Ȯ��
					return true;
				}
			} else if(next != parent) { // �湮�� ����� �ִµ� �θ�� �������� ���� ���
				return true;
			}
		}
		return false;
	}
}
