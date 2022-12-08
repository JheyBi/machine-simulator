# Simulador de Máquinas de estados

<p>Dentro da Teoria da Computação temos máquinas que realizam uma série de operações,
desde reconhecer linguagens à executar funções. São fundamentais para o entendimento de
como as linguagens de programação funcionam e como um compilador as interpreta
</p>

### Nesse simulador desenvolvemos as três principais máquina:
* Automato Finito Determinísticos(Linguagens Regulares) - AFD 
  * Recebe a entradas e troca pro proximo estado 
* Automato de Pilha(Linguagens Livre de Contexto) - AP
  * Recebe a entrada, lê algo da pilha, escreve alguma coisa e troca pro proxima estado
* Máquina de Turing(Linguagens Recursivas) - MT
  * Recebe a entrada, anda a direita e a esquerda conforme o problema e troca de estado


### Funcionamento

<p>Esse simulador primeiro lê o arquivo specs.txt para identificar qual a máquina 
e suas propriedades, depois recebe um arquivo input.txt para reconhecer as entradas.
Ao final ele gera um arquivo output.txt que informa se as entradas foram ou aceitam ou rejeitadas 
</p>
<p>
Foi desenvolvido em java para prática na linguagem e pelos recursos de orientação a objeto
</p>

### Processamento

<p>Recebemos cada linha do arquivo atraves de uma função. Na linha das condições
damos split e conseguimos obter cada informação de forma separada e guardamos em uma lista.
Depois lemos o arquivo de entrada da mesma forma damos um split em cada linha recebida e jogamos em uma lista, 
assim verificamos se os valores dessa lista e da specs são iguais, se for, temos uma variável estadoAtual 
que será atualizada para o proximo item da lista(no caso nessa estrutra: q1, a, q2 ele verifica o a e se estiver certo vai para o q2). 
Se for diferente ele vao continuar procurando outras "opções" que apareçam o mesmo valor do estadoAtual, daí realmente se ele não encontrar ele rejeita.
Ao final de cada linha, concatenamos com A ou R e gravamos em uma lista, no fim escrevemos em um arquivo atraves de uma função 
</p>

### AFD

#### specs.txt
    1 F (tipo da máquina [T/P/F])
    2 I (estado inicial (número))
    3 F1,F2,F3 (estados finais (número))
    4 F,COND,T
    5 ()
    ...
    N ()

#### input.txt
    1 a
    2 b
    3 ab
    4 ba

#### output.txt
    1 R;a (R - Rejeita)
    2 A;b (A - Aceita)
    3 A;ab
    4 R;ba

### AP

#### specs.txt
    1 P (tipo da máquina [T/P/F])
    2 I (estado inicial (número))
    3 F1,F2,F3 (estados finais (número))
    4 F,COND,T
    5 ()
    ...
    N ()

#### input.txt
    1 aabb
    2 aaabb
    3 aabbb
    4 aaabbb
    5 ab
    6 aaaaabbbbb
    7 abbaaabbbbaaababbaba

#### output.txt
    1 A;aabb
    2 R;aaabb
    3 R;aabbb
    4 A;aaabbb
    5 A;ab
    6 A;aaaaabbbbb
    7 R;abbaaabbbbaaababbaba

### MT

#### specs.txt
    1 T (tipo da máquina [T/P/F])
    2 I (estado inicial (número))
    3 F1,F2,F3 (estados finais (número))
    4 F,COND,T
    5 ()
    ...
    N ()

#### input.txt
    1 ab
    2 aabb
    3 aaabbb
    4 abbb

#### output.txt
    1 R;AB
    2 R;AABB
    3 R;AAABBB
    4 R;ABbb

### Como utilizar


     $ git clone https://github.com/JheyBi/machine-simulator

     $ cd machine-simulator

     $ java Main <caminho do arquivo specs.txt> <caminho do arquivo input.txt> <caminho onde a saída irá aparecer>
   
#### Exemplo: 
     $ java Main Ex1AFD/specs.txt Ex1AFD/input.txt Ex1AFD/output.txt  
