public class Casilla {

    private boolean mina;
    private String valor;
    private String minaValor;


    public boolean getMina(){
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
        this.minaValor = "*";
        setValor(minaValor);
    }

    public void setValor(String valor){
        this.valor=valor;
    }
    public String getValor(){
        return this.valor;
    }

}
