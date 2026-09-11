package comum;

public class SaldoInsuficienteException extends Exception {

    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException(int numero, double saldoAtual, double valorSolicitado) {
        super(String.format(
                "Saldo insuficiente na conta %d: saldo atual R$ %.2f, valor solicitado R$ %.2f",
                numero, saldoAtual, valorSolicitado));
    }
}
