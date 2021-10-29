// 절댓값 힙 (백준 11286번)

package baekjoon;

// ver1 : 실패
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q11286 {
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		ArrayList<Integer> heap = new ArrayList<Integer>();
		minHeap(heap);
		
		insert(heap, 1);
		insert(heap, -1);
		for(int i=1; i<heap.size(); i++) {
			System.out.println(i + " : " + heap.get(i)); // 루트노드부터 출력
		}
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		for(int i=1; i<heap.size(); i++) {
			System.out.println(i + " : " + heap.get(i)); // 루트노드부터 출력
		}
		insert(heap, -4);
		insert(heap, 3);
		insert(heap, -1);
		insert(heap, -3);
		insert(heap, 2);
		insert(heap, -2);
		for(int i=1; i<heap.size(); i++) {
			System.out.println(i + " : " + heap.get(i)); // 루트노드부터 출력
		}
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		insert(heap, -1);
		insert(heap, 1);
		insert(heap, -1);
		for(int i=1; i<heap.size(); i++) {
			System.out.println(i + " : " + heap.get(i)); // 루트노드부터 출력
		}
		System.out.println(delete(heap));
		System.out.println(delete(heap));
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(isEmpty(heap)) sb.append(0).append('\n');
				else sb.append(delete(heap)).append('\n');
			}
			else {
				insert(heap, num);
			}
		}
		
		System.out.print(sb);
	}
	
	public static void minHeap(ArrayList<Integer> heap) {
		heap.add(0); // 인덱스 1부터 사용 (노드 구현의 용이함을 위해)
	}
	
	public static boolean isEmpty(ArrayList<Integer> heap) {
		if(heap.size()-1 > 0) return false;
		else return true;
	}
	
	public static void insert(ArrayList<Integer> heap, int value) {
		heap.add(value);
		int p = heap.size()-1; // p=1 이면 루트노드
		while(p > 1 && Math.abs(heap.get(p/2)) >= Math.abs(heap.get(p))) {
			//System.out.println("부모: " + Math.abs(heap.get(p/2)) + " 자식: "+ Math.abs(heap.get(p)));
			if(Math.abs(heap.get(p/2)) == Math.abs(heap.get(p))) {
				if(heap.get(p/2) > heap.get(p)) {
					int temp = heap.get(p/2); // 부모 노드의 값
					heap.set(p/2, value);
					heap.set(p, temp);
					p /= 2;
				}
				break;
			}
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
			
			//if((node*2+1) < heap.size() && Math.abs(min) >= Math.abs(heap.get(node*2+1))) {
			//	if (Math.abs(min) == Math.abs(heap.get(node*2+1))) {
			//		if(min > heap.get(node*2+1)) {
			//			min = heap.get(node*2+1);
			//			minNode = node*2+1;
			//		}
			//	}
			//	else {
			//		min = heap.get(node*2+1);
			//		minNode = node*2+1;
			//	}
			//}
			if((node*2+1) < heap.size()) {
				if(Math.abs(min) == Math.abs(heap.get(node*2+1))) {
					if(min > heap.get(node*2+1)) {
						min = heap.get(node*2+1);
						minNode = node*2+1;
					}
				}
				else if(Math.abs(min) > Math.abs(heap.get(node*2+1))) {
					min = heap.get(node*2+1);
					minNode = node*2+1;
				}
			}
			
			if(Math.abs(min) > Math.abs(heap.get(node))) break; // 부모가 좌우 자식 노드보다 더 작으면 break
			else if(Math.abs(min) == Math.abs(heap.get(node))) {
				if(min > heap.get(node)) break;
				else {
					swap(heap, node, minNode);
					
					node = minNode;
				}
			}
			else {
				swap(heap, node, minNode);
				
				node = minNode;
			}
		}
		
		return deleteItem;
	}
	
	public static void swap(ArrayList<Integer> heap, int a, int b) {
		int temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
}
*/
// ver2 : insert부분의 continue 부분이 매우 중요.
// 성공
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q11286 {
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		ArrayList<Integer> heap = new ArrayList<>();
		minHeap(heap);
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(isEmpty(heap)) sb.append(0).append('\n');
				else sb.append(delete(heap)).append('\n');
			}
			else {
				insert(heap, num);
			}
		}
		
		System.out.print(sb);
	}
	
	public static void minHeap(ArrayList<Integer> heap) {
		heap.add(0); // 인덱스 1부터 사용 (노드 구현의 용이함을 위해)
	}
	
	public static boolean isEmpty(ArrayList<Integer> heap) {
		if(heap.size()-1 > 0) return false;
		else return true;
	}
	
	public static void insert(ArrayList<Integer> heap, int value) {
		heap.add(value);
		int p = heap.size()-1; // p=1 이면 루트노드
		while(p > 1 && Math.abs(heap.get(p/2)) >= Math.abs(heap.get(p))) {
			if(Math.abs(heap.get(p/2)) == Math.abs(heap.get(p))) {
				if(heap.get(p/2) > heap.get(p)) {
					int temp = heap.get(p/2); // 부모 노드의 값
					heap.set(p/2, value);
					heap.set(p, temp);
					p /= 2;
                    continue; // 이부분 때문에 정답처리?
				}
				break;
			}
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
			
			if((node*2+1) < heap.size()) {
				if(Math.abs(min) == Math.abs(heap.get(node*2+1))) {
					if(min > heap.get(node*2+1)) {
						min = heap.get(node*2+1);
						minNode = node*2+1;
					}
				}
				else if(Math.abs(min) > Math.abs(heap.get(node*2+1))) {
					min = heap.get(node*2+1);
					minNode = node*2+1;
				}
			}
			
			if(Math.abs(min) > Math.abs(heap.get(node))) break; // 부모가 좌우 자식 노드보다 더 작으면 break
			else if(Math.abs(min) == Math.abs(heap.get(node))) {
				if(min > heap.get(node)) break;
				else {
					swap(heap, node, minNode);
					
					node = minNode;
				}
			}
			else {
				swap(heap, node, minNode);
				
				node = minNode;
			}
		}
		
		return deleteItem;
	}
	
	public static void swap(ArrayList<Integer> heap, int a, int b) {
		int temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
}

