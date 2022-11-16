import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{
        // Path arquivo = Paths.get("teste.txt");

        Leitor l = new Leitor();
        l.lerArquivo("teste.txt");
        System.out.println(l.getTypeMachine());
        System.out.println(l.getInitialState());
        for(String aux:l.getFinalStates()){
            System.out.println(aux);
        }
        for(int i=0;i<l.getConditions().size();i++){
            for(String aux:l.getConditions().get(i)){
                System.out.println(aux);
            }
        }
        
     }
}

