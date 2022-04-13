package Test3;

public class Main {
	public static void main(String[] args) {
		System.out.println(reverse());
		
		// 문자열
		String str = "ABCDE";
		// 문자열 reverse
		StringBuffer sb = new StringBuffer(str);
		String reverse = sb.reverse().toString();
		// 결과 출력
		System.out.println(sb); // "EDCBA"
		System.out.println(reverse); // "EDCBA"

	}
	public static String reverse() { 
		StringBuilder builder = new StringBuilder("abcdef");
		return builder.reverse().toString(); 
	// return (new StringBuffer(str).reverse().toString()); 
	}
}

// 에네첸 유치원의 달님반 9명의 아이들이 공원으로 소풍을 떠난다.
// 소풍을 떠난다는 소식에 아이들은 몹시 들떠있지만, 달님반을 맡고 있는 선생님인 당신은 큰 고민거리가 떠올랐다.
// 달님반의 아이들 중, 서로 사이가 좋지 않은 아이들은 붙어 있으면 싸움이 나기 때문이다.
// 1번부터 8번까지 차례로 번호가 매겨진 아이들의 갈등 정보가 주어졌을 때,
// 싸움이 나지 않게 일렬로 줄을 세울 수 있는 방법을 구해보자.

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