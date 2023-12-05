public class Instancia {
    private int tempo;
    private Nodo nodo;

    public Instancia(int tempo, Nodo nodo){
        this.tempo = tempo;
        this.nodo = nodo;
    }

    public int getTempo(){
        return this.tempo;
    }

    public Nodo getNodo(){
        return this.nodo;
    }
}
