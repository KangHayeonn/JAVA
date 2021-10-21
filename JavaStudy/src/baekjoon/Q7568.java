// ��ġ (���� 7568��)

// 1. for���� �ΰ� �̿��� �ڱ� �ڽ��� ������ ��� ����� ���Ѵ�.
// 2. �ڱ� �ڽź��� Ű�� �����԰� ��ΰ� �� ���� ����� ī��Ʈ �Ѵ�.
// 3. �ش� ī��Ʈ+1 �� ���� �� ����� �ε��� ��ġ�� ��Ī ���� �迭�� �־��ش�.
// 4. �ش� �迭�� ���鹮�ڿ� �Բ� ���

package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q7568 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int arr[][] = new int[count][2];
		
		for(int i=0; i<count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.valueOf(st.nextToken());
			arr[i][1] = Integer.valueOf(st.nextToken());
		}
		
		int answer[] = new int[count];
		
		for(int i=0; i<count; i++) {
			int idx = 0;
			for(int j=0; j<count; j++) {
				if(arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) idx++;
			}
			answer[i] = idx+1;
		}
		
		for(int i=0; i < count; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}
