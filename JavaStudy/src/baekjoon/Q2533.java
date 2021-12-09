// ��ȸ�� ����(SNS)

/* DP? �޸������̼�?
 * 
 */

package baekjoon;

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
		/*
		// Ʈ�� Ȯ��
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<arr[i].size(); j++) {
				System.out.print(i + " -> " +arr[i].get(j)+ " ");
			}
			System.out.print("\n");
		}*/
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
