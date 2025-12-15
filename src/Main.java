import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        
        Store store = new Store();
        store.loadData();
        store.loadIncome();
        
        Scanner input = new Scanner(System.in);
        int choice = 0;
        
        while(choice !=7){
            System.out.println("\n ========== MENU TOKO BUKU ==========");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Semua Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Jual Buku");
            System.out.println("6. Tampilkan Pendapatan");
            System.out.println("7. Exit");
            System.out.println("Pilihan : ");
            choice = input.nextInt();
            input.nextLine();
            
            switch(choice){
                case 1:
                    store.addBuku();
                    store.simpanData();
                    break;
                case 2:
                    store.showBuku();
                    break;
                case 3:
                    store.findBuku();
                    break;
                case 4:
                    System.out.println("Masukkan Judul buku yang ingin dihapus : ");
                    String tittle = input.nextLine();
                    store.removeBuku(tittle);
                    store.simpanData();
                    break;
                case 5:
                    store.sellBuku();
                    store.simpanData();
                    store.simpanIncome();
                    break;
                case 6:
                    store.getIncome();
                    break;
                case 7:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid");
            }
        }
        input.close();
    }
}