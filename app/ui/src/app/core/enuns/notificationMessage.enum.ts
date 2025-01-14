export enum NotificationMessageEnum{
  auth_success = 'Usuário autenticado com sucesso',
  auth_error = 'Usuário ou Senha inválido',
  authorization_error = 'Sessão inválida',
  recovery_success = "Foi enviado no email cadastrado o código para recuperaração do acesso, verifique no email e informar para cadastrar nova senha",
  recovery_error = "O email informado não foi localizado",
  generic_error = "Serviço indisponível no momento, tente novamente mais tarde.",
}
