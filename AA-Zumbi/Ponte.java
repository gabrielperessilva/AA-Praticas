import java.util.List;

public class Ponte{

    public static int max(int x, int y){
        if(x > y)
            return x;
        else
            return y;
    }
    public static void main(String args[]) {
        List<Nodo> Grafo;
        int n = 4;
        int v[] = {2,1,5,10}; // Tempo em minutos
        int tempoM = 17;

        for(int i = 0; i < n; i++){
            Nodo nodo = new Nodo(v[i]);
            for(int j = 0; j < n && j != i;j++){    
                nodo.addInstancia(nodo, v[j]);
            }
        }
    }
}