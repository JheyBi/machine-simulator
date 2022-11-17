import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{

        Leitor l = new Leitor();
        l.lerArquivo("specs.txt");
        // System.out.println(l.getTypeMachine());
        // System.out.println(l.getInitialState());
        // for(String aux:l.getFinalStates()){
        //     System.out.println(aux);
        // }
        // for(int i=0;i<l.getConditions().size();i++){
        //     for(String aux:l.getConditions().get(i)){
        //         System.out.println(aux);
        //     }
        // }
        //l.lerEntrada("input.txt");

        switch(l.getTypeMachine()){

            case "F":{
                AFD machine = new AFD(l.getInitialState(), l.getFinalStates(), l.getConditions());
                machine.verificarCondicao();
                break;
            }

            case "P":{
                break;
            }

            case "T":{
                break;
            }

            default :{
                break;
            }

        }
        
     }
}

