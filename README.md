# AI Hack Logistics - App de Agendamento e Gestão de Consultas

**Descrição do Projeto:**

O **AI Hack Logistics** é um aplicativo Android desenvolvido para facilitar o agendamento e a gestão de consultas médicas. Utilizando o Firebase como banco de dados, o app permite aos usuários marcar consultas, visualizar uma lista de consultas agendadas e editar ou excluir consultas previamente cadastradas.

O aplicativo também oferece validações de dados importantes, como CPF e data de agendamento, garantindo a precisão das informações fornecidas pelos usuários. A interface do aplicativo é intuitiva e funcional, oferecendo uma experiência fluida ao navegar entre as páginas de agendamento, listagem e edição.

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
