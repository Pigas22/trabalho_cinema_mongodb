# 🎥 Projeto de Banco de Dados - Gerenciador de Cinema
## 📒 Sobre o Projeto:
A idealização do projeto foi proposta pelo professor [Howard Roatti](https://www.linkedin.com/in/howardroatti/) como uma avaliação durante a matéria, *`Banco de Dados`* no 4° período do curso Sistemas de Informação no Centro Universitário, FAESA.

Para esse projeto, foi escolhido como tema pela equipe, o Sistema de Gerenciamento de Seções de Diversos Cinemas.

Esse projeto também foi utilizado para o Trabalho da matéria *`Linguagem de Programação Orientada a Objetos`*, onde deveria ser implementados as seguintes estruturas:
- Herança
- Polimorfismo
- Classe Interface
- Tratamento de Exceção
- Interface Gráfica Básica

#### 📸 Link do vídeo demonstrativo no youtube: **<u>https://youtu.be/GwyLAv1S0tE?feature=shared</u>**

#### 🛠️ Tecnologias e Ferramentas utilizadas no Projeto:
<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Vscode](https://img.shields.io/badge/Vscode-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-white?style=for-the-badge&logo=mongodb)
![Maven](https://img.shields.io/badge/Maven-yellow?style=for-the-badge&logo=apache-maven)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white) 

</div>

### ✅ Requisitos:
Para o funcionamento correto do projeto, é necessário que tenha as seguintes tecnologias instaladas:
- **`JAVA`** (17 ou mais recente);
- **`Maven`** (v3.8.0 ou mais recente);
- **`MongoDB`** (v2.3.3 ou mais recente)

OBS: para o java é possível instalar a extensão do JAVA no vs code que instala uma JDK 17.

### ❓  Como Utilizar:
Após a instalação dos Requisitos acima, é necessário executar um comando Maven para instalar as dependências necessárias pra aplicação, para isso basta caminha até a pasta, _`trabalho_cinema_mongodb/teste`_:


```bash
cd ./trabalho_cinema_mongodb/teste
```

E assim executar o comando Maven:
```bash
mvn clean package
```

Por fim, devemos configurar a conexão do Banco de Dados com a Aplicação, para isso, navegue até a Classe [<u>__DatabaseMongoDb.java__</u>](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/connection/DatabaseMongoDb.java), nela será possível observar as seguintes variáveis:

#### 📘 Váriaveis referentes à conexão:
- **_`NOME_DATABASE`_** : Refere-se ao nome do Banco de Dados que será criado para a utilização da aplicação.
- **_`URL_MONGODB`_** : Refere-se à URL utilizada para encontrar e conectar com o serviço do MongoDB, para assim acessarmos o banco.
```Java
private static final String NOME_DATABASE = "cinema_mongo_db";
private static final String URL_MONGODB = "mongodb://localhost:27017/";
```

Após a configuração das váriaveis da classe _Database.java_, basta executar a Classe Principal, [**App.java**](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/App.java). 
Assim que iniciada, apresentará uma pergunta referente à reiniciar o Banco, caso seja a 1° execução do programa, é **EXTREMAMENTE** recomendado que responda à esta pergunta com `"Sim"`. Dessa forma o Banco de Dados será criado e inicializado com suas tabelas e seus respectivos dados. 

### 🥳 Pronto!!! Agora, a aplicação está configurada para ser utilizada.

### 📚 Estrutura e Organização:
- [<u>__Diagramas__</u>](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/dbd48fb2fdf03539d81b50c87ef23d8b775cdc2c/Diagramas) : Pasta referente ao Diagrama Relacional de Banco de Dados desenvolvido.
    - A aplicação possue 7 collections/entidades, sendo elas: ENDERECO, CINEMA, VENDA, SESSAO, FILME, CLIENTE E CLIENTE_VIP.
- [<u>__json__</u>](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/json) : Nesta pasta estão todos os arquivos JSON utilizados no desenvolvimento.
    - [insert_documents.json](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/json/insert_documents.json) : Arquivo onde possue os dados à serem inseridos em cada Coleção durante a inicialização do Banco.
- [<u>__src__</u>](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/5b304340d794ce270332eec8dbcd84b0756cb72e/teste/src) : Nesta pasta estão todos os códigos-fonte na linguagem Java.
    - [connection](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/connection) : Diretório designado à comunicação direta do Banco de Dados, com métodos gerais utilizados sob o banco.
    - [controllers](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/controllers) : Diretório designado às classes Controladoras, onde fazem a gestão entre as classes Moldes e o Banco.
    - [controllers > base](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/controllers/base) : Diretório designado às classes Base para as Controladoras, onde possuem métodos gerais utilizados em todas elas.
    - [models](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/models) : Diretório designado às classes Moldes, representação dos registros das Tabelas.
    - [reports](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/1363344e5d0070c16bc3526a2c50fefdc7f1602e/teste/src/main/java/com/trabalho/reports) : Diretório designado à leitura das consultas realizadas no banco.
    - [utils](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/utils) : Diretório designado às classes Auxiliares na aplicação, contendo formatações, menus, gerencimento de arquivos e etc... 
    - [**App.java**](https://github.com/Pigas22/trabalho_cinema_mongodb/blob/0f3e8d7f504c792ef48621d85c8192bcf89b87a1/teste/src/main/java/com/trabalho/App.java) : Classe principal do programa, classe onde possue o método Main e o loop principal da aplicação. 

## 🐱‍🏍 Participantes no Projeto:
- Davi Tambara Rodrigues;
- Samuel Eduardo Rocha de Souza;
- Thiago Holz Coutinho.
