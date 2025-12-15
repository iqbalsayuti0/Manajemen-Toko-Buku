public class TextBook extends Buku{
    private String mapel;
    
    public TextBook(String id, String tittle, float price, int stock, String mapel){
        super(id, tittle, price, stock);
        this.mapel = mapel;
    }
            
            
    public String getSubject(){
        return mapel;
    }
    
    @Override
    public double getDiskon(){
        return 0.05;
    }
    
    @Override
    public String toString() {
        return String.format(
        "%s | Mapel: %-12s",  super.toString(), mapel);
    }

    @Override
    public String toDataString() {
        return super.toDataString() + "|" + mapel;
    }
}