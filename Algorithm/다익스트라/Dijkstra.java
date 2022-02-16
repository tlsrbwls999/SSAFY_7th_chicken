import java.io.*;
import java.util.*;
public class Dijkstra {
    static final int INF=Integer.MAX_VALUE;
    static final int MAX_N=10;
    static int[][]graph=new int[MAX_N][MAX_N];
    static int[]Dist=new int[MAX_N];
    static int[]Prev=new int[MAX_N];
    static int N,E;   //노드의 수와 간선의 수

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());  //노드의 수 입력
        E=Integer.parseInt(st.nextToken());  //간선의 수 입력

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==j){
                    graph[i][j]=0;  //나한테 오는 방향은 없으므로 비용이 0
                }else{
                    graph[i][j]=INF;  //다른 영역으로 갈 최대 비용으로 배열을 설정해놓습니다.
                }
            }
        }


        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            int U=Integer.parseInt(st.nextToken()); //시작 노드
            int V=Integer.parseInt(st.nextToken()); //다음 노드
            int cost=Integer.parseInt(st.nextToken());
            graph[U][V]=graph[V][U]=cost; //방향이 없는 그래프! 즉 양방향 그래프
//            graph[U][V]=graph[V][U]=cost; //방향이 있는 그래프면  출발 노드 번호-> 도착 노드 번호에만 비용을 삽입 합니다.
        }
        dijkstra(0);
        for(int i=0;i<N;i++){
            System.out.println(Dist[i]+" ");
        }

        /**
         * 경로가 필요한 경우 출력
         *
         */
//        dijkstra1(0);
//        int curr=5;
//        while(curr!=-1){
//            System.out.print(curr+" <");
//            curr=Prev[curr];
//        }

//        for(int i=0;i<N;i++){
//            dijkstra2(0,i);
//        }

    }
    public static void dijkstra(int start){  //경록가 필요없는 겨우
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->a[0]-b[0]);   //첫번쨰 그 정점까지가는 비용, 두번째는 노드 번호를 전달
        boolean[] visited=new boolean[MAX_N];
        for(int i=0;i<N;i++){
            Dist[i]=INF;
        }
        Dist[start]=0;
        pq.add(new int[]{0,start});

        while(!pq.isEmpty()){
            int[]curr=pq.poll();
            int u=curr[1]; //노드 번호
            if(visited[u])continue;  //우선순위로 들어간것들 나오면서 스킵

            visited[u]=true;
            for(int v=0;v<N;v++){
                if(Dist[v]>Dist[u]+graph[u][v]){
                    Dist[v]=Dist[u]+graph[u][v];
                    pq.add(new int[]{Dist[v],v});
                }
            }
        }
    }


    public static void dijkstra1(int start){  //경로가 필요한 경우
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->a[0]-b[0]);   //첫번쨰 그 정점까지가는 비용, 두번째는 노드 번호를 전달
        boolean[] visited=new boolean[MAX_N];
        for(int i=0;i<N;i++){
            Prev[i]=-1; Dist[i]=INF;
        }
        Dist[start]=0;
        pq.add(new int[]{0,start});

        while(!pq.isEmpty()){
            int[]curr=pq.poll();
            int u=curr[1];
            if(visited[u])continue;  //우선순위로 들어간것들 나오면서 스킵

            visited[u]=true;
            for(int v=0;v<N;v++){
                if(Dist[v]>Dist[u]+graph[u][v]){
                    Prev[v]=u;
                    Dist[v]=Dist[u]+graph[u][v];
                    pq.add(new int[]{Dist[v],v});
                }
            }
        }
    }


    public static int dijkstra2(int start,int last){  //경로가 필요한 경우
        PriorityQueue<int[]>pq=new PriorityQueue<>((a,b)->a[0]-b[0]);   //첫번쨰 그 정점까지가는 비용, 두번째는 노드 번호를 전달
        boolean[] visited=new boolean[MAX_N];
        for(int i=0;i<N;i++){
            Prev[i]=-1; Dist[i]=INF;
        }
        Dist[start]=0;
        pq.add(new int[]{0,start});

        while(!pq.isEmpty()){
            int[]curr=pq.poll();
            int u=curr[1];
            if(u==last)return curr[0];
            if(visited[u])continue;  //우선순위로 들어간것들 나오면서 스킵

            visited[u]=true;
            for(int v=0;v<N;v++){
                if(Dist[v]>Dist[u]+graph[u][v]){
                    Prev[v]=u;
                    Dist[v]=Dist[u]+graph[u][v];
                    pq.add(new int[]{Dist[v],v});
                }
            }
        }
        return INF;
    }

}
