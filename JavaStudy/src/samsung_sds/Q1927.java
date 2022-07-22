// 최소 힙(자료구조 - 실버2)
package samsung_sds;

import java.io.*;
import java.util.*;

public class Q1927 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> heap = new ArrayList<>();
		minHeap(heap);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(isEmpty(heap)) sb.append(0).append("\n");
				else sb.append(delete(heap)).append("\n");
			} else {
				insert(heap, num);
			}
		}
		
		System.out.println(sb);
	}
	
	public static void minHeap(ArrayList<Integer> heap) {
		heap.add(0); // 인덱스 1부터 사용 (노드 구현의 용이함을 위해)
	}
	
	public static boolean isEmpty(ArrayList<Integer> heap) {
		if(heap.size()-1 > 0) return false;
		else return true;
	}
	
	public static void insert(ArrayList<Integer> heap, int value) {
		heap.add(value); // 제일 뒤쪽에 값 추가
		int p = heap.size()-1; // p=1 이면 루트노드
		while(p > 1 && heap.get(p/2) > heap.get(p)) {
			int temp = heap.get(p/2);
			heap.set(p/2, value);
			heap.set(p, temp);
			p /= 2;
		}
	}
	
	public static int delete(ArrayList<Integer> heap) {
		
		if(heap.size()-1 < 1) { // 루트 노드가 없을 때
			return 0;
		}
		
		int deleteItem = heap.get(1); // 항상 루트노드를 삭제
		
		heap.set(1,  heap.get(heap.size()-1)); // 마지막 노드 값을 root에 삽입
		heap.remove(heap.size()-1); // 마지막 노드 삭제
		
		int node = 1; // 초기 루트 노드 인덱스 정보
		
		while((node*2) < heap.size()) { // 자식 노드가 heap 사이즈를 넘어가면 더이상 삽입 불가
			int min = heap.get(node*2);
			int minNode = node*2;
			
			if((node*2+1) < heap.size() && min > heap.get(node*2+1)) {
				min = heap.get(node*2+1);
				minNode = node*2+1;
			}
			
			if(min > heap.get(node)) break; // 부모가 좌우 자식 노드보다 더 작음
			
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
