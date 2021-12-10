// ��ȸ�� ����(SNS) (���� 2533��)

/* ��� 1���� �󸮾������ ���� �ƴ� ���� ������ ����� ���� ������ 
 * - ver1 : DP �̿�
 * - ver2 : DFS �̿�
 */

package baekjoon;

// ������ ū ������ ���� ���� dfs �ϸ鼭 üũ -> ����
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2533 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ������ ����
		int[] level = new int[N+1]; // �� ��庰 level ���� (�ִ� level ��� ã�� ����)
		ArrayList<Integer> arr[] = new ArrayList[N+1];
		
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
			if(from < to) level[to] = level[from] + 1;
			else level[from] = level[to] + 1;
		}
		System.out.println(Arrays.toString(level));
		
		int max = Arrays.stream(level).max().getAsInt();
		Boolean[] isVisited = new Boolean[N+1]; // �湮�ߴ��� üũ
		Boolean[] check = new Boolean[N+1]; // �󸮾�������� üũ
		Arrays.fill(isVisited, false);
		check[1] = true;
		
		for(int i=1; i<N+1; i++) {
			Collections.sort(arr[i]);
		}
		
		for(int i=1; i<=N; i++) {
			if(level[i] == max) {
				earlyadopter(arr, isVisited, check, level, N, i);
			}
		}

		int count = 0;
		for(int i=0; i<check.length; i++) {
			if(check[i]==null) continue;
			if(check[i]) count++;
		}
		System.out.println(Arrays.toString(check) + " " + count);
	}
	
	public static void earlyadopter(ArrayList<Integer>[] graph, Boolean[] isVisited, Boolean[] check, int[] level, int N, int V) {
		Stack<Integer> stack = new Stack<>();
		boolean chk;
		isVisited[V] = true;
		check[V] = false;
		stack.push(V);
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			chk = false;

			for(Integer i : graph[x]) { // x : ���� ��� , i : ���� ���
				if(!isVisited[i]) {
					if(level[x] > level[i] && x != V) {
						if(check[i]==null) check[x] = true;
						else if(check[i]==true) {
							check[i] = false;
							//System.out.println("������? " + x + " " + i);
							//System.out.println(Arrays.toString(check));
						}
					}
					if(level[x] < level[i] && x != V) {
						if(check[i]==null) check[x] = true;
						else if(check[i]==true) check[i] = false;
					}
					stack.push(i);
					chk = true;
					isVisited[i] = true;
					break; // �ʼ�
				}
			}
			if(!chk) stack.pop();
		}
	}
}
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2533 {
	static int[][] dpList;
	static boolean[] isVisited; 
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ������ ����
		ArrayList<Integer> arr[] = new ArrayList[N+1];
		
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
		}
		
		dpList = new int[N+1][2]; // �迭�� ũ�Ⱑ 2�� ���� : �ش� ���(�ε���)���� �󸮾������ ���(O) || �󸮾���Ͱ� �ƴ� ���(X)
		isVisited = new boolean[N+1];
		//dp(arr, 1, 0);
		dfs(arr, 1);
		System.out.print(Math.min(dpList[1][0], dpList[1][1]));
	}
	// dp ����
	public static void dp(ArrayList<Integer>[] tree, int child, int parent) {
		dpList[child][0] = 0; // �ش� ��忡�� �󸮾���Ͱ� �ƴ϶�� ������ ��� -> ���� ī��Ʈ 0 ����
		dpList[child][1] = 1; // �ش� ��忡�� �󸮾���Ͷ�� ������ ��� -> ���� ī��Ʈ 1 ����
		
		for(Integer next: tree[child]) {
			if(next != parent) { // �ڽ��� �Ѿ�� parent���� ������ ��� �ݺ��� (��ȯ������ �ȵ�)
				dp(tree, next, child);
				dpList[child][0] += dpList[next][1];
				dpList[child][1] += Math.min(dpList[next][0], dpList[next][1]);
			}
		}
	}
	// dfs ����
	public static void dfs(ArrayList<Integer>[] tree, int node) {
		dpList[node][0] = 0;
		dpList[node][1] = 1;
		isVisited[node] = true;
		
		for(Integer next : tree[node]) {
			if(!isVisited[next]) {
				dfs(tree, next);
				dpList[node][0] += dpList[next][1];
				dpList[node][1] += Math.min(dpList[next][0], dpList[next][1]);
			}
		}
	}
}