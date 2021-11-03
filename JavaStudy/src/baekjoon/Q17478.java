// ����Լ��� ������? (���� 17478��)

package baekjoon;

import java.util.Scanner;

public class Q17478 {
	public static void main(String args[]) {
		Scanner c = new Scanner(System.in);
		int count = c.nextInt();
		System.out.println("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");
		recursion("", count);
		c.close();
	}
	
	public static void recursion(String str, int count) {
		String s = "____";
		if(count==0) {
			System.out.println(str+ "\"����Լ��� ������?\"");
			System.out.println(str+ "\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
			System.out.println(str+ "��� �亯�Ͽ���.");
			return;
		}
		System.out.println(str+ "\"����Լ��� ������?\"");
		System.out.println(str+ "\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
		System.out.println(str+ "���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
		System.out.println(str+ "���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");
		recursion(str+s, count-1);
		System.out.println(str+ "��� �亯�Ͽ���.");
	}
}
