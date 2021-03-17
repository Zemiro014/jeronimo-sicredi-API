# jeronimo-sicredi-API

   Este é uma API Rest que possui a finalidade de gerenciar um sistema de votação de uma corporação constituído por associados, bem como disponibilizar os resultados das
pautas em votação. Permite que os associados cadastrados no sistema possam participar de uma sessão de votos na qual se discute uma determinada pauta. 

   A API disponibiliza Endpoints que permitem manipular dados de um associado (pesquisar, inserir, deletar e atualizar); das pautas (pesquisar, inserir, deletar) 
e das sessões de voto (pesquisar todas as sessões e votar uma pauta). 

 

ESTRUTURA DO PROJECTO 

Link do diagrama das classes: https://app.diagrams.net/?libs=general;uml#G19_vX48YA3kUz6o4c8r4C3_4_aVhainOy (Não tenho certeza se poderão ter acesso a este Link)

OBSERVAÇÂO: o projecto implementa a estrutura DTO (Data Transference Object) 

As Entidades estão  no pacote: com.jeronimo.sicredi_API.domain

TECNOLOGIAS UTILIZADOS 

A API foi construída com Spring Boot em conexão com MongoDB. As dependências gerenciado por Gradle e a API consome mensagens da Kafka.
A API possui documentação Swegger: http://localhost:8081/swagger-ui.html#/
