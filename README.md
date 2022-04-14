# Tarefa

Desenvolver uma single page de um Open Banking (que no caso foi nomeado Bankadia) e vincular a API com os seguintes métodos: registro, login, extrato e saldo. 

# Tecnologias e ferramentas

As principais tecnologias foram Java no Spring para desenvolver a API e escolhi o banco de dados MySQL. Fiz testes no back-end com JUnit e fiz alguns comentários (para fixar conteúdo), documentação Swagger e deploy. Já no front, TypeScript foi a linguagem utilizada, e para desenvolvimento visual Material-UI, além de outros recursos como Axios, Redux e Router-Dom.

# Desenvolvimento

Optei por implementar registro e login, e criei também um componente de conta onde, na página home, no botão "clientes", o usuário cadastrado tem seus dados vinculados à sua respectiva conta e é mostrado na tela (na aplicação em si, só é possível cadastrar, logar e navegar), e também coloquei algumas outras funcionalidades como validação de token para acessar o conteúdo da página e de senha na hora do cadastro, por exemplo. 

Alguns métodos desenvolvidos no back-end como buscar usuário, buscar usuário pelo ID, assim como algumas requisições documentadas no Swagger, não foram consumidas no front-end, muito porque busquei desenvolver as coisas mais primordiais da tarefa.

Não implementei dashboard, extrato e saldo porque não tenho segurança para fazê-lo. Responsividade também não foi aplicada no projeto.

