# Documentação
  

# Projeto: Gestão Financeira 

CURSO 

  Ciências da Computação - Pontifícia universidade católica de Minas Gerais

## Alunos integrantes da equipe

* Arthur Braga de Carvalho
* Eduardo Spinelli
* Guilherme Lima Martini
* Rodrigo Machado Ribeiro da Gloria
* Pedro Xavier

## Professores responsáveis


* Lucio
* Romel Vieira Carneiro
* Ayala

# Estrutura do Documento

- [Informações do Projeto](#informações-do-projeto)
  - [Participantes](#Alunos-integrantes-da-equipe)
- [Estrutura do Documento](#estrutura-do-documento)
- [Introdução](#introdução)
  - [Problema](#problema)
  - [Objetivos](#objetivos)
  - [Justificativa](#justificativa)
  - [Público-Alvo](#público-alvo)
- [Especificações do Projeto](#especificações-do-projeto)
  - [Personas e Mapas de Empatia](#personas-e-mapas-de-empatia)
  - [Histórias de Usuários](#histórias-de-usuários)
  - [Requisitos](#requisitos)
    - [Requisitos Funcionais](#requisitos-funcionais)
    - [Requisitos não Funcionais](#requisitos-não-funcionais)
  - [Restrições](#restrições)
- [Projeto de Interface](#projeto-de-interface)
  - [User Flow](#user-flow)
  - [Wireframes](#wireframes)
- [Metodologia](#metodologia)
  - [Divisão de Papéis](#divisão-de-papéis)
  - [Ferramentas](#ferramentas)
  - [Controle de Versão](#controle-de-versão)

- [Projeto da Solução](#projeto-da-solução)
  - [Tecnologias Utilizadas](#tecnologias-utilizadas)
  - [Arquitetura da solução](#arquitetura-da-solução)
- [Avaliação da Aplicação](#avaliação-da-aplicação)
  - [Plano de Testes](#plano-de-testes)
  - [Ferramentas de Testes (Opcional)](#ferramentas-de-testes-opcional)
  - [Registros de Testes](#registros-de-testes)
- [Referências](#referências)


# Introdução
Ter uma gestão financeira bem feita é fundamental para o sucesso e a estabilidade de qualquer indivíduo, família, empresa ou organização, envolve o controle e a administração adequada dos recursos financeiros disponíveis, de modo a garantir que as metas financeiras sejam alcançadas de forma sustentável.A proposta do nosso site é justamente essa, facilitar ao máximo a vida dos usuários, mostrando que se pode ter uma gestão de seus rendimentos de uma forma clara e simples.


## Problema
Conforme dito na introdução, o problema principal a ser enfrentado neste projeto é atender as necessidades dos usuários, dando a possibilidade de simplificar suas finanças e ter controle absoluto de seus gastos.


## Objetivos
O objetivo geral deste trabalho é criar uma plataforma que ajude os usuários a controlar, analisar e melhorar suas finanças pessoais. Aqui estão alguns dos principais objetivos:

Controle Financeiro: Permitir que os usuários registrem e acompanhem todas as suas transações financeiras, incluindo despesas e receitas.
Orçamento Personalizado: Oferecer a capacidade de criar e acompanhar orçamentos personalizados, ajudando os usuários a definir metas financeiras realistas.
Análise de Gastos: Fornecer ferramentas de análise para que os usuários possam entender onde estão gastando seu dinheiro e identificar áreas para economizar.


## Justificativa
Atualmente o Brasil se encontra em
um período de crise, por isso, economizar em todos os aspectos possíveis é essencial, nosso site vem para solucionar este problema a crescente demanda por ferramentas e recursos que ajudem as pessoas a lidar com suas finanças de forma mais eficaz, além de fornecer educação financeira, automatização, segurança e suporte na tomada de decisões financeiras mais informadas. Isso pode contribuir significativamente para a saúde financeira dos indivíduos e das empresas.


## Público-Alvo

Jovens Profissionais.
Estudantes Universitários.
Pessoas que desejam economizar.
Investidores Iniciantes.
Pessoas que querem melhorar seus hábitos financeiros.
Pequenos Empresários


## Personas 
![persona 1](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/138338965/cd3be318-19c1-4f0b-a169-34f66bb5e30a)

![persona 2](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/138338965/19dc4d4e-f3b4-425c-8f79-48a3d443398a)




## Mapas de Empatia
![mapa de empatia 1](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/138338965/078223ec-4444-4bdc-bced-6ae3e849bbb4)

![mapa de empatia 2](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/138338965/65813cc8-f703-434c-adbc-3c2bf0ee2ade)




## Histórias de Usuários

João tinha dificuldades com dinheiro, gastava sem controle. Baixou um aplicativo de finanças, começou a registrar despesas e definiu metas de economia. O app mostrou gráficos e dicas financeiras. João mudou seus hábitos, economizou para uma viagem e investiu em seu futuro. Agora, ele tem paz de espírito financeira


## Requisitos

As tabelas que se seguem apresentam os requisitos funcionais e não funcionais que detalham o escopo do projeto.

### Requisitos Funcionais

| ID   | Descrição                                                        | Prioridade |
|------|------------------------------------------------------------------|------------|
| RF-01| O site deve permitir o registro e o acompanhamento dos gastos   | Alta       |
| RF-02| O site deve permitir que o usuário defina um limite dos gastos mensais | Baixa      |
| RF-03| O site deve gerar relatórios (Como gráficos) que mostrem o panorama financeiro | Média      |
| RF-04| O site deve mostrar uma comparação dos gastos em diferentes períodos | Média      |
| RF-05| O site deve mostrar a rentabilidade de diferentes tipos de investimento | Alta       |
| RF-06| O site deve permitir a configuração de diferentes perfis (Casa, pessoal, trabalho) | Baixa      |
| RF-07| O site deve permitir que o usuário categorize suas compras      | Média      |
| RF-08| O site deve apresentar notícias relevantes      | Média      |



### Requisitos não Funcionais

| ID    | Descrição                                                           | Prioridade |
|-------|---------------------------------------------------------------------|------------|
| RNF-01| O site deve conseguir exibir as informações de forma correta mesmo com muitas entradas de gasto | Alta       |
| RNF-02| O site deve exibir os gráficos de maneira clara, mesmo se apresentar muitas categorias | Alta       |
| RNF-03| O site deve ter interface intuitiva e fácil de usar                 | Média      |
| RNF-04| O site deve apresentar configurações para os seus gráficos          | Baixa      |
| RNF-05| O site deve ser responsivo                                          | Média      |
| RNF-06| O site deve ter disponibilidade alta, com baixo tempo de inatividade| Baixa      |
| RNF-07| O site deve apresentar capacidade de recuperação dos dados          | Baixa      |



## User Flow
O fluxo de usuário foi imaginado da seguinte forma, as telas serão explicadas na seção de wireframes.

![image](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/120015178/d20b21f6-71a0-4efe-8f19-168f2408d5f1)



## Wireframes
# Tela Inicial
![image](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/120015178/a125b20a-066f-4ce2-bf6a-98bace04bf22)

# Tela de Perfil
![image](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/120015178/052255c1-266c-49fe-81e8-3066f18b08b2)


# Tela de Notícia

![image](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/120015178/a37fce1d-e27d-4158-b3af-3ef2f75ad701)


# Tela de Notícia Completa
![image](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/120015178/2d7b895e-1576-4717-86a0-7c1a1a5ec49e)

# Tela de Organização dos Gastos

![image](https://github.com/ICEI-PUC-Minas-PPLCC-TI/ti-1-ppl-cc-m-20232-empresa-de-gestao-financeira/assets/120015178/ea32a50d-4d99-418e-a547-8546fea799cc)



## Gestão de Projetos


# Referências

> - [Formato ABNT](https://www.normastecnicas.com/abnt/trabalhos-academicos/referencias/)
> - [Referências Bibliográficas da ABNT](https://comunidade.rockcontent.com/referencia-bibliografica-abnt/)
> - [Awesome searchbar](https://www.youtube.com/watch?v=v1PeTDrw6OY)
> - [Input file](https://www.youtube.com/watch?v=gXXmQeZOHKY&t=744s)
>-  [Página de Cadastro](https://www.youtube.com/watch?v=Q68vbJplf7I&t=449s)
