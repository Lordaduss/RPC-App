package comum;

public class ContaNaoEncontradaException extends Exception {

    private static final long serialVersionUID = 1L;

    public ContaNaoEncontradaException(int numero) {
        super("Conta numero " + numero + " nao encontrada.");
    }
}
