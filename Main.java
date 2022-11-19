import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{

        String specsPath =args[0];
        String inputPath = args[1];
        String outputPath = args[2];
        Leitor l = new Leitor();
        l.lerArquivo(specsPath);
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
                machine.verificarCondicao(outputPath, inputPath );
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

