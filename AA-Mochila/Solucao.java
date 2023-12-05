import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solucao{
    public int max(int x, int y){
      if (x > y)
        return x;
      else
        return y;
    }

    public boolean cmp(Objeto a, Objeto b) {
      double r1 = (double) a.getValor() / a.getPeso();
      double r2 = (double) b.getValor() / b.getPeso();
      return r1 > r2;
    }

    public int bound(Node u, int n, int k, Objeto coisas[]) {
      if (u.getPeso() >= k)
          return 0;

      int p_b = u.getProfit();

      int j = u.getLevel() + 1;
      int totweight = (int) u.getPeso();

      while ((j < n) && (totweight + coisas[j].getPeso() <= k)) {
          totweight += coisas[j].getPeso();
          p_b += coisas[j].getValor();
          j++;
      }

      if (j < n)
          p_b += (k - totweight) * coisas[j].getValor() / coisas[j].getPeso();

      return p_b;
    }
    
    public int recursiva(int k, Objeto[] coisas, int n){
      if (n == 0) {
        if (coisas[n].getPeso() <= k)
          return coisas[n].getValor();
        else 
          return 0;
      }
      int valorCom = -1;
      if(k >= coisas[n].getPeso()){
        valorCom = this.recursiva(k - coisas[n].getPeso(), coisas, n - 1) + coisas[n].getValor();
      }
      int valorSem = this.recursiva(k, coisas, n-1);
      return this.max(valorCom, valorSem);
    }
  
    public int PD(int k, Objeto[] coisas, int n){
        int A[][] = new int[n+1][k+1];
        for (int i = 1; i <= n;i++){
            for (int c = 0;c <= k;c++){
                if (coisas[i-1].getPeso() > c){
                    if(i - c >= 0)
                        A[i][c] = A[i-c][c];
                }
                else {
                    int menorSem = A[i - 1][c];
                    int menorCom = A[i - 1][c - coisas[i-1].getPeso()]+coisas[i-1].getValor();
                    A[i][c] = max(menorCom,menorSem);
                }
            }
        }
        return A[n][k];
    }

    public int aproximada(int k,Objeto[] coisas, int n){
      int s = 0;
      int c = 0;
      while(c < n && s + coisas[c].getPeso() <= k){
        s = s + coisas[c].getPeso();
        c = c + 1;
      }
      int soma = 0;
      for(int i = 0; i < c; i++){
        soma += coisas[i].getValor();
      }
      if(c > n || soma > coisas[c].getValor())
        return soma;
      else
        return coisas[c].getValor();
    }
    
    public int branch_bound(int k, Objeto coisas[], int n) {
      Arrays.sort(coisas, new Comparator<Objeto>() {
            public int compare(Objeto a, Objeto b) {
                double r1 = (double) a.getValor() / a.getPeso();
                double r2 = (double) b.getValor() / b.getPeso();
                return Double.compare(r2, r1);
            }
      });

      PriorityQueue<Node> Q = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return Integer.compare(b.getBound(), a.getBound());
            }
      });

      Node u = new Node();
      Node v = new Node();

      u.setLevel(-1);
      u.setProfit(0);
      u.setPeso(0);

      Q.add(u);

      int maxgetProfit = 0;

      while (!Q.isEmpty()) {
          u = Q.poll();

          if (u.getLevel() == -1)
              v.setLevel(0);

          if (u.getLevel() == n - 1)
              continue;

          v.setLevel(u.getLevel() + 1);

          v.setPeso(u.getPeso() + coisas[v.getLevel()].getPeso()) ;
          v.setProfit(u.getProfit() + coisas[v.getLevel()].getValor());

          if (v.getPeso() <= k && v.getProfit() > maxgetProfit)
              maxgetProfit = v.getProfit();

          v.setBound(bound(v, n, k, coisas)) ;

          if (v.getBound() > maxgetProfit)
              Q.add(v);

          v.setPeso(u.getPeso());
          v.setProfit(u.getProfit()) ;
          v.setBound(bound(v, n, k, coisas));

          if (v.getBound() > maxgetProfit)
              Q.add(v);
      }

      return maxgetProfit;
    }


  }