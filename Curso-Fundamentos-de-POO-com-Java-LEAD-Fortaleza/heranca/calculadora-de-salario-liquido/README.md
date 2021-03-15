<div>
    <h1>
        Projeto: Compreendendo as Classes Abstratas
        <font size="3">
            <br><i>por Ruan Dias - RdCoder</i>
        </font>
    </h1>
</div>

Este projeto faz parte de um conjunto de implementações práticas
pessoais voltadas a desenvolver os conhecimentos adquiridos por
mim no curso de
[_Fundamentos de POO com Java_](http://leadfortaleza.com.br/dal/?cp_cursos=/curso-de-fundamentos-de-programacao-orientada-a-objetos-com-java/),
 da instituição
[_LEAD Fortaleza_](https://leadfortaleza.com.br).

Você encontrará aqui, neste documento, informações úteis sobre a
atividade sugestionada pelo curso, como os objetivos a serem
alcançados, detalhes acerca da proposta prática feita, além
da descrição a respeito solução encontrada para resolver a
problemática.


## <br> Informações sobre o projeto

### ▸ Objetivo:
    Aplicar o paradigma da herança em um projeto real.

### ▸ Proposta:
    Codificar um programa de linha de comando que calcule, a partir
    do salário bruto e do bônus recebido por um funcionário, o valor
    final líquido do salário (a soma do salário bruto com o bônus),
    aplicando, para isso, o paradigma da herança.

### ▸ Solução:
Foram criadas uma classe do tipo ***SalarioBruto*** para representar o
salário bruto do funcionário e, em seguida, uma outra classe herdeira
de ***SalarioBruto***, do tipo ***SalarioLiquido***, que representa o salário
líquido dele. A última guarda em seus atributos o bônus recebido e lança mão de
métodos que somam o salário bruto do funcionário — obtido da 
superclasse — a esse bônus e, então, retorna o resultado como sendo
o salário líquido do funcionário.

Por último, implementei a classe ***Principal*** para conter toda a
lógica de execução do programa, desde a solicição dos dados sobre o 
salário ao usuário à impressão do salário final na tela.

### <br> ▸ Bibliografia:
- Aula 5, Herança, Fundamentos de POO com Java - LEAD Fortaleza | 
<http://leadfortaleza.com.br/dal/?cp_cursos=/curso-de-fundamentos-de-programacao-orientada-a-objetos-com-java/>