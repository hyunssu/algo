package 백준;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946번_벽_부수고_이동하기_4 {
	
	static int N, M, val;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int[][] map;
	static int[][] ans;
	
	static HashMap<Integer, Integer> hashMap;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ans = new int[N][M];
		hashMap = new HashMap<Integer, Integer>();
		hashMap.put(1, 0);
		val = 2;
		for(int r = 0; r < N; r++) {
			String str = br.readLine();
			for(int c = 0; c < M; c++ ) {
				int value = str.charAt(c) - '0';
				map[r][c] = value;
				ans[r][c] = value;
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 0) {
					map[r][c] = val;
					bfs(r, c);
					val++;
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(ans[r][c] == 1) ans[r][c] = (countArea(r, c) + 1)%10;
			}
			
		}
		StringBuilder  sb = new StringBuilder();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sb.append(ans[r][c]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	private static int countArea(int r, int c) {
		int returnValue = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
			set.add(map[nr][nc]);
		}
		Iterator<Integer> iter  = set.iterator();
		while(iter.hasNext()) {
			returnValue += hashMap.get(iter.next());
		}
		return returnValue;
	}

	public static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {r, c});
		int cnt = 0;
		while(!q.isEmpty()) {
			cnt++;
			int[] temp = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];			
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 0) continue;
				q.add(new int[] {nr, nc});
				map[nr][nc] = val;
			}
		}
		
		hashMap.put(val, cnt);
	}
	
}
