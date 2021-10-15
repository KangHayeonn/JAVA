// Heap ����

// ArrayList �̿�
// �ε�ȣ�� ���� �������� �������� ���� ( > : �������� , < : �������� )
package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

public class Heap {
	
	public static void main(String args[]) {
		ArrayList<Integer> heap = new ArrayList<>();
		minHeap(heap);
		insert(heap, 1);
		insert(heap, 7);
		insert(heap, 3);
		insert(heap, 4);
		insert(heap, 9);
		insert(heap, 6);
		insert(heap, 5);
		
		/*
		for(Integer i : heap) {
			System.out.println("�ε��� " +heap.indexOf(i) + ": " + i);
		}*/
		
		for(int i=1; i<heap.size(); i++) {
			System.out.println(i + " : " + heap.get(i)); // ��Ʈ������ ���
		}
		
		System.out.println(delete(heap));
		
		for(int i=1; i<heap.size(); i++) {
			System.out.println(i + " : " + heap.get(i)); // ��Ʈ������ ���
		}
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
		System.out.println(p);
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