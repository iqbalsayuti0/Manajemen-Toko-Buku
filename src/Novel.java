public class Novel extends Buku{
    private String genre;
    
    public Novel(String id, String tittle, float price, int stock, String genre){
        super(id, tittle, price, stock);
        this.genre = genre;
    }
    
    public String getGenre(){
        return genre;
    }
    
    @Override
    public double getDiskon(){
        return 0.10;
    }
    
    @Override
    public String toString() {
        return String.format(
        "%s | Genre: %-12s", super.toString(), genre);
    }

    @Override
    public String toDataString() {
        return super.toDataString() + "|" + genre;
    }

}
