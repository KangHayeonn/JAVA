// �ִ��� (���� 11279��)

package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class Q11279 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		ArrayList<Integer> heap = new ArrayList<>();
		minHeap(heap);
				
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<count; i++) {
			int input = sc.nextInt();
			if(input==0) {
				int output = delete(heap);
				sb.append(output).append("\n");
			}
			else insert(heap, input);
		}
		System.out.println(sb);
		sc.close();
	}
	
	public static void minHeap(ArrayList<Integer> heap) {
		heap.add(0); // �ε��� 1���� ��� (��� ������ �������� ����)
	}
	
	public static void insert(ArrayList<Integer> heap, int value) {
		heap.add(value);
		int p = heap.size()-1; // p=1 �̸� ��Ʈ���
		while(p > 1 && heap.get(p/2) < heap.get(p)) {
			int temp = heap.get(p/2); // �θ� ����� ��
			heap.set(p/2, value);
			heap.set(p, temp);
			p /= 2;
		}
	}
	
	public static int delete(ArrayList<Integer> heap) {
		
		if(heap.size()-1 < 1) { // ��Ʈ��尡 ���� ��
			return 0;
		}
		
		int deleteItem = heap.get(1); // �׻� ��Ʈ��带 ���� 
		
		heap.set(1, heap.get(heap.size()-1)); // ������ ��� ���� root�� ����
		heap.remove(heap.size()-1); // ������ ��� ����
		
		int node = 1; // �ʱ� ��Ʈ ��� �ε��� ����
		
		while((node*2) < heap.size()) { // �ڽĳ�尡 heap.size�� �Ѿ��  -> ���̻� ���� �Ұ�
			int min = heap.get(node*2);
			int minNode = node*2;
			
			if((node*2+1) < heap.size() && min < heap.get(node*2+1)) {
				min = heap.get(node*2+1);
				minNode = node*2+1;
			}
			
			if(min < heap.get(node)) break; // �θ� �¿� �ڽ� ��庸�� �� ������ break
			
			swap(heap, node, minNode);
			
			node = minNode;
		}
		
		return deleteItem;
	}
	
	public static void swap(ArrayList<Integer> heap, int a, int b) {
		int temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
}
