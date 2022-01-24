// ĭ��� ���� (���� 4779��)

/* [ �˰��� ]
 * 
 * 1. ó�� �Է¹��� N�� ���� 3^N���� -�� �̷���� ���ڿ��� ����
 * 2. 3��� �� �� ��� ���ڿ��� �������� �ٲ۴�.
 * 3. ������ ���ڿ� 2������ 2�� ������ �ݺ��Ѵ�.
 * 4. ��� ���� ���̰� 1�� ������ 2-3�� ������ �ݺ�
 * 5. ���� ���� ���̰� 1�� �� ��� �ش� ���ڿ��� ���
 */

// BufferedWriter�� �޸��ʰ� �ذ�
package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q4779 {
	static int N;
	static char[] Line;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		//StringBuilder sb = new StringBuilder("");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while((str=br.readLine())!=null) {
			N = Integer.parseInt(str);
			int num = (int)Math.pow(3, N);
			
			Line = new char[num];
			for(int i=0; i<num; i++) {
				Line[i] = '-';
			}
			
			partition(0, num);
			
			for(int i=0; i<Line.length; i++) {
				bw.write(Line[i]);
			}
			bw.write("\n");
			
			//sb.append(result).append("\n");
		}
		bw.flush();
		//System.out.print(sb);
	}
	public static void partition(int start, int num) {
		
		if(num < 3) return;
		
		/* A B C
		 * 
		 * A : start ~ start+num/3
		 * B : start+num/3 ~ start+num/3*2
		 * C : start+num/3*2 ~ start+num
		 */
		
		partition(start, num/3);
		for(int i=start+num/3; i<start+num/3*2; i++) {
			Line[i] = ' '; // '-' -> ' '
		}
		partition(start+num/3*2, num/3);
	}
}
                            