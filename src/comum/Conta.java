package comum;
import java.io.Serializable;
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int numero;
    private final String titular;
    private double saldo;

    public Conta(int numero, String titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
    }

    @Override
    public String toString() {
        return String.format("Conta %d - %s - saldo: R$ %.2f", numero, titular, saldo);
    }
}
