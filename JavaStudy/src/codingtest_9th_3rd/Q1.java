// ���� (���α׷��ӽ� LEVEL1)

// ver1
// (1) ������������ ����
// (2) ������� ���ÿ� ����
// (3) budget�� �Ѵ��� Ȯ���ϱ�
// (4) ���ÿ� ����� ������ result�� ���

package codingtest_9th_3rd;

import java.util.ArrayList;
import java.util.Arrays;

public class Q1 {
	static public void main(String args[]) {
		Q1 s = new Q1();
		int d[] = {1,3,2,5,4};
		System.out.println(s.solution(d, 9));
	}
	public int solution(int[] d, int budget) {
		int sum = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		Arrays.sort(d);
		for(int item : d) {
			sum += item;
			if(sum<=budget) {
				list.add(item);
			}
		}
		System.out.println(list + " " + list.size());
		return list.size();
	}
}
