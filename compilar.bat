@echo off
if not exist bin mkdir bin
javac -d bin -encoding UTF-8 src\comum\Conta.java src\comum\ContaService.java src\comum\ContaNaoEncontradaException.java src\comum\SaldoInsuficienteException.java src\servidor\ContaServiceImpl.java src\servidor\Servidor.java src\cliente\Cliente.java
echo Compilacao concluida. Classes em .\bin
pause
