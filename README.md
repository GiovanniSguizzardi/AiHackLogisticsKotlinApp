# AI Hack Logistics - App de Agendamento, Gestão de Consultas e Medicamentos

> [!WARNING]
> A aplicação possui 2 versões:
> - [Versão 1.0.0](https://github.com/GiovanniSguizzardi/AiHackLogisticsKotlinApp/tree/f44e184e371c74e86e31e70585b6ff3b6c1d938a/ProjetoChallenge) (VERSÃO DA SPRINT PASSADA — SPRINT 03)
> - [Versão 2.0.0](https://github.com/GiovanniSguizzardi/AiHackLogisticsKotlinApp/tree/f44e184e371c74e86e31e70585b6ff3b6c1d938a/ProjetoChallengeV2) (VERSÃO DA SPRINT ATUAL — SPRINT 04)

**Descrição do Projeto:**

O **AIHackLogistics** é um aplicativo Android desenvolvido para facilitar o agendamento e a gestão de consultas médicas, bem como o gerenciamento de pedidos de medicamentos. Ele permite que os usuários realizem agendamentos, visualizem listas de consultas e medicamentos, editem ou excluam itens já marcados, tudo com armazenamento em tempo real no Firebase.

Com a nova atualização (versão 2.0.0), o aplicativo traz uma experiência de usuário aprimorada com novas funcionalidades, validações, animações e melhorias visuais. Isso garante que o processo de agendamento de consultas e gerenciamento de medicamentos seja mais intuitivo, rápido e visualmente agradável para o usuário.

---

## Principais Funcionalidades:

### **Novidades na Versão 2.0.0**:

- **Interface Atualizada com Animações**: Agora, a interface conta com transições suaves e animações, melhorando a experiência do usuário ao navegar entre as telas.
- **Botões e Ícones Modernizados**: Todos os botões foram atualizados com novos ícones e cores, conferindo um design mais moderno e minimalista ao app.
- **Modo de Edição Melhorado**: O usuário agora pode editar consultas e pedidos de medicamentos com maior facilidade, com um design refinado e uma interface mais intuitiva.
- **Avisos de Navegação**: Mensagens visuais foram adicionadas para informar o usuário sobre a possibilidade de voltar à tela anterior quando necessário.
- **Função Async Storage**: Além do Firebase, foi implementado o Async Storage para garantir que, mesmo offline, o app ainda consiga salvar temporariamente as informações dos agendamentos e pedidos localmente e sincronizar posteriormente.
- **Confirmação de Exclusão**: Foi implementada uma confirmação de exclusão, tanto para consultas quanto para medicamentos, para que o usuário confirme antes de realizar essa ação.

### **Funcionalidades de Medicamentos na Versão 2.0.0**:

- **Listagem de Pedidos de Medicamentos**: Exibe uma lista dos pedidos com possibilidade de visualização detalhada e opção de edição.
- **Solicitação de Medicamentos**: Permite que o usuário crie novos pedidos, com validações de CPF e quantidade.
- **Edição de Pedidos de Medicamentos**: Possibilidade de editar as informações do pedido, como nome do solicitante, medicamento, quantidade e CPF.
- **Exclusão de Pedidos com Confirmação**: Um pop-up de confirmação garante que o usuário deseja realmente excluir o pedido.

### **Funcionalidades Clássicas**:

- **Agendamento de Consultas**: Permite ao usuário selecionar uma especialidade médica, informar os dados pessoais (nome e CPF) e marcar uma consulta para uma data específica.
- **Validação de CPF e Data**: O app valida o CPF e a data de agendamento para garantir que os dados fornecidos sejam corretos.
- **Listagem de Consultas**: Uma interface organizada onde o usuário pode visualizar todas as consultas agendadas.
- **Edição e Exclusão de Consultas**: O app possibilita editar ou excluir consultas facilmente, tudo com o armazenamento e sincronização no Firebase.
- **Firebase Realtime Database**: Integração com o Firebase para armazenamento em tempo real das consultas e gerenciamento dinâmico dos dados.

---

## Estrutura do Projeto:
- **Frontend**: Desenvolvido em **Kotlin**, o projeto utiliza componentes nativos do Android, garantindo uma UI responsiva e de fácil uso.
- **Backend**: O **Firebase Realtime Database** armazena e gerencia os dados em tempo real, permitindo que as consultas e pedidos de medicamentos sejam sincronizados entre diferentes dispositivos.
- **Async Storage**: Implementado para garantir o salvamento temporário dos dados localmente no dispositivo, permitindo que o usuário continue a interagir com o app mesmo sem conexão à internet.
- **Animações e Transições**: Novas animações e transições entre telas foram adicionadas, criando uma experiência mais agradável.
- **Validação de Dados**: As funções de validação para CPF e Data garantem a inserção correta de dados pelos usuários.

### Estrutura e Código do Módulo de Medicamentos
Para facilitar a navegação e desenvolvimento, as novas funcionalidades de medicamentos foram organizadas da seguinte forma no repositório:

  - `activity_medicamentos`: Conjunto de layouts e XMLs específicos para a gestão de medicamentos.
  - `activity_inicial_medicamentos.xml`: Tela inicial do módulo de medicamentos, com acesso às opções de listagem, solicitação e exclusão.
  - `activity_listar_pedidos.xml`: Layout para listar os pedidos de medicamentos, com design adaptado para exibir as informações mais relevantes.
  - `PaginaMedicamentosActivity`: Tela principal para navegação das funcionalidades de medicamentos.
  - `PaginaSolicitacaoMedicamentoFragment`: Fragment para solicitação de novos medicamentos.
  - `PaginaListarPedidosFragment`: Exibe a lista de pedidos e permite edição e exclusão.
  - `PaginaEditarPedidoFragment`: Permite a edição das informações do pedido de medicamento selecionado.
  - `PaginaExcluirPedidoFragment`: Implementação para exclusão de pedidos de medicamentos com confirmação.

### Atualizações no Navigation e Estrutura de Rotas
As novas rotas adicionadas ao `nav_graph.xml` incluem as transições entre as telas de medicamentos e consultas, garantindo uma navegação clara e organizada. Veja um exemplo de configuração das novas ações:

- `action_PaginaInicial_to_PaginaMedicamentosActivity`
- `action_PaginaMedicamentosActivity_to_ListarPedidos`
- `action_PaginaListarPedidos_to_PaginaEditarPedido`

Essas ações definem as transições entre as telas, adicionando animações para melhorar a experiência do usuário ao navegar entre consultas e pedidos de medicamentos.

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


