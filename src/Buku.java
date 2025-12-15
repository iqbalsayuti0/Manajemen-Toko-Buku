public abstract class Buku implements Sellable{
    private String id;
    private String tittle;
    private float price;
    private int stock;
    public abstract double getDiskon();
    
    public Buku(String id, String tittle, float price, int stock){
        this.id = id;
        this.tittle = tittle;
        this.price = price;
        this.stock = stock;
    }
    
    public String getId(){
        return id;
    }
    public String getTittle(){
        return tittle;
    }
    public float getPrice(){
        return price;
    }
    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    
    public double getFinalPrice(){
        return price - (price * getDiskon());
    }
    
    @Override
    public void sell(int qty) throws OutOfStockExceptions{
        if(qty > stock){
            throw new OutOfStockExceptions("Stock tidak mencukupi, hanya tersisa " + stock + "lagi");
        }else{
            stock -= qty;
            System.out.println("Berhasil menjual " + qty + ", sisa stock adalah " + stock );
            
        }
    }
    
    @Override
    public String toString(){
        return String.format(
                "%-10s %-5s | %-30s | Rp %,12.2f | Stock: %-4d",
                "[" + getClass().getSimpleName().toUpperCase() + "]",
                id.toUpperCase(), tittle.toUpperCase(), getFinalPrice(), stock
        );
    }
    
    public String toDataString() {
        return String.join("|",
            getClass().getSimpleName().toUpperCase(),
            id,
            tittle,
            String.valueOf(price),
            String.valueOf(stock)
        );
    }
}