package com.jefiro.email;

import com.jefiro.generete.IdGenerete;
import com.jefiro.modelos.Usuario;

public class Email {
    private final IdGenerete id = new IdGenerete();
    private final ValidarEmail valida = new ValidarEmail();
    public void enviarCodigo(String email){
        var code = id.id();
        valida.inserirDados(code);
        SendEmail sendEmail = new SendEmail();
        var sessao = sendEmail.iniciarSessao();

        var assunto = "Bem-vindo ao nosso serviço!";
        var mensagem = "Olá,\n\n"
                + "Seja bem-vindo ao nosso serviço! Para completar o seu registro, utilize o seguinte código de verificação: \n\n"
                +"Código de Verificação: "+ code +"\n\n"
                +"Este código é válido por 15 minutos. Não compartilhe com outras pessoas.\n\n"
                +"Atenciosamente,\n"
                +"Equipe do nosso serviço";

        var enviaMsg = sendEmail.criarMenssagem(sessao, email,assunto,mensagem);

        sendEmail.enviarMenssagem(enviaMsg);
    }
    public boolean verificarCodigo(String codigo){
        return valida.validarCodigo(codigo);
    }
}
