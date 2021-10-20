// ī�� (���� 1835��)

// ��Ʈ�� �������� �˰��� �ۼ�
// ���� �̿�

// [�˰���] 
// å�� ���� �÷����°� deque �Ǿտ� ���� �߰�
// �ش� ���� ��ŭ �ǵ� ���ڸ� �Ǿ����� �̵�
// 1���� �� ������ �ݺ�

package baekjoon;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Q1835 {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		Deque<Integer> deque = new LinkedList<>();
		
		for(int i=num; i>0; i--) {
			if(deque.isEmpty()) {
				deque.add(i);
				continue;
			}
			
			deque.addFirst(i);
			int count = i;
			while(count > 0) {
				deque.push(deque.pollLast());
				count--;
			}
		}
		while(deque.size()!=0) {
			if(deque.size()==1) System.out.print(deque.poll());
			else System.out.print(deque.poll() +" ");
		}
		input.close();
	}
}
