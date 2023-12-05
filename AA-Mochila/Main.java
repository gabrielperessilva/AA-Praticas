import java.util.Random;

public class Main {

    public static Objeto[] criaMochila(int n){
        Objeto[] mochila = new Objeto[n];
        Random random = new Random();
        for(int i = 0;i < n;i++){
            int valor = random.nextInt(10);
            int peso = random.nextInt(10);
            Objeto temp = new Objeto(peso, valor);
            mochila[i] = temp;
        }
        return mochila;
    }
    public static void main(String[] args){
        int k = 100;
        int n = 10;
        Objeto[] m = criaMochila(n);
        Objeto[] m1 = m;
        Objeto[] m2 = m;
        Objeto[] m3 = m;
        Objeto[] m4 = m;
        for(int i = 0;i < n;i++){
            System.out.println(m[i].getPeso()+" "+m[i].getValor());
        }

        Solucao s = new Solucao();
        System.out.println(s.recursiva(k, m1, n-1));
        System.out.println(s.PD(k, m2, n));
        System.out.println(s.aproximada(k, m3, n-1));
        System.out.print(s.branch_bound(k, m4, n-1));

    }
}
