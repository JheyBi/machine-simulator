import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*Autômatos de Pilha Determinísticos (P)
0,a;x;y,1 -- (transição de q0 para q1 lendo a da fita, x da pilha e escrevendo y na pilha) */

public class AP extends Leitor{
    
    private String initialStateAP;
    private String[] finalStatesAP;
    private List<String[]> conditionsAP;
    private List<String[]> inputFormatted = new ArrayList<String[]>();
    private List<String> message= new ArrayList<String>();
    Stack<String> pilha = new Stack<String>();
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


    public AP(String initialStateAP, String[] finalStatesAP, List<String[]> conditionsAP, String inputIn) throws IOException{
        System.out.println("Inicializando o AP..");
        this.initialStateAP = initialStateAP;
        this.finalStatesAP = finalStatesAP;
        this.conditionsAP = conditionsAP;
        this.lerEntrada(inputIn);
    }

    public void lerEntrada(String path) throws IOException{
        Path arquivo = Paths.get(path);
        if(!Files.exists(arquivo)){
            System.out.println("Não existe");
        }
        List<String> input = Files.readAllLines(arquivo);
        for(int i  = 0; i < input.size(); i++ ){
            //System.out.println(input.get(i).split(""));
            inputFormatted.add(input.get(i).concat("_").split(""));
        }
        // for(int i=0;i<inputFormatted.size();i++){
        //      for(String linhas:inputFormatted.get(i)){
        //         System.out.println(linhas);
        //      }   
        // }
    }

    // Displaying element on the top of the stack
    static String stack_peek(Stack<String> stack)
    {
        if(stack.empty()){
            return "_";
        }else{

            String element = (String) stack.peek();
            return element;

        }

    }

    public void verificarCondicao(String outputOut, String inputIn) throws IOException{
        Path arquivo = Paths.get(inputIn);
        List<String> fileInput = Files.readAllLines(arquivo);

        for(int i=0;i<this.inputFormatted.size();i++){
            
            int j=0;
            pilha.clear();
            pilha.push("Z");
            
            for(String input:inputFormatted.get(i)){

                //Setando o estadoAtual como o primeiro estado
                if(j==0){
                    
                    estadoAtual=initialStateAP;
                
                }

                //encontra todos as opções do estado atual
                //verifico a entrada com as condições de cada opção, se não bater já para. Se bater trocar
                int achou=0;
                for(String[] aux:conditionsAP){
                    //Achar as condições do estadoAtual
                    //F,a,Z
                    //F,b,Y
                    //0,a;x;y,1
                    //Split no aux[1]
                    String[] aux2 = aux[1].split(";");
                    if(estadoAtual.equals(aux[0])){
                        
                        //Achamos as condições, agora teremos que verificar se bate com o input
                        //aux[0] 0
                        if (input.equals(aux2[0])){
                            
                            // Se for vazio
                            String top = stack_peek(pilha);
                            //Logica - Leonardo Faria
                            /*if(aux2[1].equals("_") || top.equals(aux2[2])){
                                pilha.push(aux2[2]);
                                estadoAtual = aux[2];
                                achou=1;
                                break;
                            }
                            else if(top.equals(aux2[1])){

                                pilha.pop();
                                estadoAtual = aux[2];
                                achou=1;
                                break;

                            }
                            else{
                                break;
                            }*/

                            //Logica - João Bernardo
                            int desempilhar=0;
                            
                            //Desempilhar
                            //Se não tiver que desempilhar nada
                            if(aux2[1].equals("_")){
                            
                                desempilhar=1;
                            
                            }
                            // Se tiver que desempilhar alguma coisa
                            else if(top.equals(aux2[1])){
                                
                                pilha.pop(); //aux[1].get(1) x
                                desempilhar=1;
                            
                            }

                            //Empilhar
                            //Se não tiver que empilhar nada
                            if(desempilhar==1 && aux2[2].equals("_")){
                                
                                estadoAtual = aux[2];
                                achou=1;
                                break;

                            }
                            else if(desempilhar==1){
                                
                                pilha.push(aux2[2]);
                                estadoAtual = aux[2];
                                achou=1;
                                break;
                            
                            }

                        }
                        
                    }

                }
                
                if(achou==0){
                    estadoAtual = "ERRO";
                    break;
                }
                System.out.println(estadoAtual);
                j++;
                
            } 
            
            for(String aux:this.finalStatesAP){

                System.out.println(estadoAtual);
                if(estadoAtual.equals(aux) && pilha.empty()){

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
        System.out.println("Finalizando AP...\nFIM");

    }

}
