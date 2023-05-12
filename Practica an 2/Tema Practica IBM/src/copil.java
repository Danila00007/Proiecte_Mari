public class copil extends Catalog{
    private String CarnetDeNote;

    public copil(){
        super(0, "Nu este specificat", false);
        CarnetDeNote = "Nu este specificat";
    }

    public copil(String CarnetDeNote){
    this();
    this.CarnetDeNote = CarnetDeNote;
    }

    public String getCarnetDeNote() {
        return CarnetDeNote;
    }

    public void setCarnetDeNote(String carnetDeNote) {
        CarnetDeNote = carnetDeNote;
    }

    @Override
    public String toString() {
        return "CarnetDeNote='" + CarnetDeNote + '\'' + " " + super.toString();
    }
}
