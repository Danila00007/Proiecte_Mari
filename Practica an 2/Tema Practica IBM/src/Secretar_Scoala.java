public class Secretar_Scoala {
    //Suprascrierea toString-ului
    @Override
    public String toString() {
        return "Secretar_Scoala";
    }

    public static void main(String[] args) {

        //Initializare variabile pentru fiecare clasa
    Catalog p = new copil("Are carnet");
    copil c = new copil();
    Secretar_Scoala s = new Secretar_Scoala();

        //Afisare text din toString din Secretar_Scoala
        System.out.println(s);

        //Afisare text inainte de modificari din copil
        System.out.println(c.getName() + " are " + c);

        //Afisare text dupa modificari din copil
        c.setName("Bogdan");
        c.setCarnetDeNote("Are carnet");
        c.setNrDeLaCatalog(1);
        c.setVerificareAnTerminal(true);
        System.out.println(c.getName() + " are update-ul profilului cu " + c + "\n");

        //Afisare text pentru get-uri
        p.setName("Alex");
        p.setNrDeLaCatalog(2);
        p.setVerificareAnTerminal(false);
        System.out.println(p);
        p.VerificareAnTerminal();


        //Afisare text diferentele dintre cele 2 tipuri de referinta
        Catalog dif1 = new copil("Are carnet");
        copil dif2 = new copil();
        dif2.getCarnetDeNote();
        dif1.getName();


    }

}
