/* **********************************************************************
 *  La classe Employe
 * **********************************************************************/
abstract class Employe {
	private String nom;
	private String prenom;
	private int age;
	private String date;
	private string matricule;
	
	public Employe(String prenom, String nom, int age, String date) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.date = date;
		this.matricule =matricule
	}

	public abstract double calculerSalaire();

	public String getTitre()
		{
			return "L'employ�" ;
		}
	
	public String getNom() {
		return getTitre() + prenom + " " + nom;
	}
}

/* **********************************************************************
 *  La classe Commercial (regroupe Vendeur et Repr�sentant)
 * **********************************************************************/
abstract class Commercial extends Employe {
	private double chiffreAffaire;

	public Commercial(String prenom, String nom, int age, String date,string matricule,double chiffreAffaire) {
		super(prenom, nom, age, date,matricule);
		this.chiffreAffaire = chiffreAffaire;
	}

	public double getChiffreAffaire()
		{
			return chiffreAffaire;
		}
	
}

/* **********************************************************************
 *  La classe Vendeur
 * **********************************************************************/
class Vendeur extends Commercial {
	private final static double POURCENT_VENDEUR = 0.2;
	private final static int BONUS_VENDEUR = 400;
	public Vendeur(String prenom, String nom, int age, String date,string matricule,double chiffreAffaire) {
		super(prenom, nom, age, date,matricule,chiffreAffaire);
	}

	public double calculerSalaire() {
		return (POURCENT_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR;
	}

	public String getTitre()
		{
			return "Le vendeur ";
		}

}

/* **********************************************************************
 *  La classe Repr�sentant
 * **********************************************************************/
class Representant extends Commercial {
    private final static double POURCENT_REPRESENTANT = 0.2;
	private final static int BONUS_REPRESENTANT = 800;
	public Representant(String prenom, String nom, int age, String date, double chiffreAffaire) {
		super(prenom, nom, age, date,matricule, chiffreAffaire);
	}

	public double calculerSalaire() {
		return (POURCENT_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT;
	}

	public String getTitre()
		{
			return "Le repr�sentant ";
		}
}

/* **********************************************************************
 *  La classe Technicien (Production)
 * **********************************************************************/
class Technicien extends Employe {
	private final static double FACTEUR_UNITE = 5.0;
	

	private int unites;

	public Technicien(String prenom, String nom, int age, String date,string matricule, int unites) {
		super(prenom, nom, age, date);
		this.unites = unites;
	}

	public double calculerSalaire() {
		return FACTEUR_UNITE * unites;
	}

	public String getTitre()
		{
			return "Le technicien ";
		}
}

/* **********************************************************************
 *  La classe Manutentionnaire
 * **********************************************************************/
class Manutentionnaire extends Employe {
	private final static double SALAIRE_HORAIRE = 65.0;
	private int heures;

	public Manutentionnaire(String prenom, String nom, int age, String date,string matricule, int heures) {
		super(prenom, nom, age, date);
		this.heures = heures;
	}

	public double calculerSalaire() {
		return SALAIRE_HORAIRE * heures;
	}

	public String getTitre()
		{
			return "Le manut. " ;
		}
}

/* **********************************************************************
 *  L'interface d'employes à risque
 * **********************************************************************/
interface ARisque {
	int PRIME = 200;
}

/* **********************************************************************
 *  Une premi�re sous-classe d'employ�s à risque
 * **********************************************************************/
class TechnARisque extends Technicien implements ARisque {

	public TechnARisque(String prenom, String nom, int age, String date, string matricule, int unites) {
		super(prenom, nom, age, date, matricule, unites);
	}

	public double calculerSalaire() {
		return super.calculerSalaire() + PRIME;
	}
}

/* **********************************************************************
 *  Une autre sous-classe d'employ�s à risque
 * **********************************************************************/
class ManutARisque extends Manutentionnaire implements ARisque {
	
	public ManutARisque(String prenom, String nom, int age, String date, matricule, int heures) {
		super(prenom, nom, age, date, matrricule, heures);
	}

	public double calculerSalaire() {
		return super.calculerSalaire() + PRIME;
	}
}

/* **********************************************************************
 *  La classe Personnel
 * **********************************************************************/
class Personnel {
	private Employe[] staff;
	private int nbreEmploye;
	private final static int MAXEMPLOYE = 200;

	public Personnel() {
		staff = new Employe[MAXEMPLOYE];
		nbreEmploye = 0;
	}

	public void ajouterEmploye(Employe e) {
		++nbreEmploye;
		if (nbreEmploye <= MAXEMPLOYE) {
			staff[nbreEmploye - 1] = e;
		} else {
			System.out.println("Pas plus de " + MAXEMPLOYE + " employ�s");
		}
	}

	public double salaireMoyen() {
		double somme = 0.0;
		for (int i = 0; i < nbreEmploye; i++) {
			somme += staff[i].calculerSalaire();
		}
		return somme / nbreEmploye;
	}

	public void afficherSalaires() {
		for (int i = 0; i < nbreEmploye; i++) {
			System.out.println(staff[i].getNom() + " gagne "
					+ staff[i].calculerSalaire() + " francs.");
		}
	}
}

// ======================================================================

class Salaires {
	public static void main(String[] args) {
		Personnel p = new Personnel();
		p.ajouterEmploye(new Vendeur("Eluard", "Grindel", 45, "1995", 16A0RT, 30000));		
		p.ajouterEmploye(new Representant("Sosthene", "Chiba", 25, "2001",16B0RT, 20000));
		p.ajouterEmploye(new Technicien("Fabius", "lepetit", 28, "1998", 17B0RT, 1000));
		p.ajouterEmploye(new Manutentionnaire("Esperance", "labelle", 32, "1998",18B0RT, 45));
		p.ajouterEmploye(new TechnARisque("Mbaylaou", "Bichi", 28, "2000",16C0RT, 1000));
		p.ajouterEmploye(new ManutARisque("Arsene", "pater", 30, "2001", 18D0RT, 45));

		p.afficherSalaires();
		System.out.println("Le salaire moyen dans l'entreprise est de "
				+ p.salaireMoyen() + " francs.");

	}

}
