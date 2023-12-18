# Desafio GrowUp 2023.2 - Squad 17

---

## Objetivo

O projeto CommunityApp tem como objetivo proporcionar uma plataforma interativa para comunidades, onde os usuários podem criar, compartilhar e discutir postagens em diferentes tópicos. A aplicação oferece funcionalidades como criação de postagens, comentários, e integração com comunidades específicas. Além disso, há recursos de autenticação para garantir a segurança e a privacidade dos usuários.

---

## Principais Recursos:

- **Criação de Postagens:** Os usuários podem criar postagens, compartilhar conteúdo e expressar suas opiniões.
- **Comentários:** Interatividade é promovida por meio de comentários em postagens, permitindo discussões e interações entre membros da comunidade.
- **Comunidades Específicas:** A aplicação suporta a criação e participação em comunidades específicas, proporcionando ambientes dedicados a tópicos de interesse.
- **Segurança e Autenticação:** A autenticação de usuários é assegurada para garantir um ambiente seguro e confiável.

---

## Tecnologias Utilizadas:

- **Spring Boot:** Backend da aplicação, oferecendo uma base robusta para o desenvolvimento.
- **Swagger:** Documentação interativa da API para facilitar o entendimento e teste das funcionalidades.
- **JWT (JSON Web Token):** Mecanismo utilizado para autenticação e segurança na transmissão de dados.
- **Banco de Dados Relacional:** Armazenamento persistente de dados para postagens, comentários e informações de usuário.

---

## Instruções de Execução:

### pré-requisitos:

- Java JDK 17 instalado 
- Utilize a IDE de sua preferência. Recomendado usar IDE proprio para Java como: [IntelliJ Idea](https://www.jetbrains.com/idea/download/#section=windows) | [Eclipse](https://www.eclipse.org/downloads/packages/) | [Netbeans](https://netbeans.apache.org/download/index.html)

### Passo 1: Clone o repositório

1. Abra o GitHub e acesse o repositório.
2. Clique no botão "Code" e copie o link.
3. Abra o terminal Git Bash.
4. Execute o seguinte comando para clonar o repositório:

```
git clone link_do_repositório
```
(substitua "link_do_repositório" pelo link que você copiou)

---

### Passo 2: Início

1. **Abra o Projeto na sua IDE:**
    - Utilize a IDE de sua preferência, como [IntelliJ Idea](https://www.jetbrains.com/idea/download/#section=windows), [Eclipse](https://www.eclipse.org/downloads/packages/), ou [Netbeans](https://netbeans.apache.org/download/index.html).
    - Navegue até o diretório onde o projeto foi clonado.

2. **Configuração do Banco de Dados H2 (Opcional):**
    - O CommunityApp utiliza o H2 como banco de dados em memória por padrão.
    - Durante a execução local, você pode acessar o console do H2 em [http://localhost:8080/h2-console](http://localhost:8080/h2-console). As configurações padrão do JDBC URL são geralmente `jdbc:h2:mem:testdb`, e você pode manter as outras configurações padrão.

3. **Configuração da Aplicação:**
    - Verifique as configurações do projeto, como o arquivo `application.properties` ou `application.yml`, para garantir que estejam corretas para o seu ambiente.

4. **Execução Local:**
    - Execute o aplicativo usando Maven ou a opção da sua IDE: `mvn spring-boot:run`.
    - O Swagger abrirá automaticamente no seu navegador padrão.

5. **Explorando a Documentação da API (Swagger):**
    - Caso o Swagger não abra automaticamente, acesse [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) para explorar e testar os endpoints da API.

Agora você está pronto para começar a desenvolver e explorar o CommunityApp localmente! 🚀

---

### Passo 3: Explorando Recursos com Swagger

1. **Criação de Novo Usuário:**
   - Para começar, utilize o endpoint `/auth/register` para criar um novo usuário. Preencha os detalhes necessários e execute a operação.

2. **Login e Obtenção do Token JWT:**
   - Em seguida, acesse o endpoint `/auth/login`. Insira suas credenciais recém-criadas e execute a operação. O token JWT será gerado como parte da resposta.

3. **Autorizando as Operações:**
   - Agora, na parte superior da janela do Swagger, clique na opção "Authorize".
   - Na janela de autorização, insira o token JWT no formato "Bearer {seu-token}" e clique em "Authorize". Isso configurará o Swagger para incluir o token nas solicitações futuras.

4. **Explorando os Recursos Protegidos:**
   - Com o token configurado, você pode explorar e testar endpoints protegidos. Navegue pela documentação do Swagger e escolha os endpoints que deseja experimentar.

5. **Executando Operações:**
   - Ao clicar em um endpoint, você verá campos para parâmetros. Preencha-os conforme necessário e clique em "Try it out". O Swagger incluirá automaticamente o token JWT nas solicitações.

6. **Aproveitando os Recursos:**
   - Explore operações como criação de postagens, interação em comunidades e outras funcionalidades disponíveis. O token JWT garante que você tenha as permissões adequadas.

Claro, aqui está um exemplo de um README básico sobre como um usuário pode usar os serviços do Mailtrap no projeto:

---

# Configuração do Mailtrap para recuperar a senha

O Mailtrap é um serviço que permite simular o envio de e-mails em ambientes de desenvolvimento, sem que os e-mails reais sejam entregues aos destinatários. Isso é extremamente útil para testar a funcionalidade de envio de e-mails em um aplicativo sem impactar os usuários reais.

## Passos para Configurar o Mailtrap no Seu Projeto Spring Boot

### 1. Crie uma Conta no Mailtrap

1. Acesse [Mailtrap](https://mailtrap.io/) e crie uma conta.
2. Após criar uma conta, crie uma nova caixa de entrada ("inbox") no Mailtrap. Anote as credenciais fornecidas.

### 2. Configuração no `application.properties`

No seu arquivo `application.properties` do projeto Spring Boot, adicione as seguintes configurações:

```properties
# Configurações do Mailtrap
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=SEU_USERNAME
spring.mail.password=SEU_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

Substitua `SEU_USERNAME` e `SEU_PASSWORD` pelos valores fornecidos pelo Mailtrap.

### 3. Teste de Envio de E-mails

Agora, ao enviar e-mails no seu aplicativo Spring Boot durante o desenvolvimento, os e-mails serão capturados pelo Mailtrap e exibidos na caixa de entrada configurada.

## Dicas Adicionais

- **Visualização de E-mails:** Acesse a caixa de entrada no Mailtrap para visualizar os e-mails enviados durante o desenvolvimento.

- **Ambiente de Desenvolvimento:** Certifique-se de que estas configurações são usadas apenas no ambiente de desenvolvimento. No ambiente de produção, configure as propriedades do e-mail de maneira apropriada.

Agora você está pronto para explorar e utilizar os recursos da API de maneira segura e autenticada através do Swagger.




