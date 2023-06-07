package ����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9328��_���� {
	
	static int test, row, col, ans;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int[][] map;
	static boolean[][] visited;
	
	static Queue<int[]>  queue;
	static HashMap<Integer, Boolean> hash;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		test = Integer.parseInt(br.readLine());
		for(int t = 0; t < test; t++) 
		{
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			ans = 0;
			hash = new HashMap<Integer, Boolean>();
			queue = new LinkedList<int[]>();
			map = new int[row+2][col+2];
			visited = new boolean[row+2][col+2];
			for(int c = 0; c < col + 2; c++) {
				map[0][c] = map[row+1][c] = -2;
			}
			for(int r = 0; r < row + 2; r++) {
				map[r][0] = map[r][col+1] = -2;
			}
			
			for(int r = 1; r <= row; r++) {
				String str = br.readLine();
				for(int c = 1; c <= col; c++) {
					map[r][c] = str.charAt(c-1) - '0';						
				}
			}
			String str = br.readLine();
			for(int i = 0; i < str.length(); i++) {
				hash.put(str.charAt(i) - '0', true);
			}
			bfs();
			while(bfs2()) {
				
			}
			System.out.println(ans);			
		}
	}

	private static boolean bfs2() {
		Queue<int[]> q = new LinkedList<int[]>();
		int size = queue.size();
		boolean isChange = false;
		for(int s = 0; s < size; s++) {
			int[] temp = queue.poll();
			if(hash.containsKey(map[temp[0]][temp[1]]+32)) {
				isChange = true;
				map[temp[0]][temp[1]] = -2;
				q.add(new int[] {temp[0], temp[1]});
				visited[temp[0]][temp[1]] = false;
			} else {
				queue.add(temp);
			}
		}
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				if(nr < 0 || nc < 0 || nr >= row + 2 || nc >= col + 2 || visited[nr][nc] || map[nr][nc] == -6) continue;
				visited[nr][nc] = true;
				if(map[nr][nc] == -2) {
					q.add(new int[] {nr, nc});
				} else if(map[nr][nc] == -12) {
					map[nr][nc] = -2;
					q.add(new int[] {nr, nc});
					ans++;
				} else if(map[nr][nc] >= 17 && map[nr][nc] <= 42) {
					if(hash.containsKey(map[nr][nc]+32)) {
						map[nr][nc] = -2;
						q.add(new int[] {nr, nc});
					} else {
						queue.add(new int[] {nr, nc});
					}
				} else if(map[nr][nc] >= 49 && map[nr][nc] <= 74) {
					hash.put(map[nr][nc], true);
					map[nr][nc] = -2;
					q.add(new int[] {nr, nc});
				}
			}
		}
		
		return isChange;
		
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				if(nr < 0 || nc < 0 || nr >= row + 2 || nc >= col + 2 || visited[nr][nc] || map[nr][nc] == -6) continue;
				visited[nr][nc] = true;
				if(map[nr][nc] == -2) {
					q.add(new int[] {nr, nc});
				} else if(map[nr][nc] == -12) {
					map[nr][nc] = -2;
					q.add(new int[] {nr, nc});
					ans++;
				} else if(map[nr][nc] >= 17 && map[nr][nc] <= 42) {
					if(hash.containsKey(map[nr][nc]+32)) {
						map[nr][nc] = -2;
						q.add(new int[] {nr, nc});
					} else {
						queue.add(new int[] {nr, nc});
					}
				} else if(map[nr][nc] >= 49 && map[nr][nc] <= 74) {
					hash.put(map[nr][nc], true);
					map[nr][nc] = -2;
					q.add(new int[] {nr, nc});
				}
			}
		}
	}
}
