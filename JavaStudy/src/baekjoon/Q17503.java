// ���� ���� (���� 17503��)

/* [ �˰��� ]
 * 
 * 1. �Էµ� ���� ���� ������ ���� ���� (���� ������ ���� ��� ������ ��ȣ���� ū ������ ����)
 * 2. �� ���ĵ� ���� �ϳ��� �ּ����� ������ (�̶�, ������ ��ȣ���� ���� sum�� ����)
 * 3. ���� �ּ����� ����� ���� ������ �Ⱓ(N)�� ������ ��� sum�� ä�����ϴ� ��ȣ��(M)�� ���� �� (�������� ä�� ��ȣ���� ���������� max ������ ��������)
 * 4. sum<M�� ��� �ּ������� �ϳ��� �������ְ� �׶��� ���� sum���� ����
 * 5. ���ο� ���� �ٽ� �ּ����� �������ְ� �׶��� ��ȣ���� sum�� ������ (max�� ����)
 * 6. 3-5�� �ݺ��ϸ� sum>=M�� ��� �׶��� max���� return
 * 7. ���� ��� �迭 Ž���� ���� ������ return ���� ������ �׶��� -1�� return
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
		private int prefer; // ������ ��ȣ��
		private int degree; // ���� ����
		
		public type (int prefer, int degree) {
			this.prefer = prefer;
			this.degree = degree;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ���� ������ �Ⱓ
		int M = Integer.parseInt(st.nextToken()); // ä���� �ϴ� ��ȣ���� ��
		int K = Integer.parseInt(st.nextToken()); // ���� ���� ����
		
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
				if(a.degree==b.degree) {      // ������ ���� �� -> ������ ��ȣ�� ������ ��������!
					return b.prefer-a.prefer;
				}
				else {
					return a.degree-b.degree; // defalut�� ���������� ���� �������� 
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
		heap.add(new type(0, 0)); // �ε��� 1���� ��� (��� ������ �������� ����)
	}
	
	public static void insert(ArrayList<type> heap, type value) {
		heap.add(new type(value.prefer, value.degree));
		int p = heap.size()-1; // p=1 �̸� ��Ʈ���
		while(p > 1 && heap.get(p/2).prefer > heap.get(p).prefer) {
			type temp = heap.get(p/2); // �θ� ����� ��
			heap.set(p/2, value);
			heap.set(p, temp);
			p /= 2;
		}
	}

	public static type delete(ArrayList<type> heap) {
		
		if(heap.size()-1 < 1) { // ��Ʈ��尡 ���� ��
			return null;
		}
		
		type deleteItem = heap.get(1); // �׻� ��Ʈ��带 ���� 
		
		heap.set(1, heap.get(heap.size()-1)); // ������ ��� ���� root�� ����
		heap.remove(heap.size()-1); // ������ ��� ����
		
		int node = 1; // �ʱ� ��Ʈ ��� �ε��� ����
		
		while((node*2) < heap.size()) { // �ڽĳ�尡 heap.size�� �Ѿ��  -> ���̻� ���� �Ұ�
			type min = heap.get(node*2);
			int minNode = node*2;
			
			if((node*2+1) < heap.size() && min.prefer > heap.get(node*2+1).prefer) {
				min = heap.get(node*2+1);
				minNode = node*2+1;
			}
			
			if(min.prefer > heap.get(node).prefer) break; // �θ� �¿� �ڽ� ��庸�� �� ������ break
			
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
