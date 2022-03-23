package controller;

import java.util.concurrent.Semaphore;

public class Transacao extends Thread{
	private String tipo_transacao;
	private int codigo_conta;
	private double saldo_conta, valor_transacao;
	
	private Semaphore semaforo_deposito;
	private Semaphore semaforo_saque;
	
	public Transacao(String _tipo_transacao, int _codigo_conta, double _saldo_conta, 
					 double _valor_transacao, Semaphore _semaforo_deposito, Semaphore _semaforo_saque) {
		
		tipo_transacao = _tipo_transacao.toLowerCase();
		codigo_conta = _codigo_conta;
		saldo_conta = _saldo_conta;
		valor_transacao = _valor_transacao;
		
		semaforo_deposito = _semaforo_deposito;
		semaforo_saque = _semaforo_saque;
		
	}

	public void run() {
		if(tipo_transacao == "saque") {
			try {
				semaforo_saque.acquire();
				System.out.println("Conta " + codigo_conta +
								   " - Saldo: R$ " + saldo_conta + 
								   " Saque: R$ " + valor_transacao +
								   " -> Novo Saldo: R$ " + (saldo_conta - valor_transacao));
				
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaforo_saque.release();
			}
			
		}else if(tipo_transacao == "deposito") {
			try {
				semaforo_deposito.acquire();
				System.out.println("Conta " + codigo_conta + 
								   " - Saldo: R$ " + saldo_conta + 
								   " Depósito: R$ " + valor_transacao +
								   " -> Novo Saldo: R$ " + (saldo_conta + valor_transacao));
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				semaforo_deposito.release();
			}
		}
	}
}
