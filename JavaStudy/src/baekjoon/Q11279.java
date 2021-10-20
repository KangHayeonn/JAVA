// 최대힙 (백준 11279번)

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
		heap.add(0); // 인덱스 1부터 사용 (노드 구현의 용이함을 위해)
	}
	
	public static void insert(ArrayList<Integer> heap, int value) {
		heap.add(value);
		int p = heap.size()-1; // p=1 이면 루트노드
		while(p > 1 && heap.get(p/2) < heap.get(p)) {
			int temp = heap.get(p/2); // 부모 노드의 값
			heap.set(p/2, value);
			heap.set(p, temp);
			p /= 2;
		}
	}
	
	public static int delete(ArrayList<Integer> heap) {
		
		if(heap.size()-1 < 1) { // 루트노드가 없을 때
			return 0;
		}
		
		int deleteItem = heap.get(1); // 항상 루트노드를 삭제 
		
		heap.set(1, heap.get(heap.size()-1)); // 마지막 노드 값을 root에 삽입
		heap.remove(heap.size()-1); // 마지막 노드 삭제
		
		int node = 1; // 초기 루트 노드 인덱스 정보
		
		while((node*2) < heap.size()) { // 자식노드가 heap.size를 넘어갈때  -> 더이상 삽입 불가
			int min = heap.get(node*2);
			int minNode = node*2;
			
			if((node*2+1) < heap.size() && min < heap.get(node*2+1)) {
				min = heap.get(node*2+1);
				minNode = node*2+1;
			}
			
			if(min < heap.get(node)) break; // 부모가 좌우 자식 노드보다 더 작으면 break
			
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
