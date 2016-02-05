import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        if(getLot(lotNumber) != null) {
            boolean successful = getLot(lotNumber).bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    getLot(lotNumber).getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        Lot selectedLot = null;
        boolean encontrado = false;
        int index = 0;
        while(index < lots.size() && !encontrado) {          
            if(lots.get(index).getNumber() == lotNumber) {
                selectedLot = lots.get(index);
                encontrado = true;
            } 
            index ++;
        }
        if(!encontrado) {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            selectedLot = null;
        }
        return selectedLot; 
    }

    /**
     * Metodo que mostrara por pantalla los detalles de todos los items que se estan subastando actualmente
     */
    public void close(){
        int index = 0;
        while(index < lots.size()){
            if(lots.get(index).getHighestBid() != null){
                System.out.println("Lote numero: " + lots.get(index).getNumber() + "   Descripcion: " + lots.get(index).getDescription() + 
                    "   Valor de la puja: " + lots.get(index).getHighestBid().getValue() + "   Pujador: " + lots.get(index).getHighestBid().getBidder().getName());
            }
            else{
                System.out.println("Lote numero: " + lots.get(index).getNumber() + "   Descripcion: " + lots.get(index).getDescription() + "   Sin pujas");
            }
            index ++;
        }
    }

    /**
     * Metodo que devuelve todos los items que no tienen una puja
     */
    public ArrayList<Lot> getUnsold(){
        ArrayList<Lot> notSold = new ArrayList<Lot>();
        int index = 0;
        while(index < lots.size()){
            if(lots.get(index).getHighestBid() == null){
                notSold.add(lots.get(index));
            }
            index ++;
        } 
        return notSold;
    }

    /**
     * Metodo que eliminara un objeto de la subasta
     */
    public Lot removeLot(int number){
        Lot lot = getLot(number);
        if(lot != null) {
            lots.remove(lot);
        }
        return lot;
    }
}