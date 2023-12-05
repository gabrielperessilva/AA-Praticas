public class Busca {
    
    public static void main(String args[]){
        int V[] = {1,2,1,2,1,2,3,3,3,4,5,6,7,6,5,4,5,3,2,1,2};
        int z = 7;
        int n = 21;
        int x = 0,y = n-1;
        while(x < y){
            
            if (Math.abs(V[x] - z) > Math.abs(V[y] - z)){
                y = y - Math.abs(V[y] - z);
            }else {
                x = x + Math.abs(V[x] - z);
            }

            if (V[x] == z){
                System.out.println(x);
                break;
            } 
        
            if (V[y] == z){
                System.out.println(y);
                break;
            }

        }

    }
}
