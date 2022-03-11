// ������ (���α׷��ӽ� LEVEL1 - Kakao)

package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FailureRate {
	public static class type implements Comparable<type> {
		private int index;
		private float value;
		
		public type(int index, float value) {
			this.index = index;
			this.value = value;
		}
		
		@Override
		public int compareTo(type t) {
			if(this.value == t.value) {
				return this.index - t.index; // index ��������
			}
			else return t.value - this.value > 0 ? 1 : -1; // �⺻ : value ��������
		}
	}
	public static void main(String args[]) {
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		// int[] stages = {4, 4, 4, 4, 4};
		System.out.println(Arrays.toString(solution(5, stages)));
	}
	public static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int index = 1;

		int[] stayed_not_clear = new int[N + 2];	// ���������� ���������� ���� Ŭ�������� ���� �÷��̾��
        int[] stayed_clear = new int[N + 1];	// ���������� ������ �÷��̾� ��
        PriorityQueue<type> pq= new PriorityQueue<>();

        for (int i = 0; i < stages.length; i++) {
            stayed_not_clear[stages[i]]++;
        }

        stayed_clear[N] = stayed_not_clear[N] + stayed_not_clear[N + 1];
        for (int i = N-1; i >= 1; i--) {
        	stayed_clear[i] = stayed_not_clear[i] + stayed_clear[i + 1];
        }

        for (int i = 1; i <= N; i++) {
            
            if(stayed_clear[i]==0){ //���������� ������ ������ ���� ��� �ش� ���������� �������� 0
                pq.add(new type(i, 0));
                continue;
            }
            
            float rate = (float) stayed_not_clear[i] / stayed_clear[i];
            pq.add(new type(i, rate));
        }
		
		index = 0;
		while(!pq.isEmpty()) {
			type p = pq.poll();
			answer[index++] = (int)(p.index);
		}
		return answer;
	}
}
