import java.io.IOException;


/*Autômatos de Pilha Determinísticos (P)
0,a;x;y,1 -- (transição de q0 para q1 lendo a da fita, x da pilha e escrevendo y na pilha) */

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
                AP machine = new AP(l.getInitialState(), l.getFinalStates(), l.getConditions(), inputPath);
                machine.verificarCondicao(outputPath, inputPath);
                break;
            }

            case "T":{
                MT machine = new MT(l.getInitialState(), l.getFinalStates(), l.getConditions(), inputPath);
                machine.verificarCondicao(outputPath);
                break;
            }

            default :{
                System.out.println("Máquina não reconhecida, desligando...\n FIM");
                break;
            }

        }
        
     }
}

