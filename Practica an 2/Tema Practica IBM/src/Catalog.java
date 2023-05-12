public class Catalog {

        private int NrDeLaCatalog;
        private String name;
        private boolean VerificareAnTerminal;


        public Catalog()
        {

            super();
        }
        public  Catalog(int NrDeLaCatalog, String name, boolean VerificareAnTerminal){
            this.name = name;
            this.NrDeLaCatalog = NrDeLaCatalog;
            this.VerificareAnTerminal = VerificareAnTerminal;
        }


    public int getNrDeLaCatalog() {
        return NrDeLaCatalog;
    }

    public void setNrDeLaCatalog(int nrDeLaCatalog) {
        NrDeLaCatalog = nrDeLaCatalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerificareAnTerminal() {
        return VerificareAnTerminal;
    }

    public void setVerificareAnTerminal(boolean verificareAnTerminal) {
        VerificareAnTerminal = verificareAnTerminal;
    }

    public void VerificareAnTerminal(){
            if(VerificareAnTerminal == true)
            {
                System.out.println(name + " este capabil pentru a da bacalaureatul");
            }else {
                System.out.println(name + " nu este capabil pentru a da bacalaureatul");
            }
    }

    @Override
    public String toString() {
        return "NrDeLaCatalog=" + NrDeLaCatalog + ", name='" + name + '\'' + ", VerificareAnTerminal=" + VerificareAnTerminal;
    }
}
