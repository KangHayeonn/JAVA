// ȸ��̱� (���� 2660��)

/* [ �˰��� ]
 * 
 * 1. �� ���� ���� BFS(�ʺ�켱Ž��)�� ���� �ٸ� �������� �ִܰŸ��� ��������
 * 2. ������ 1�� ��, [0,1,2,2,3] | ������ 2�� ��, [1,0,1,1,2] �̷������� 5����
 * 3. �� �������� �ٸ� ������ ����� �ִܰŸ� �迭���� �ִ��� ������ ������ �������� �´� �ε����� �־��� ex. [3,2,2,2,3]
 * 4. 3������ �ּڰ��� ���� �װͰ� ���� �ε����� ���ο� �迭(answer)�� �������ְ� count++����
 * 5. �ּڰ��� count �׸��� answer �迭�� ����Ǿ� �ִ� ������ ������������ ���
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2660 {
	public static class type {
		private int node; 
		private int depth; 

		public type (int node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		StringTokenizer st = new StringTokenizer("");

		while(true) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if(from == -1 && to == -1) break;
			
			arr[from][to] = 1;
			arr[to][from] = 1;
		}
		
		Boolean[] isVisited =  new Boolean[N+1];
		int[] shortPath = new int[N+1];
		int[] maxList = new int[N];
		int min = Integer.MAX_VALUE; // ȸ�� �ĺ��� ����
		
		for(int i=1; i<N+1; i++) {
			Arrays.fill(isVisited, false);
		    shortPath = BFS(arr, isVisited, shortPath, i);
		    int maxPath = Arrays.stream(shortPath).max().getAsInt();
		    if(maxPath <= min) min = maxPath;
		    //if(maxPath == min) count++;
		    maxList[i-1] = maxPath;
		}
		
		int count =0; // ȸ�� �ĺ��� ��
		ArrayList<Integer> answer = new ArrayList<>();
		for(int i=0; i<maxList.length; i++) {
			if(maxList[i]==min) {
				count++;
				answer.add(i+1);
			}
		}
		
		System.out.println(min + " " + count);
		for(Integer i: answer) {
			System.out.print(i + " ");
		}
	}
	public static int[] BFS(int[][] arr, Boolean[] isVisited, int[] answer, int V) {
		Queue<type> queue = new LinkedList<type>();
		isVisited[V] = true;
		queue.add(new type(V, 0));
		answer[V] = 0;
		
		while(!queue.isEmpty()) {
			type q = queue.poll();
			int x = q.node;
			int depth = q.depth;
			
			for(int i=1; i<arr.length; i++) {
				if(arr[x][i]==1 && !isVisited[i]) {
					queue.add(new type(i, depth+1));
					isVisited[i] = true;
					answer[i] = depth+1;
				}
			}
		}
		return answer;
	}
}
