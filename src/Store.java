import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Store {
    private ArrayList<Buku> bukus = new ArrayList<>();
    private double income = 0;
    Scanner input = new Scanner(System.in);
            
    public void addBuku(){
    
        System.out.println("Ketik 1 untuk Novel / Ketik 2 untuk TextBook");
        int pilihInput = input.nextInt();
        
        if(pilihInput == 1){
            input.nextLine();
            System.out.print("Masukkan ID buku: ");
            String id = input.nextLine();
            System.out.print("Masukkan judul buku: ");
            String tittle = input.nextLine();
            System.out.print("Masukkan genre buku: ");
            String genre = input.nextLine();
            System.out.print("Masukkan harga buku: ");
            float price = input.nextFloat();
            System.out.print("Masukkan stok buku: ");
            int stock = input.nextInt();
            input.nextLine();
            
            bukus.add(new Novel(id,tittle,price,stock,genre));
            System.out.println("Buku berhasil ditambahkan!");
        }
        else if(pilihInput == 2){
            input.nextLine();
            System.out.print("Masukkan ID buku: ");
            String id = input.nextLine();
            System.out.print("Masukkan judul buku: ");
            String tittle = input.nextLine();
            System.out.print("Masukkan mapel buku: ");
            String mapel = input.nextLine();
            System.out.print("Masukkan harga buku: ");
            float price = input.nextFloat();
            System.out.print("Masukkan stok buku: ");
            int stock = input.nextInt();
            input.nextLine(); //clear buffer (caritau maksudnya!!!)
     
            bukus.add(new TextBook(id,tittle,price,stock,mapel));
            System.out.println("Buku berhasil ditambahkan!");
        }else{
            System.out.println("Tolong ikuti instruksi broo!!!");
        }
     
    }
    
    public void simpanData(){
        try (PrintWriter pw = new PrintWriter(new FileWriter("data.txt"))){
            for (Buku b : bukus){
                pw.println(b.toDataString());
            }
        }catch(IOException e){
            System.out.println("Gagal menyimpan data");
        }
    }
    
    public void loadData(){
        bukus.clear(); //supaya ngga double
        
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            String line;
            
            while ((line = br.readLine()) != null) {
            String[] data = line.split("\\|");

            String type = data[0];
            String id = data[1];
            String tittle = data[2];
            float price = Float.parseFloat(data[3]);
            int stock = Integer.parseInt(data[4]);
            
            if(type.equalsIgnoreCase("NOVEL")){
                String genre  = data[5];
                bukus.add(new Novel(id,tittle,price,stock,genre));
            
            }else if(type.equalsIgnoreCase("TEXTBOOK")){
                String mapel = data[5];
                bukus.add(new TextBook(id,tittle,price,stock,mapel));
                }
            }
        }catch (IOException e){
            System.out.println("File belum ada / gagal dibaca");
        }
    }
    
    public void simpanIncome() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("income.txt"))) {
            pw.println(income);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan income");
        }
    }
    
    public void loadIncome() {
        try (BufferedReader br = new BufferedReader(new FileReader("income.txt"))) {
            income = Double.parseDouble(br.readLine());
        } catch (IOException | NumberFormatException e) {
            income = 0;
            System.out.println("Income belum ada / gagal dibaca");
        }
    }

    
    public void removeBuku(String tittle){
        Buku target = null;
        
        for (Buku b : bukus){
            if(b.getTittle().equalsIgnoreCase(tittle)){
                target = b;
                break;
            }
        }
        
        if (target != null){
            bukus.remove(target);
            System.out.println("Buku Berhasil dihapus!!");
        }else{
            System.out.println("Buku tidak ditemukan!!");
        }
    }
    
    public Buku findBuku(){
        System.out.println("Masukkan Judul buku yang kamu cari : ");
        String tittle = input.nextLine();
        
        for(Buku b : bukus){
            if(b.getTittle().equalsIgnoreCase(tittle)){
                System.out.println("Buku  ditemukan, berikut info lengkapnya! \n" + b);
                return b;
            }
        }
        return null;
    }
    
    public void sellBuku(){
        Buku buku = findBuku();
        
        if(buku == null){
            System.out.println("Buku tidak ditemukan!\n");
            return;
        }else{
            System.out.println("Masukkan jumlah yang ingin dijual : ");
            int qty = input.nextInt();
            input.nextLine();
            
            try{
                buku.sell(qty);
                income += buku.getFinalPrice() * qty;
            }catch(OutOfStockExceptions e){
                System.out.println(e.getMessage());
            }  
        }
    }
    
    public void showBuku(){
        if(bukus.isEmpty()){
            System.out.println("Sedang tidak ada buku, Mohon kembali lain kali ");
        }
        System.out.println("Daftar Buku : ");
        for(Buku b : bukus ){
            System.out.println(b);
        }
        System.out.println();
    }
    
    public double getIncome(){
      System.out.printf("Kekayaan saat ini : Rp %-,12.2f", income);
      return 0;
    }
}