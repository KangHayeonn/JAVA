// �ܼ�ī�޶� (���α׷��ӽ� LEVEL3)

/* [ �˰��� ]
 * 
 * 1. start, end Ÿ���� ���� ArrayList�� ����
 * 2. start�� �������� ������������ ���� { -20 -16 } { -18 -14} {-15  -4}
 * 3. ���� end�� ���� start�� ���� ���� end�� ũ�ų� ������ ī�޶� ��ġ ���� (ī�޶� ��ġ : ���� end)
 * 4. ���� end�� ���� start�� ������ �� ���� end�� �� ������ ���� end ������ ī�޶� ��ġ ���� �� count++ (ī�޶� ��ġ : ���� end)
 * 5. ������ count���� +1 �� �� ���
 *  
 */

// ver1 : �ܼ� �ݺ��� ����
/*
package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SpeedCamera {
	public static class type {
		private int start;
		private int end;
		public type(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String args[]) {
		int[][] routes = {{-20,-15},{-14,-5},{-18,-13},{-5,-3}};
		System.out.print(solution(routes));
	}
	public static int solution(int[][] routes) {
		int answer =0;
		ArrayList<type> list = new ArrayList<>();
		for(int i=0; i<routes.length; i++) {
			list.add(new type(routes[i][0], routes[i][1]));
		}
		Collections.sort(list, new Comparator<type>() {
			@Override
			public int compare(type s1, type s2) {
				if(s1.start == s2.start) return s1.end - s2.end;
				else return s1.start - s2.start; // start ���� �������� ��������
			}
		});
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).start + " : " + list.get(i).end);
		}
		
		int Camera = list.get(0).end; // Camera ��ġ
		
		for(int i=0; i<list.size(); i++) {
			if(Camera >= list.get(i).start) continue;
			else {
				Camera = list.get(i).end;
				answer++;
			}
		}
		return answer+1;
	}
}*/

/* [ �˰��� ] - ����
 * 
 * 1. ���� ������ ������ ������ �Ѵ�
 * 2. ù��°�� ���������� ���� ��ġ�� ���Ѵ�.
 * 3. �ش� ��ġ���� ������ ������ �����ų� ���� ���� ��� ���� �����ش�
 * 4. 2-3�� ��� ī�޶� �Ѵ븸 ��ġ�ϸ� �ǹǷ� answer++ ���ش�.
 * 5. �ٽ� ������ �迭���� ù��°�� ���������� ���� ��ġ�� ���Ѵ�. (2-5�� �ݺ�)
 * 6. �׸��� �迭�� ���̻� ���� ���� ������ �׶��� answer�� ��½����ش�.
 *  
 */

// ver2 : �˰��� ����
// ����
package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SpeedCamera {
	public static class type {
		private int start;
		private int end;
		public type(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String args[]) {
		int[][] routes = {{-20,-15},{-14,-5},{-18,-13},{-5,-3}};
		System.out.print(solution(routes));
	}
	public static int solution(int[][] routes) {
		int answer =0;
		ArrayList<type> list = new ArrayList<>();
		for(int i=0; i<routes.length; i++) {
			list.add(new type(routes[i][0], routes[i][1]));
		}
		Collections.sort(list, new Comparator<type>() {
			@Override
			public int compare(type s1, type s2) {
				return s1.end - s2.end; // end ���� �������� ��������
			}
		});
		
		while(!list.isEmpty()) {
			int Camera = list.get(0).end; // Camera ��ġ
			
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).start <= Camera) {
					list.remove(i);
					i--;
				}
			}
			
			answer++;
		}
		
		return answer;
	}
}
