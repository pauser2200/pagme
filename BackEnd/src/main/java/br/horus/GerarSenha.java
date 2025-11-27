package br.horus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {

    /**
     *
     * Classe para gerar a promeira senha
     * no caso 123456
     *
     **/
    public static void main(String[] args) {
      System.out.println(new BCryptPasswordEncoder().encode(new String("basic123")));

        //var encoder = new BCryptPasswordEncoder();
        ///System.out.println(encoder.matches("basic123",
               // "$2a$10$BIvtdkZTnFW14RxkHTEOYOy6cY77XRm2NTYgmrcfq2sTXNGH0X08C"));
    }
}
