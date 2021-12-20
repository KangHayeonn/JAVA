package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Shark{
	int x;
	int y;
	public Shark(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}

public class Q17086 {
	static int n,m;
	static int xpos[]= {0,0,1,-1,1,1,-1,-1};
	static int ypos[]= {1,-1,0,0,1,-1,-1,1};
	static int[][] map;
	static int[][] dis;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new int[n][m];
		dis=new int[n][m];
		Queue<Shark> q=new LinkedList<Shark>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j]=sc.nextInt();
				if(map[i][j]==1) {
					q.add(new Shark(j,i));//x,y 순서 주의~!
				}
			}
		}
		int answer=Integer.MIN_VALUE;
	
		while(q.size()!=0) {
			Shark cur=q.poll();
			int x=cur.x;
			int y=cur.y;
			for (int j = 0; j < 8; j++) {
				int yy=y+ypos[j];
				int xx=x+xpos[j];
				if(xx<0 || yy<0 || xx>=m || yy>=n)continue;
				if(dis[yy][xx]!=0 || map[yy][xx]==1)continue;
				dis[yy][xx]=dis[y][x]+1;
				if(dis[yy][xx]>answer)answer=dis[yy][xx];
				q.add(new Shark(xx,yy));
			}
		}
		System.out.println(answer);	
		
	}
}
