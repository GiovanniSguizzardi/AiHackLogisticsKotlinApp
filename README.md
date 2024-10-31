# AI Hack Logistics - App de Agendamento, gestão de Consultas

> [!WARNING]
> A aplicação possui 2 versões:
> - [Versão 1.0.0](https://github.com/GiovanniSguizzardi/AiHackLogisticsKotlinApp/tree/f44e184e371c74e86e31e70585b6ff3b6c1d938a/ProjetoChallenge) ( VERSÃO DA SPRINT PASSADA -- SPRINT 03 )
> - [Versão 2.0.0](https://github.com/GiovanniSguizzardi/AiHackLogisticsKotlinApp/tree/f44e184e371c74e86e31e70585b6ff3b6c1d938a/ProjetoChallengeV2) ( VERSÃO DA SPRINT ATUAL -- SPRINT 04 )

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
- **Confirmação de Exclusão da Consulta**: Foi implementada um pop-up na página de Desmarcar Consulta para verificar se REALMENTE o usuario gostaria de fazer aquela ação.

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

![image](https://github.com/user-attachments/assets/0b66d669-8e26-43da-9bae-3a2c4e705c37)

---

## Telas presentes na aplicação:

### Página Inicial:
![image](https://github.com/user-attachments/assets/19f2c840-9d30-4f13-9937-9c4db8d61ec8)

### Página | Inicial de Pedido(s):
![image](https://github.com/user-attachments/assets/2f866b28-d372-4902-8de1-e900b102b861)

### Página | Agendar Consulta:
![image](https://github.com/user-attachments/assets/dde9b407-ee11-4175-a87b-8dc6083f8147)

### Página | Confirmar Consulta:
![image](https://github.com/user-attachments/assets/f4cd7581-787d-43dd-868c-9b981bfc2ef5)

### Página | Listar Consulta(s):
![image](https://github.com/user-attachments/assets/355fa04b-0675-4909-9bf6-d95ae0b4a50e)

### Página | Desmarcar Consulta(s):
![image](https://github.com/user-attachments/assets/bfa1ed95-21b6-48ef-a0e3-65331b81d602)

### Página | Editar Consulta(s):
![image](https://github.com/user-attachments/assets/f34e9326-cca1-40bf-8e28-3c3de717869e)

### Página | Solicitar Medicamento:
![image](https://github.com/user-attachments/assets/f990f06f-8000-4127-9739-10ed3eb1a37b)

### Página | Confirmar Medicamento:
![image](https://github.com/user-attachments/assets/57e421cd-de2d-406d-9851-0a998ff32058)

### Página | Listar Medicamento(s):
![image](https://github.com/user-attachments/assets/f4e47b7f-9a10-4597-88d0-48640d034001)

### Página | Cancelar Pedido(s):
![image](https://github.com/user-attachments/assets/1f062fa1-5335-46d9-acc0-ee586231dd71)


