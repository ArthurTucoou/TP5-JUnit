package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	public void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	// S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
	public void dontPrint() {
		// On met pas assez d'argent
		machine.insertMoney(PRICE - 1);
		assertFalse(machine.printTicket(), "On ne doit pas imprimer");
	}

	@Test
	// S4 : on imprime le ticket si le montant inséré est suffisant
	public void print() {
		// On met assez d'argent
		machine.insertMoney(PRICE + 1);
		assertTrue(machine.printTicket(), "On doit imprimer");
	}

	@Test
	// S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	public void prixTicketDécrémentée() {
		// On met assez d'argent
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		assertEquals(machine.getBalance(),10, "On doit imprimer");
	}

	@Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	public void prixTotalajour() {
		// On met assez d'argent
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		assertEquals(machine.getTotal(),PRICE, "On doit imprimer");
	}

	@Test
        // S7 :rend correctement la monnaie
        public void rendCorrectementLaMonnaie() {
          machine.insertMoney(PRICE + 20);
          assertEquals(20,machine.refund());

        }

    @Test
        // S8 :remet la balance à zéro
        public void remetLaBalanceAZero() {
          machine.insertMoney(PRICE);
          machine.refund();
          assertEquals(0,machine.getBalance());
        }
}
