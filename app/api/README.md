# identity_server

Domain Layer: Como já comentado, essa camada concentra toda a regra de negócio da aplicação. Essa deve ser a sua única preocupação, delegando qualquer outro tipo de atividade para as demais camadas;
Infrastructure Layer: É a camada de mais baixo nível, responsável por prover serviços como persistência e envio de email, ou seja, dar o suporte tecnológico para as demais camadas;
Application Layer: Coordena as atividades da aplicação, porém, não contém regras de negócio. Pode, por exemplo, manter um estado de determinada transação. Essa é a camada mais discutida, pois dependendo do domínio da aplicação, ela pode não ser necessária.
User Interface Layer: Responsável pela interação com o usuário, seja interpretando comandos ou exibindo informação para o mesmo.