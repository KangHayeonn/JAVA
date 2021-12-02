// 맥주 축제 (백준 17503번)

/* [ 알고리즘 ]
 * 
 * 1. 입력된 값을 도수 레벨에 따라 정렬 (도수 레벨이 같을 경우 맥주의 선호도가 큰 순으로 정렬)
 * 2. 위 정렬된 값을 하나씩 최소힙에 저장함 (이때, 맥주의 선호도는 따로 sum에 저장)
 * 3. 만약 최소힙의 사이즈가 축제 열리는 기간(N)과 같아질 경우 sum과 채워야하는 선호도(M)의 합을 비교 (마지막에 채운 선호도의 도수레벨을 max 변수에 저장해줌)
 * 4. sum<M일 경우 최소힙에서 하나를 삭제해주고 그때의 값을 sum에서 삭제
 * 5. 새로운 값을 다시 최소힙에 저장해주고 그때의 선호도를 sum에 더해줌 (max도 변함)
 * 6. 3-5를 반복하며 sum>=M일 경우 그때의 max값을 return
 * 7. 만약 모든 배열 탐색이 끝날 때가지 return 값이 없으면 그때는 -1을 return
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q17503 {
	public static class type {
		private int prefer; // 맥주의 선호도
		private int degree; // 도수 레벨
		
		public type (int prefer, int degree) {
			this.prefer = prefer;
			this.degree = degree;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 축제 열리는 기간
		int M = Integer.parseInt(st.nextToken()); // 채워야 하는 선호도의 합
		int K = Integer.parseInt(st.nextToken()); // 맥주 종류 개수
		
		type[] beers = new type[K];
		
		ArrayList<type> heap = new ArrayList<>();
		minHeap(heap);
		
		for(int i=0; i<K; i++) {
			st= new StringTokenizer(br.readLine());
			beers[i] = new type(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(beers, new Comparator<type>() {	
			@Override
			public int compare(type a, type b) {
				if(a.degree==b.degree) {      // 도수가 같을 때 -> 맥주의 선호도 순으로 내림차순!
					return b.prefer-a.prefer;
				}
				else {
					return a.degree-b.degree; // defalut는 도수레벨에 따라 오름차순 
				}
			}
		});
		
		int sum = 0;
		int max = 0;
		int i=0;
		
		for(i=0; i<beers.length; i++) {
			//System.out.println(beers[i].prefer +" " + beers[i].degree);
			insert(heap, beers[i]);
			sum += beers[i].prefer;
			max = Math.max(max, beers[i].degree);
			//System.out.println(heap.size() + " : " + sum + " " + max);
			if(heap.size()==N+1) {
				if(sum<M) {
					sum-=delete(heap).prefer;
				} else {
					System.out.print(max);
					return;
				}
			}
		}

		if(i==beers.length) {
			System.out.print(-1);
			return;
		}
	}
	
	public static void minHeap(ArrayList<type> heap) {
		heap.add(new type(0, 0)); // 인덱스 1부터 사용 (노드 구현의 용이함을 위해)
	}
	
	public static void insert(ArrayList<type> heap, type value) {
		heap.add(new type(value.prefer, value.degree));
		int p = heap.size()-1; // p=1 이면 루트노드
		while(p > 1 && heap.get(p/2).prefer > heap.get(p).prefer) {
			type temp = heap.get(p/2); // 부모 노드의 값
			heap.set(p/2, value);
			heap.set(p, temp);
			p /= 2;
		}
	}

	public static type delete(ArrayList<type> heap) {
		
		if(heap.size()-1 < 1) { // 루트노드가 없을 때
			return null;
		}
		
		type deleteItem = heap.get(1); // 항상 루트노드를 삭제 
		
		heap.set(1, heap.get(heap.size()-1)); // 마지막 노드 값을 root에 삽입
		heap.remove(heap.size()-1); // 마지막 노드 삭제
		
		int node = 1; // 초기 루트 노드 인덱스 정보
		
		while((node*2) < heap.size()) { // 자식노드가 heap.size를 넘어갈때  -> 더이상 삽입 불가
			type min = heap.get(node*2);
			int minNode = node*2;
			
			if((node*2+1) < heap.size() && min.prefer > heap.get(node*2+1).prefer) {
				min = heap.get(node*2+1);
				minNode = node*2+1;
			}
			
			if(min.prefer > heap.get(node).prefer) break; // 부모가 좌우 자식 노드보다 더 작으면 break
			
			swap(heap, node, minNode);
			
			node = minNode;
		}
		
		return deleteItem;
	}
	
	public static void swap(ArrayList<type> heap, int a, int b) {
		type temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
}
