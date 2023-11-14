# Agrix - Sistema de Gestão e Monitoramento de Fazendas

O Agrix é um sistema que possibilita a gestão e monitoramento de fazendas participantes. Ele oferece funcionalidades para cadastrar fazendas, plantações (crops), e associar fertilizantes a essas plantações. O sistema é desenvolvido em Java com o framework Spring Boot e utiliza um banco de dados MySQL para armazenar as informações.

## Funcionalidades

- **Cadastro de Fazendas:** Registre informações sobre diversas fazendas, incluindo detalhes como nome, localização e outros atributos.

- **Cadastro de Plantações (Crops):** Associe plantações a fazendas existentes, fornecendo dados como nome, área plantada, data de plantio e data de colheita.

- **Associação de Fertilizantes:** Associe fertilizantes a plantações, permitindo um controle detalhado sobre o uso de insumos agrícolas.

- **Autenticação de Usuários:** O sistema oferece autenticação de usuários, gerando tokens JWT para acesso seguro às funcionalidades.

## Rotas

### Autenticação

- **POST /auth/login:** Realiza o processo de autenticação, gerando um token JWT. Requer credenciais de usuário.

### Fazendas

- **POST /farms:** Cria uma nova fazenda com base nos dados fornecidos.
- **GET /farms:** Obtém a lista de todas as fazendas cadastradas.
- **GET /farms/{farmId}:** Obtém detalhes de uma fazenda específica pelo ID.
- **POST /farms/{farmId}/crops:** Associa uma plantação a uma fazenda existente.
- **GET /farms/{farmId}/crops:** Obtém a lista de plantações de uma fazenda específica pelo ID.

### Cultivos (Crops)

- **GET /crops:** Obtém a lista de todas as plantações cadastradas (requer autenticação de gerente ou administrador).
- **GET /crops/{cropId}:** Obtém detalhes de uma plantação específica pelo ID.
- **GET /crops/search:** Filtra plantações com base em um intervalo de datas de colheita.
- **POST /crops/{cropId}/fertilizers/{fertilizerId}:** Associa um fertilizante a uma plantação existente.
- **GET /crops/{cropId}/fertilizers:** Obtém a lista de fertilizantes associados a uma plantação pelo ID.

### Pessoas (Users)

- **POST /persons:** Cria um novo usuário com base nos dados fornecidos (requer autenticação de administrador).

### Fertilizantes

- **POST /fertilizers:** Cria um novo registro de fertilizante com base nos dados fornecidos.
- **GET /fertilizers:** Obtém a lista de todos os fertilizantes cadastrados (requer autenticação de administrador).
- **GET /fertilizers/{fertilizerId}:** Obtém detalhes de um fertilizante específico pelo ID.

## Tecnologias Utilizadas

- Java
- Spring Boot
- MySQL
- Docker

## Como Rodar

Certifique-se de ter o Docker instalado. Utilize o seguinte comando para iniciar o banco de dados:

```bash
docker-compose up -d
```

Em seguida, construa e execute a aplicação:

```bash
docker build -t agrix-app .
docker run -p 8080:8080 agrix-app
```

A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

## Requisitos

- Docker
- JDK 17

Explore as rotas mencionadas acima para interagir com o sistema Agrix.
