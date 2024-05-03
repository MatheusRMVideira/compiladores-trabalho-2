/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufscar.dc.compiladores.trabalho2;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;


public class Error extends BaseErrorListener {

    public static final Error INSTANCE = new Error();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {
        // Esse metodo eh executado pelo ANTLR quando ocorre um erro no parsing
        Token token = (Token) offendingSymbol;

        // Inicio do print
        String line_msg = "Linha " + token.getLine() + ": ";

        // Obtem a mensagem de erro a partir do tipo do token
        String error_msg = getErrorMsg(token);

        // Lanca excecao
        throw new ParseCancellationException(line_msg + error_msg + "\nFim da compilacao");
    }

    private static String getErrorMsg(Token token) {
        // Obtem mensagem de erro a partir do token
        String error_msg;

        int token_type = token.getType();
        String token_text = token.getText();

        switch (token_type) { // Mensagens diferentes de acordo com o tipo do token
            case AlgumaLexer.INVALIDO:
                error_msg = token_text + " - simbolo nao identificado";
                break;
            case AlgumaLexer.CADEIA_ABERTA:
                error_msg = "cadeia literal nao fechada";
                break;
            case AlgumaLexer.COMENTARIO_ABERTO:
                error_msg = "comentario nao fechado";
                break;
            case Token.EOF:
                error_msg = "erro sintatico proximo a EOF";
                break;
            default:
                error_msg = "erro sintatico proximo a " + token_text;
        }
        return error_msg;
    }
}