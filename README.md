# AI Hack Logistics - App de Agendamento, gestão de Consultas

---

**Descrição do Projeto:**

O **AIHackLogistics** é um aplicativo Android desenvolvido para facilitar o agendamento e a gestão de consultas médicas. Ele permite que os usuários realizem agendamentos, visualizem uma lista de consultas, editem ou excluam consultas já marcadas, tudo com armazenamento em tempo real no Firebase. 

Com a nova atualização (versão 2.0.0), o aplicativo traz uma experiência de usuário aprimorada com novas funcionalidades, validações, animações e melhorias visuais. Isso garante que o processo de agendamento de consultas seja mais intuitivo, rápido e visualmente agradável para o usuário.

---

## Principais Funcionalidades:

### **Novidades na Versão 2.0.0**:

- **Interface Atualizada com Animações**: Agora, a interface conta com transições suaves e animações, melhorando a experiência do usuário ao navegar entre as telas.
- **Botões e Ícones Modernizados**: Todos os botões foram atualizados com novos ícones e cores, conferindo um design mais moderno e minimalista ao app.
- **Modo de Edição Melhorado**: O usuário agora pode editar consultas com maior facilidade, com um design refinado e uma interface mais intuitiva.
- **Avisos de Navegação**: Mensagens visuais foram adicionadas para informar o usuário sobre a possibilidade de voltar à tela anterior quando necessário.
- **Função Async Storage**: Além do Firebase, foi implementado o Async Storage para garantir que, mesmo offline, o app ainda consiga salvar temporariamente as informações dos agendamentos localmente e sincronizar posteriormente.

### **Funcionalidades Clássicas**:

- **Agendamento de Consultas**: Permite ao usuário selecionar uma especialidade médica, informar os dados pessoais (nome e CPF) e marcar uma consulta para uma data e horário específicos.
- **Validação de CPF e Data**: O app faz a validação de CPF e da data de agendamento para garantir que os dados fornecidos sejam corretos.
- **Listagem de Consultas**: Uma interface clara e organizada onde o usuário pode visualizar todas as consultas agendadas.
- **Edição e Exclusão de Consultas**: O app possibilita editar ou excluir consultas facilmente, tudo isso com o armazenamento e sincronização no Firebase.
- **Firebase Realtime Database**: Integração com o Firebase para armazenamento em tempo real das consultas e gerenciamento dinâmico dos dados.

---

## Estrutura do Projeto:

- **Frontend**: Desenvolvido em **Kotlin**, o projeto utiliza componentes nativos do Android, garantindo uma UI responsiva, fluida e de fácil uso.
- **Backend**: O **Firebase Realtime Database** armazena e gerencia os dados em tempo real, permitindo que as consultas sejam sincronizadas entre diferentes dispositivos.
- **Async Storage**: Implementado para garantir o salvamento temporário dos dados localmente no dispositivo, permitindo que o usuário continue a interagir com o app mesmo sem conexão à internet.
- **Animações e Transições**: Novas animações e transições entre telas foram adicionadas, criando uma experiência mais agradável.
- **Validação de Dados**: As funções de validação para CPF e Data de Agendamento garantem a inserção correta de dados pelos usuários.

---

## Tecnologias Utilizadas:

- **Kotlin**: Linguagem de programação principal utilizada para o desenvolvimento da aplicação Android.
- **Firebase Realtime Database**: Utilizado como backend para armazenamento em tempo real.
- **OkHttp**: Biblioteca para requisições HTTP.
- **Async Storage**: Armazenamento de dados local, implementado para otimizar a usabilidade em modo offline.
- **Android Jetpack Navigation**: Utilizado para gerenciar a navegação entre os diferentes fragments do app.
- **ViewBinding**: Facilita o acesso aos componentes de UI e reduz código repetitivo (boilerplate).
- **Lottie Animations**: Implementado para animações suaves e modernas na interface.

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
