import java.util.List;

public class Nodo {

    private List<Instancia> instancias;
    private int tempo;

    public Nodo(int tempo){
        this.tempo = tempo;
    }
    
    public int getTempo(){
        return this.tempo;
    }

    public void addInstancia(Nodo nodo, int tempo){
        Instancia i = new Instancia(tempo, nodo);
        instancias.add(i);
    }
}
