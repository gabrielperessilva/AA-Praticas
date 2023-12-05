import java.util.ArrayList;
import java.util.Random;
public class Principal{
    static Random r = new Random();

    public static void criaVetRandom(int[] v, int n){
        for (int i = 0; i < n;i++) 
        {            
            v[i] = r.nextInt(0,1000);        
        }

    }

    public static double testeWP(int[] v, WP wp, int n){
        long startTime = System.nanoTime();
        int k = v[n/2];        
        Temp t = wp.partition(v, 0, n - 1, k);
        long endTime = System.nanoTime();
        boolean b = wp.verifica(v, 0, n-1, k, t.p, t.q);
        if (b == false)
            return -1;
        return (endTime - startTime) / 1_000_000.0;    
    }

    public static String header(int length, String title){
        int titleLength = title.length();
        int padding = length - titleLength - 2;
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return"|" +" ".repeat(Math.max(0, leftPadding)) + title +" ".repeat(Math.max(0, rightPadding)) +"|";    
    }

    public static void main(String[] args)
    {        
        WP wp = new WP();
        boolean b = true;
        double x;
        int[] arraySizes = {100, 1000, 10000, 100000, 1000000};        
        ArrayList<int[]> vetores = new ArrayList<>();
        for (int n: arraySizes) {
            int[] v = new int[n];            
            criaVetRandom(v, n);            
            vetores.add(v);        
        }        
        System.out.println(header(arraySizes.length * 11 + 1, "Tempo medio algoritmos"));        
        System.out.println(header(arraySizes.length * 11 + 1, "WP"));
        System.out.print("|"); 
        for (int arraySize : arraySizes) 
        {            
            String num = String.valueOf(arraySize);
            int padding = 11 - num.length() - 1;            
            System.out.print(" ".repeat(Math.max(0, padding / 2)) + num +" ".repeat(Math.max(0, padding - padding / 2)) +"|");        
        }        
        System.out.println();
                    
        System.out.print("|");
        int j = 0;
        for (int[] v: vetores) {
            double sumTempo = 0.0;
            for (int i = 0; i < 10; i++) {    
                x = testeWP(v.clone(), wp, arraySizes[j]);
                if (x == -1)
                    b = false;
                sumTempo += x;                
            }                
            System.out.printf(" %6.3fms |", sumTempo / 10);   
            j++;         
        }  
        System.out.println();

        if (b == true)
            System.out.println(header(arraySizes.length * 11 + 1, "Todos os testes executados com sucesso"));
        else
            System.out.println(header(arraySizes.length * 11 + 1, "Testes com erros"));
                      
        System.out.println();        
    }   
    
}
