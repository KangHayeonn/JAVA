// ��ȥ�� (���� 5567��)

/* [ �˰��� ]
 * 
 * 1. BFS�� �̿��ؼ� ǰ
 * 2. �� -> ģ�� -> ģ���� ģ�� ( �� LEVEL 2������ ���� ���� )
 * 
 */

// �迭�θ� ���� -> �� LinkedList�� �Ȱ��� �ߴµ� �ȵɱ�...
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5567 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c_number = Integer.parseInt(br.readLine());
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st;
		boolean[] isVisited = new boolean[c_number+1];
		
		int[][] arr = new int[c_number+1][c_number+1];
		
		for(int i=0; i<count; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// �����
			arr[from][to] = 1;
			arr[to][from] = 1; // �ܹ����� ��� �̺κ� ����
		}
		
		BFS_Array(arr, isVisited, c_number, 1);			
	}
	
	// BFS ���� : ������� & ť
	public static void BFS_Array(int[][] arr, boolean[] isVisited, int N, int V) {
		isVisited[V] = true;
		int count =0; // �ʴ� ���� ��
		
		for(int i=1; i<N+1; i++) {
			if(arr[V][i]==1) {
				if(!isVisited[i]) {
					count++;
					isVisited[i] = true;
				}
				for(int j=1; j<N+1; j++) {
					if(arr[i][j]==1) {
						if(!isVisited[j]) {
							count++;
							isVisited[j] = true;
						}
					}
				}
			}
		}
		System.out.print(count);
	}
}
