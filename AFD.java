import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*Autômatos Finitos Determinísticos (F)
0,a,1 -- (transição de q0 para q1 lendo a) */

public class AFD extends Leitor {
    
    private String initialStateAFD;
    private String[] finalStatesAFD;
    private List<String[]> conditionsAFD;
    private List<String[]> inputFormatted = new ArrayList<String[]>();
    private List<String> message= new ArrayList<String>();
    private String output;
    private String estadoAtual;
/*
-Primeiro ele vai ler o input
-ele vai analisar o primeiro elemento desse input
-ele vai analisar se existe algum condition para mudar o estadoAtual
    -se existir, o estadoAtual passa a ser o estado que muda
    -se não existir, confere se existe outra possibilidade
        -se não existir, programa  acaba
            -é um estado final?
                -se sim, programa aceita
                -se não, programa recusa
 */

/*
-Encontra primeira condição

-Verifica a condição é verdadeira:
    -se for executa uma função que irá passar para o proximo estado:
        -Essa função verifica se o proximo é nullo:
            -Se for, para o sistema e escreve o arquivo de saída
            -Senão executa a mudança e chama recursivamente a função de verificar até o proximo ser nullo
    
    -se for false, executa uma função que procura se há alguma condição dps,
    que possua esse estado atual e essa condição:
        -se encontrar, vai mudar para esse estado
        -senão vai escrever errado
*/

    public AFD(String initialStateAFD, String[] finalStatesAFD, List<String[]> conditionsAFD, String inputIn) throws IOException{
        System.out.println("Inicializando o AFD..");
        this.initialStateAFD = initialStateAFD;
        this.finalStatesAFD = finalStatesAFD;
        this.conditionsAFD = conditionsAFD;
        this.lerEntrada(inputIn);
    }

    public void lerEntrada(String path) throws IOException{
        Path arquivo = Paths.get(path);
        if(!Files.exists(arquivo)){
            System.out.println("Não existe");
        }
        List<String> input = Files.readAllLines(arquivo);
        for(int i  = 0; i < input.size(); i++ ){
            inputFormatted.add(input.get(i).split(""));
        }
        /*for(int i=0;i<inputFormatted.size();i++){
             for(String linhas:inputFormatted.get(i)){
                System.out.println(linhas);
             }   
        }*/
    }

    public void verificarCondicao(String outputOut, String inputIn) throws IOException{

        Path arquivo = Paths.get(inputIn);
        List<String> fileInput = Files.readAllLines(arquivo);

        for(int i=0;i<this.inputFormatted.size();i++){
            int j=0;

            for(String input:inputFormatted.get(i)){
                

                //Setando o estadoAtual como o primeiro estado
                if(j==0){
                    
                    estadoAtual=initialStateAFD;
                
                }

                //encontra todos as opções do estado atual
                //verifico a entrada com as condições de cada opção, se não bater já para. Se bater trocar
                //System.out.println(this.conditionsAFD.size());
                int achou=0;
                for(String[] aux:conditionsAFD){
                //Achar as condições do estadoAtual
                //F,a,Z
                //F,b,Y
                    if(estadoAtual.equals(aux[0])){
                        //Achamos as condições, agora teremos que verificar se bate com o input
                        if(input.equals(aux[1])){
                            achou=1;
                            //Achamos que o input bate, agora mudaremos de estado
                            estadoAtual = aux[2];
                            break;
                        }
                    }
                }
                if(achou==0){
                    estadoAtual = "ERRO";
                    break;
                }
                j++;
            } 
            
            for(String aux:this.finalStatesAFD){
                if(estadoAtual.equals(aux)){
                    output = "A";
                    break;
                }
                else{
                    output="R";
                }
            }
            message.add(output+";"+fileInput.get(i));

        }

        escreverArquivo(outputOut, message);
        System.out.println("Finalizando AFD...\nFIM");

    }

}
