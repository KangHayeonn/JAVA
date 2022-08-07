package baekjoon;

import java.io.*;
import java.util.*;

public class Q11266 {
	static ArrayList<Integer>[] arr;
	static int[] nodeOrder;
	static ArrayList<Integer> answer;
	static int order = 0;
	static boolean[] isChecked;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			arr[from].add(to);
			arr[to].add(from);
		}
		
		nodeOrder = new int[V+1]; // ��� �湮 ���� ����
		isChecked = new boolean[V+1]; // �̹� ���� �迭�� ���� ���������� üũ (������ �ߺ� �߰� ����)
		answer = new ArrayList<>(); // ���� ����
		for(int i=1; i<V+1; i++) { // ����׷����� �ƴ� ���� �ֱ� ����
			if(nodeOrder[i] == 0) { // ���� �湮���� ���� ���(== ���� �ȵ� ���)
				dfs(0, i); // �θ�, �ʱ� ��Ʈ ��
			}
		}
		
		System.out.println(answer.size());
		Collections.sort(answer);
		for(int i : answer) {
			System.out.print(i + " ");
		}
	}
	public static int dfs(int parent, int curr) {
		nodeOrder[curr] = ++order; // 1������ ������ �ֱ� ����
		
		int child = 0; // ��Ʈ ����� ��츸 ���
		int min = Integer.MAX_VALUE, value;
		
		// ��Ʈ ��尡 �ƴ� ���
		for(int i : arr[curr]) {
			
			// �湮���� ���� ����� ���
			if(nodeOrder[i] == 0) {
				value = dfs(curr, i); // ���� ���� curr�� �θ� �ǰ� �װͰ� ����� �ְ� �ڽ��� ��
				child += 1;
				
				// �θ����� �������� �� ũ�ų� ���� ���� �����ϸ� �ش� �θ�� ������
				if(value >= nodeOrder[curr] && !isChecked[curr] && parent != 0) {
					answer.add(curr);
					isChecked[curr] = true;
				}
				else min = Math.min(min, value);
			} else { // �湮�� ����� ��� -> ������ ����� �� �߿��� �ּҰ����� ����
				min = Math.min(min, nodeOrder[i]);
			}
		}
		
		// ��Ʈ ����� ��� -> �ڽ��� �ΰ� �̻��̸� ������ ������
		if(parent == 0 && child > 1) {
			answer.add(curr);
			isChecked[curr] = true;
		}
		
		return min;
	}
}