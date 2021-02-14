# Projeto: Entendendo as Interfaces

<div>
    <font size="2">
            <i>por Ruan Dias - RdCoder</i> <br><br>
    </font>
</div>

Este projeto faz parte de um conjunto de implementações práticas
pessoais voltadas a desenvolver os conhecimentos adquiridos por
mim no curso de 
[_Fundamentos de POO com Java_](http://leadfortaleza.com.br/dal/?cp_cursos=/curso-de-fundamentos-de-programacao-orientada-a-objetos-com-java/), 
 da instituição
[_LEAD Fortaleza_](https://leadfortaleza.com.br).

Você encontrará aqui, neste documento, informações úteis sobre esta 
atividade sugestionada pelo curso, como os objetivos a serem 
alcançados, detalhes sobre a proposta prática feita, além
da descrição sobre a solução encontrada para resolver a
problemática.

## <br> Informações sobre o projeto
### ▸ Objetivo:
    Compreender o conceito e a aplicação do polimorfismo no Java.

### ▸ Proposta:
    Implementar uma classe que represente a superclasse e que contenha
    métodos para serem sobrescritos e também subclasses dela, que
    sobrescrevam ao seu modo esses métodos.

### ▸ Solução:
Para desenvolver essa proposta, optei por abstrair a ideia de **formas
geométricas** para classes. Foram criadas, com isso: 
- Uma classe abstrata do tipo ***FormaGeometrica*** para representar
uma forma geométrica genérica qualquer e, dentro dela, inseri o método
*renderforma()* cuja função é renderizar formas concretas, derivadas dela,
na tela;
- Uma subclasse de ***FormaGeometrica***, concreta, do tipo ***Quadrado***
a fim de representar um quadrado, que é uma forma geométrica. Sobre ela,
sobrescrevi (um exemplo de polimorfismo) 
o método *renderforma()* de sua superclasse, acrescentando,
além da renderização já feita por esse método em sua versão na superclasse,
o comportamento de imprimir sua descrição na tela para o usuário;
- Do mesmo modo, uma subclasse de ***FormaGeometrica***, concreta, do tipo
***Triangulo***
representando um triângulo qualquer. Sobre ela, e assim como em ***Triangulo***,
sobrescrevi o método *renderforma()* — aplicando novamente o conceito de polimorfismo
— da superclasse e incluí o comportamento
de exibir sua descrição no console.


Além disso, para executar a aplicação, adicionei uma classe principal,
a ***Application***,
instanciei nela objetos a partir das classes concretas descritas acima 
e, por fim, foram chamados os métodos *renderForma()* delas para desenhá-las
na tela.

### <br> ▸ Bibliografia:
- Aula 8, Polimorfismo, Fundamentos de POO com Java - LEAD Fortaleza | 
<http://leadfortaleza.com.br/dal/?cp_cursos=/curso-de-fundamentos-de-programacao-orientada-a-objetos-com-java/>