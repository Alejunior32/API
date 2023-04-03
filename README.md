# Desafio4_API

# :clipboard: descrição do projeto

 O projeto de API tinha como objetivo desenvolver um middleware para usuários consumirem notícias de forma 
rápida e prática através do consumo da API de Notícias Gratuitas (https://apinoticias.tedk.com.br/). 

# :hammer: Funcionalidades do projeto

### O projeto tem as seguintes funcionalidades:

- **Cadastro de administrador**: endpoint para cadastrar um usuário como administrador, somente administradores 
podem mandar essa requisição;
- **Deletar administrador**: endpoint para deletar um administrador, somente administradores 
podem mandar essa requisição;
- **Atualizar administrador**: endpoint para Atualizar um administrador, somente administradores 
podem mandar essa requisição;
- **Receber dados de administrador**: endpoint para receber um administrador, somente administradores 
podem mandar essa requisição;
- **Cadastro de usuário**: endpoint para cadastrar um usuário, somente administradores 
podem mandar essa requisição;
- **Deletar usuário**: endpoint para deletar um usuário, somente administradores 
podem mandar essa requisição;
- **Atualizar usuário**: endpoint para Atualizar um usuário, somente administradores 
podem mandar essa requisição;
- **Receber dados de usuário**: endpoint para receber um usuário, somente administradores 
podem mandar essa requisição;
- **Cadastro de etiqueta para usuário**: endpoint para cadastrar uma etiqueta para um usuário, somente o usuário pode 
salvar etiquetas para ele;
- **Histórico de parâmetros acessados**: endpoint que manda o Histórico de parâmetros acessados pelo usuário;
- **Histórico de etiquetas mais acessadas**: endpoint que manda as dez etiquetas mais acessadas e o número de acessos;
- **Acesso às notícias com as etiquetas cadastradas** : endpoint para o usuário acessar as notícias que tem relação com as etiquetas
do usuário, caso ele não tiver a etiqueta, retorna um erro pedindo para cadastrar a etiqueta.

### Exceeds concluidas:

- :heavy_check_mark: Testes unitários 
- :heavy_check_mark: JWT Token
- :heavy_check_mark: Swagger 
- :heavy_check_mark: Projeto no Postman  
- :heavy_check_mark: Endpoint para envio de e-mail com notícias da data corrente para 
usuários de acordo com suas etiquetas (somente adm) 
- :heavy_check_mark: Envio de e-mail para usuário cadastrado no sistema no ato do cadastro

# :telescope: Tecnicas e Tecnologias utilizadas

 - `Java 17`
 - `InteliJ IDEA`
 - `Spring Framework`
 - `Arquitetura REST`
 - `Junit`
 - `Mockito`

# 📁 Acesso ao projeto

 O projeto está na pasta Desafio4_API, será essa pasta que será aberta na IDE.

# 🛠️ Abrir e rodar o projeto

- `1°`: É necessario abrir no MySQL o script do banco de dados do projeto, que está na pasta "Popular banco MySQL",
para popular o banco.

- `2°`: Para abrir o projeto feito no postman é necessário ir no postmane na aba "My workspace" clicar em import, 
irá abrir uma janela, onde será selecionado o arquivo .json, que está na pasta "Projeto no postman".

# :incoming_envelope: Endpoints

## Requisições do Usuário:

 Nessas Requisições o usuário é enviado através do Token.

#### Etiqueta:

- Salvar Etiqueta , (**POST**) localhost:8080/v1/etiquetas/usuario
- Buscar Etiquetas do usuário, (**GET**) localhost:8080/v1/etiquetas/usuario

#### Notícias

 - Busca noticias enviando uma data, (**GET**) localhost:8080/v1/noticias/usuario/com-data/?data={DATA}&etiquetaId={Id}
 - Busca noticias com a data atual, (**GET**) localhost:8080/v1/noticias/usuario/data-atual/?etiquetaId={Id} 
 - Busca noticias com todas as etiquetas do usuário de uma data especifica, (**GET**) localhost:8080/v1/noticias/usuario/todas-as-noticias-do-usuario/com-data/?data={DATA}
 - Busca noticias com todas as etiquetas do usuário com a data atual, (**GET**) localhost:8080/v1/noticias/usuario/todas-as-noticias-do-usuario/data-atual/

#### Histórico

- Busca o Histórico do usuário, (**GET**) localhost:8080/v1/historicos/usuario

## Requisições do administrador

#### Usuário

- Cadastra usuário, (**POST**) localhost:8080/v1/usuarios
- Busca usuário, (**GET**) localhost:8080/v1/usuarios/{usuarioID}
- Busca todos usuários, (**GET**) localhost:8080/v1/usuarios
- Atualiza usuário, (**PUT**) localhost:8080/v1/usuarios/{usuarioId}
- Deleta usuário, (**DELETE**) localhost:8080/v1/usuarios/{usuarioId}

#### Admin

- Cadastra Admin, (**POST**) localhost:8080/v1/admins
- Busca Admin, (**GET**) localhost:8080/v1/admins/{adminID}
- Busca todos Admins, (**GET**) localhost:8080/v1/admins
- Atualiza Admin, (**PUT**) localhost:8080/v1/admins/{adminId}
- Deleta Admin, (**DELETE**) localhost:8080/v1/admins/{adminId}

#### Etiqueta

- Buscar todas as etiquetas cadastradas com os usuários que cada etiqueta possui, (**GET**) localhost:8080/v1/etiquetas/admin
- Buscar uma etiqueta com os usuários que possuem essa etiqueta, (**GET**) localhost:8080/v1/etiquetas/admin/{etiquetaID}

#### Etiquetas mais acessadas

- Busca as dez etiquetas mais acessadas, (**GET**) localhost:8080/v1/historicos/admin/dez-maiores-acessados

#### Email

- Envia um email para um usuario com as noticias, (**POST**) localhost:8080/v1/mail/enviar?usuarioId={usuarioID}

#### Swagger

- Documentação do swagger para o projeto, (**GET**) http://localhost:8080/v2/api-docs

## Autenticação

- Autentica o usuário e retorna o token JWT, (**POST**) localhost:8080/v1/auth

