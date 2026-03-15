# API REST - Gestão de Empreendimentos

## 🎯 Propósito
Esta aplicação é um sistema simples de gerenciamento de dados focado no cadastro e manutenção de **Empreendimentos**. Desenvolvido como um exercício de avaliação ao programa IA para Devs do SCTEC/2026, o projeto expõe uma API REST para operações de CRUD (Create, Read, Update, Delete). O objetivo principal vai além da mera funcionalidade: busca demonstrar o domínio de fundamentos arquiteturais sólidos, código limpo e boas práticas modernas do ecossistema Java, garantindo uma base escalável e de fácil manutenção.

## 📹 Apresentação e Vídeo Pitch

Como parte da entrega deste projeto, foi gravado um vídeo de apresentação (pitch) detalhando as decisões arquiteturais, a estrutura do código e uma demonstração prática da aplicação em funcionamento.

Neste vídeo, você encontrará:
* **Visão Geral:** O propósito da API de Gestão de Empreendimentos.
* **Decisões Técnicas:** A explicação sobre o uso de `records` para DTOs, Enums para integridade e o padrão Repository.
* **Tratamento de Erros:** Como a aplicação lida com validações e retorna erros no padrão RFC 7807 (Problem Details).
* **Demonstração Prática:** A execução do projeto (via Docker) e o consumo dos endpoints simulando cenários reais.

🔗 **[Clique aqui para assistir ao Vídeo Pitch no Google Drive](https://drive.google.com/file/d/1t8FUUZXtoxFn03DOzgLiXBu631WKbS0M/view?usp=sharing)**


## 🚀 Tecnologias Utilizadas
* **Java 21+**: Linguagem principal, aproveitando os recursos modernos.
* **Spring Boot 4.0.3**: Framework base, configuração automática e criação das rotas de recursos.
* **Spring Data JPA / Hibernate**: Mapeamento objeto-relacional (ORM) e abstração transparente da camada de persistência.
* **H2 Database**: Banco de dados relacional em memória, adequado para simulações, garantindo que a aplicação suba rapidamente com dados de *seeding* pré-carregados.
* **Jakarta Bean Validation**: Especificação oficial para validação de integridade dos dados de entrada.
* **Docker & Docker Compose**: Para conteinerização e padronização do ambiente de execução.

## 🏗️ Decisões Arquiteturais e Padrões de Projeto

### Padrão Repository e Pragmatismo
A camada de persistência foi estruturada utilizando o padrão **Repository** do Spring Data JPA. Dado o escopo restrito e a baixa complexidade das regras de negócio, optou-se intencionalmente por não adotar abordagens mais complexas como o *Domain-Driven Design (DDD)* ou a criação de *Value Objects* para campos específicos. A introdução dessas camadas geraria um *overengineering* desnecessário para o momento. O padrão Repository atende perfeitamente ao requisito, provendo uma abstração elegante para o banco de dados mantendo o código conciso e legível.

### Desacoplamento via Interfaces (Injeção de Dependência)
O fluxo de negócio foi rigorosamente isolado da camada de controle web. Utilizamos injeção de dependência orientada a interfaces (com a interface `EmpreendimentoService` assinando os contratos e a classe `EmpreendimentoServiceImpl` executando a lógica real). Essa inversão de dependência garante baixo acoplamento, facilitando a substituição de implementações no futuro e viabilizando a criação de testes unitários completamente isolados através da injeção de *Mocks*.

### Imutabilidade com DTOs e Records
Para blindar o modelo de domínio (Entities) e evitar vazamento de dados estruturais da tabela, a API utiliza o padrão **Data Transfer Object (DTO)**. Empregamos a funcionalidade de `records` do Java moderno para construir DTOs puramente imutáveis e livres de *boilerplate* (dispensando a escrita manual de getters e setters). Foram elaborados DTOs distintos para Entrada (Request), Saída (Response) e Atualização Parcial, garantindo que o cliente da API envie e receba estritamente o que é permitido.

### Integridade com Enums
Para campos categóricos e de domínio fechado, como o **Segmento de atuação** (Tecnologia, Comércio, Indústria, Serviços, Agronegócio) e o **Status** (Ativo/Inativo), utilizamos estruturas de **Enums**. Essa decisão promove segurança de tipagem (*type safety*) em tempo de compilação, elimina o risco de inserção de "strings mágicas" incorretas no banco de dados e facilita a validação automática do payload JSON.

### Validações e Problem Details (RFC 7807)
As requisições passam por uma validação estrita utilizando o **Bean Validation** (`@NotBlank`, `@Size`, `@Email`). Em caso de falha, as exceções são interceptadas globalmente por uma classe `@RestControllerAdvice` e formatadas seguindo o padrão da RFC 7807 (**Problem Details for HTTP APIs**). Isso fornece aos clientes (Front-end ou Mobile) uma estrutura de erro padronizada, previsível e com indicações exatas de quais campos falharam, melhorando drasticamente a experiência de integração.

## 🐳 Como Executar a Aplicação

A aplicação foi totalmente conteinerizada através de um `Dockerfile` (utilizando *multi-stage build* para otimização de imagem) e orquestrada pelo `docker-compose`. Isso garante que o projeto rode de forma idêntica em qualquer sistema operacional, sem a necessidade de instalar e configurar o JDK ou o banco de dados localmente.

1. Certifique-se de ter o **Docker** e o **Docker Compose** instalados e rodando na sua máquina.
2. Pelo terminal, navegue até o diretório raiz do projeto (onde está o arquivo `docker-compose.yml`).
3. Execute o comando de inicialização:
   ```bash
   docker-compose up --build
   ```

## 🧪 Testando a API com Postman

Para facilitar a validação e o uso da API, o projeto inclui uma coleção pré-configurada do Postman (arquivo `Empreendimentos.postman_collection.json`).

A principal utilidade desta coleção é fornecer um ambiente de testes "pronto para uso". Todas as operações do CRUD já estão mapeadas com as URLs corretas (apontando para o `localhost:8080`), os verbos HTTP correspondentes e os cabeçalhos necessários (`Content-Type: application/json`). Além disso, as requisições de criação (POST) e atualização (PUT) já contêm exemplos de *payloads* JSON formatados exatamente conforme os DTOs e validações da nossa aplicação. Isso elimina a necessidade de digitar os testes do zero, previne erros de sintaxe nos JSONs e permite que qualquer pessoa valide o funcionamento completo da API em questão de segundos.

**Como carregar a coleção no Postman:**
1. Abra o aplicativo do Postman na sua máquina.
2. No painel superior esquerdo, clique no botão **"Import"** (ou use o atalho `Ctrl+O` / `Cmd+O`).
3. Uma janela será aberta. Arraste o arquivo `Empreendimentos.postman_collection.json` para a área indicada ou clique em "choose files" para procurá-lo no seu computador.
4. O Postman identificará automaticamente o formato do arquivo. Clique em **"Import"** para confirmar.
5. Uma nova coleção intitulada "API Empreendimentos" aparecerá na barra lateral (aba *Collections*).