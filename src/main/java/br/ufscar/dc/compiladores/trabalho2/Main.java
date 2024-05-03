
package br.ufscar.dc.compiladores.trabalho2;

/**
 *
 * @author jeanf
*/
import br.ufscar.dc.compiladores.trabalho2.AlgumaLexer;
import br.ufscar.dc.compiladores.trabalho2.AlgumaParser;
import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;


public class Main
{
    public static void main(String[] args) throws RuntimeException, FileNotFoundException, IOException 
    {       
        // Valida quantidade de args
         if (args.length != 2) {
            System.out.println("Quantidade de args diferente de 2");
            System.exit(0);
        }
         
        // Faz a leitura do arquivo e cria o analisador léxico e sintático.
        AlgumaLexer input = new AlgumaLexer(CharStreams.fromFileName(args[0]));
        AlgumaParser parser = new AlgumaParser(new CommonTokenStream(input));

        parser.removeErrorListeners(); // Remove o listener de erro padrao
        parser.addErrorListener(Error.INSTANCE); // Insere o nosso listener personalizado
        
        // Abre o arquivo
        try (PrintWriter output = new PrintWriter(args[1])) {

            try {
                parser.programa(); // Ponto de entrada da analise
            }
            // Printa as mensagens de erro no arquivo saida.
            catch (ParseCancellationException error_msg) {
                output.println(error_msg.getMessage());
                output.close();
        } 
        // Erro caso nao consiga abrir o output
        } catch (IOException exception) {
            System.out.println("Error: could not open file: " + args[1]);
        }
    }
}