<div>
    <h1>
        Projeto: Calculadora de Salário Líquido
        <font size="3">
            <br><i>por Ruan Dias - RdCoder</i>
        </font>
    </h1>
</div>
​	Este projeto faz parte de um conjunto de implementações práticas pessoais voltadas a desenvolver os conhecimentos adquiridos por mim no curso de
[_Fundamentos de POO com Java_](http://leadfortaleza.com.br/dal/?cp_cursos=/curso-de-fundamentos-de-programacao-orientada-a-objetos-com-java/),
da instituição [_LEAD Fortaleza_](https://leadfortaleza.com.br).

Você encontrará neste documento informações úteis sobre a
atividade sugestionada pelo curso — [objetivos](#objetivo) a serem
alcançados, detalhes acerca da [proposta](#proposta) feita e da [solução](#solucao) desenvolvida.



## No que consiste este projeto?

### <span id="objetivo">▸ Objetivo:</span>

    Aplicar o paradigma da Programação Orientada a Objetos, aprendida
    no curso, lançando mão da linguagem Java.


### <span id="proposta">▸ Proposta:</span>

    Codificar um programa de linha de comando que calcule, a partir
    do salário base de um funcionário, o valor final líquido após o
    acréscimo de bônus.


### <span id="solucao">▸ Solução:</span>

- Abstrair o **salário** do funcionário, cujas classes criadas foram:

  | NOME                                                         | DESCRIÇÃO                                                    |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | [**Salario**](./src/main/java/rdcoder/edu/fundamentos_de_poo_com_java/heranca/calculadora_salario_liquido/salario/Salario.java) | Classe abstrata que representa um salário genérico qualquer. |
  | [**SalarioBruto**](./src/main/java/rdcoder/edu/fundamentos_de_poo_com_java/heranca/calculadora_salario_liquido/salario/SalarioBruto.java) | Classe concreta que herda de `Salario` (aplicada a ***herança*** aqui, conceito da OO) e representa o que o nome sugere. |
  | [**SalarioLiquido**](./src/main/java/rdcoder/edu/fundamentos_de_poo_com_java/heranca/calculadora_salario_liquido/salario/SalarioLiquido.java) | Classe que herda de `Salario` , que representa o salário líquido, ou seja, a soma de um **salário base** a um **bônus**. |

- Implementar a lógica do *front-end* da aplicação via CLI (*Command Line Interface*). Vide abaixo essa implementação:

  | NOME                                                         | DESCRIÇÃO                                                    |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | [**CLIBuilder**](./src/main/java/rdcoder/edu/fundamentos_de_poo_com_java/heranca/calculadora_salario_liquido/ui/CLIBuilder.java) | Classe que modela o ***front-end*** via terminal. Ela trata da renderização (exibição de componentes e retorno do resultado) e da coleta dos dados necessários para o cálculo. |
  | [**ProgramInfo**](./src/main/java/rdcoder/edu/fundamentos_de_poo_com_java/heranca/calculadora_salario_liquido/ui/ProgramInfo.java) | Classe abstrata que encapsula **constantes** importantes que guardam informações sobre o programa. |
  | [**Componente**](./src/main/java/rdcoder/edu/fundamentos_de_poo_com_java/heranca/calculadora_salario_liquido/ui/cli_components/Componente.java) | Interface que representa um **componente** genericamente. Portanto, os componentes destinados ao *front-end* (caixas de texto, por exemplo) devem implementar esta interface. |
  | [**TextBox**](./src/main/java/rdcoder/edu/fundamentos_de_poo_com_java/heranca/calculadora_salario_liquido/ui/cli_components/TextBox.java) | Classe que representa o componente **caixa de texto**, com opções de personalização de layout, label, etc, e que implementa a interface `Componente`. Ela permite ao usuário digitar texto via teclado e ao programa coletar esses dados.<br><br>Esta classes está recheada de diversas aplicações dos conceitos da orientação a objetos — dos seus princípios (SOLID), herança, polimorfismo, interfaces. Além de, até mesmo da linguagem Java — exceções, enumeradores, programação funcional, registros (*preview version* do OpenJDK 15), etc. |



​	Por fim, obtemos uma aplicação de terminal aparentemente simples, porém robusta e escalável por dentro, que encaminha o usuário para digitar o nome do funcionário, o salário base e o bônus que se quer aplicar sobre ele. Em seguida, integra essas variáveis, para, no fim, exibir o resultado ao usuário e guiá-lo para um novo cálculo, se desejado.

----



### ▸ Bibliografia:

- Aula 5, Herança, Fundamentos de POO com Java - LEAD Fortaleza | 
<http://leadfortaleza.com.br/dal/?cp_cursos=/curso-de-fundamentos-de-programacao-orientada-a-objetos-com-java/>