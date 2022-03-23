package view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.Semaphore;
import controller.Transacao;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo_saque = new Semaphore(1);
		Semaphore semaforo_deposito = new Semaphore(1);
		
		for(int i=0; i < 20; i++) {
			double saldo = round (((Math.random() * 100001) + 1000), 2);
			double transacao = round(((Math.random() * 10001) + 1), 2);
			
			new Transacao(getTransacaoRandom(), i, saldo, transacao, semaforo_deposito, semaforo_saque).start();
		}
	}
	
	private static String getTransacaoRandom() {
		String transacao[] = {"saque", "deposito"};
		int random = new Random().nextInt(transacao.length);
		return transacao[random];
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_DOWN);
	    return bd.doubleValue();
	}
}
