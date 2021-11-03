// �ּ� �� (���� Q1927��)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q1927 {
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		ArrayList<Integer> heap = new ArrayList<>();
		minHeap(heap);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(isEmpty(heap)) sb.append(delete(heap)).append('\n');
				else sb.append(0).append('\n');
			}
			else {
				insert(heap, num);
			}
		}
		
		System.out.print(sb);
	}
	
	public static void minHeap(ArrayList<Integer> heap) {
		heap.add(0); // �ε��� 1���� ��� (��� ������ �������� ����)
	}
	
	public static boolean isEmpty(ArrayList<Integer> heap) {
		if(heap.size()-1 > 0) return true;
		else return false;
	}
	
	public static void insert(ArrayList<Integer> heap, int value) {
		heap.add(value);
		int p = heap.size()-1; // p=1 �̸� ��Ʈ���
		while(p > 1 && heap.get(p/2) > heap.get(p)) {
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
			
			if((node*2+1) < heap.size() && min > heap.get(node*2+1)) {
				min = heap.get(node*2+1);
				minNode = node*2+1;
			}
			
			if(min > heap.get(node)) break; // �θ� �¿� �ڽ� ��庸�� �� ������ break
			
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