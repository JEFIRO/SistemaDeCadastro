package com.jefiro.email;

import com.jefiro.generete.IdGenerete;
import com.jefiro.modelos.Usuario;

public class Email {
    private final String ASSUNTO = "Bem vindo ao JefiroApp, Agracecemos o seu cadastro";
    private final String CORPO = "SEJA BEM VINDO NOS DESEJAMOS A MELHOR EXPEREENCIA POSSIVEL EM NOSSO APP ";
    private final String CORPO2 = "SEU COODIGO DE VERIFICAÇÃO: ";
    private final String CORPO3 = " LEMBRE-SE SEU CODIGO FICARA DISPONIVEL POR 15 MINUTOS";
    private final IdGenerete id = new IdGenerete();
    private final ValidarEmail valida = new ValidarEmail(id.id());
    public void enviarCodigo(){

        Usuario usuario = new Usuario();

        valida.inserirDados();

        SendEmail sendEmail = new SendEmail("jefiroo@gmail.com","wvex akeb nbbu idmm");

        var sessao = sendEmail.iniciarSessao();
        var mensagem = CORPO + CORPO2 +this.valida.toString()+ CORPO3;
        var enviaMsg = sendEmail.criarMenssagem(sessao, usuario.getGmail(),ASSUNTO,mensagem);

        sendEmail.enviarMenssagem(enviaMsg);
    }
    public void verificarCodigo(){
        valida.buscarDados();
        boolean val = valida.validarCodigo();
        System.out.println(val);
    }
}
