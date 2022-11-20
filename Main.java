import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException{

        String specsPath =args[0];
        String inputPath = args[1];
        String outputPath = args[2];
        Leitor l = new Leitor();
        l.lerArquivo(specsPath);

        switch(l.getTypeMachine()){

            case "F":{
                AFD machine = new AFD(l.getInitialState(), l.getFinalStates(), l.getConditions(), inputPath);
                machine.verificarCondicao(outputPath, inputPath);
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

