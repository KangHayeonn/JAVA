// ATM (백준 11399번)

// 최소힙 이용
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11399 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> heap = new ArrayList<>();
		minHeap(heap);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			insert(heap, Integer.parseInt(st.nextToken()));
		}
		
		int ele = 0;
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			int delItem = delete(heap);
			ele += delItem;
			sum += ele;
			//System.out.println(i + " : " + " -> " + ele + " " + sum); // 루트노드부터 출력
		}
		System.out.print(sum);
	}
	
	public static void minHeap(ArrayList<Integer> heap) {
		heap.add(0); // 인덱스 1부터 사용 (노드 구현의 용이함을 위해)
	}
	
	public static void insert(ArrayList<Integer> heap, int value) {
		heap.add(value);
		int p = heap.size()-1; // p=1 이면 루트노드
		while(p > 1 && heap.get(p/2) > heap.get(p)) {
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
			
			if((node*2+1) < heap.size() && min > heap.get(node*2+1)) {
				min = heap.get(node*2+1);
				minNode = node*2+1;
			}
			
			if(min > heap.get(node)) break; // 부모가 좌우 자식 노드보다 더 작으면 break
			
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
