package Test3;

public class Main {
	public static void main(String[] args) {
		System.out.println(reverse());
		
		// ���ڿ�
		String str = "ABCDE";
		// ���ڿ� reverse
		StringBuffer sb = new StringBuffer(str);
		String reverse = sb.reverse().toString();
		// ��� ���
		System.out.println(sb); // "EDCBA"
		System.out.println(reverse); // "EDCBA"

	}
	public static String reverse() { 
		StringBuilder builder = new StringBuilder("abcdef");
		return builder.reverse().toString(); 
	// return (new StringBuffer(str).reverse().toString()); 
	}
}

// ����þ ��ġ���� �޴Թ� 9���� ���̵��� �������� ��ǳ�� ������.
// ��ǳ�� �����ٴ� �ҽĿ� ���̵��� ���� �鶰������, �޴Թ��� �ð� �ִ� �������� ����� ū ��ΰŸ��� ���ö���.
// �޴Թ��� ���̵� ��, ���� ���̰� ���� ���� ���̵��� �پ� ������ �ο��� ���� �����̴�.
// 1������ 8������ ���ʷ� ��ȣ�� �Ű��� ���̵��� ���� ������ �־����� ��,
// �ο��� ���� �ʰ� �Ϸķ� ���� ���� �� �ִ� ����� ���غ���.

// 1 <= N <= 10
// 1 <= a,b <= 8

/*
4
5 3
4 3
4 7
3 6

10560

4
3 6
5 7
4 1
8 7

13152
*/