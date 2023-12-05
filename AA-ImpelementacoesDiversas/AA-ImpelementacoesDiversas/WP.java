public class WP{

    public void swap(int[] v, int i, int j){
        int tmp = v[i];        
        v[i] = v[j];        
        v[j] = tmp;    
    }

    Temp partition(int[] v, int e, int d, int k) {
        int p = e, i, t = d;
        
        for (i = e;i <= d;i++) {
            if(v[i] == k){
                t = i;
                break;
            }
        }
        swap(v, t, d);
        for (i = e;i < d;i++) {
            if(v[i] < v[d]){
                swap(v, i, p);
                p++;
            }
        }
        int q = p;
        for (i = p;i < d;i++) {
            if(v[i] == v[d]){
                swap(v, i, q);
                q++;
            }
        }
        swap(v, q, d);
        

        return new Temp(v, p - 1, q);
    }

    boolean verifica(int v[], int e, int d, int k, int p, int q){

        for (int i = e; i <= p;i++){
            if (v[i] >= k){
                System.out.println(v[i]+"<");
                return false;
            }
                
        }
        for (int i = p+1; i <= q;i++){
            if (v[i] > k || v[i] < k){
                System.out.println(v[i]+"==");
                return false;
            }
        }
        for (int i = q+1; i <= d;i++){
            if (v[i] <= k){
                System.out.println(v[i]+">");
                return false;
            }
        }
        return true;
    }

}
