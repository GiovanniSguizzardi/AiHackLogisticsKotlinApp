# AI Hack Logistics - App de Agendamento e Gestão de Consultas

---

## Integrantes:

- RM551261 - Giovanni Sguizzardi
- RM98057 - Nicolas E. Inohue
- RM99841 - Marcel Prado
- RM552302 - Samara Moreira 
- RM552293 - Vinicius Monteiro

---

**Descrição do Projeto:**

O AIHackLogistics é um aplicativo Android projetado para agendamentos e marcação de consultas médicas. O aplicativo permite que os usuários criem novos agendamentos de consulta, visualizem uma lista de consultas e editem ou excluam consultas anteriores, tudo com o auxílio do Firebase.
Além disso, oferece validações importantes de dados, como CPF e data de agendamento, para garantir que as informações inseridas pelos usuários sejam precisas. A interface do aplicativo foi projetada para proporcionar uma experiência contínua, com pouca dificuldade ao navegar entre a página de agendamento, listagem e edição.

---

## Principais Funcionalidades:

- **Agendamento de Consultas**: O usuário pode selecionar uma especialidade médica, escolher a data e informar seus dados pessoais (nome e CPF) para realizar o agendamento.
- **Validação de CPF e Data**: O sistema valida se o CPF é válido e se a data fornecida é uma data possível e no formato correto (`dd/MM/yyyy`).
- **Listagem de Consultas**: As consultas agendadas são listadas em uma interface amigável, e o usuário pode clicar em qualquer consulta para editá-la.
- **Edição de Consultas**: Ao selecionar uma consulta na lista, o usuário é redirecionado para uma página de edição onde pode atualizar os dados.
- **Exclusão de Consultas**: O app também permite desmarcar consultas previamente agendadas.
- **Firebase**: Utiliza o Firebase Realtime Database para armazenar e gerenciar os dados das consultas em tempo real.

---

## Estrutura do Projeto:

- **Frontend**: Desenvolvido em Kotlin, utilizando componentes nativos do Android para garantir uma interface responsiva e intuitiva.
- **Backend**: O Firebase é utilizado como backend para armazenamento em tempo real e gerenciamento de dados das consultas.
- **Validação de Dados**: CPF e data são validados utilizando funções customizadas antes que o agendamento seja realizado.

---

## Tecnologias Utilizadas:

- **Kotlin**: Linguagem de programação principal utilizada para o desenvolvimento do aplicativo Android.
- **Firebase Realtime Database**: Banco de dados NoSQL em tempo real, usado para armazenar as consultas.
- **OkHttp**: Biblioteca utilizada para realizar requisições HTTP e conectar o aplicativo ao Firebase.
- **Android Jetpack Navigation**: Utilizado para navegação entre os diferentes fragments do app.
- **ViewBinding**: Implementado para facilitar o acesso aos componentes de UI, reduzindo código boilerplate.

---

## Estrutura de Pastas:

![image](https://github.com/user-attachments/assets/6a6ebdf8-6e77-465b-9758-840e9c3c02bb)

---

## Telas presentes na aplicação:

### Página Inicial:
![image](https://github.com/user-attachments/assets/c87af1b1-b155-4654-bc04-707b51a00829)

### Página de Agendamento de Consultas:
![image](https://github.com/user-attachments/assets/b4c74a07-6be8-428e-943d-b43ad50b61c4)

### Página de Confirmação da Consulta:
![image](https://github.com/user-attachments/assets/e1c77a0e-2961-434b-a6af-187022331d41)

### Página de Consultas Marcadas:
![image](https://github.com/user-attachments/assets/355fa04b-0675-4909-9bf6-d95ae0b4a50e)

### Página de Desmarcar Consultas:
![image](https://github.com/user-attachments/assets/bfa1ed95-21b6-48ef-a0e3-65331b81d602)

### Página de Edição de Consultas:
![image](https://github.com/user-attachments/assets/f34e9326-cca1-40bf-8e28-3c3de717869e)
