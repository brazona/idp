export enum NotificationMessageEnum{
  auth_success = 'Usuário autenticado com sucesso',
  auth_error = 'Usuário ou Senha inválido',
  authorization_error = 'Sessão inválida',
  recovery_success = "Foi enviado no email cadastrado o código para recuperaração do acesso, verifique no email e informar para cadastrar nova senha",
  recovery_error = "O email informado não foi localizado",
  generic_error = "Serviço indisponível no momento, tente novamente mais tarde.",
  validate_code_success = "O código foi validado com sucesso, atualize a senha para prosseguir ",
  validate_code_error = "Código invalido, digite o código enviado no email ou solicite um novo" ,
}
