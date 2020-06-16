package az.elvin.hotel.model;

public class RoomStructure extends HotelModel{
    private int baseOccupancy;
    private int higherOccupancy;
    private int extaBed;
    private int kidsOccupancy;

    public int getBaseOccupancy() {
        return baseOccupancy;
    }

    public void setBaseOccupancy(int baseOccupancy) {
        this.baseOccupancy = baseOccupancy;
    }

    public int getHigherOccupancy() {
        return higherOccupancy;
    }

    public void setHigherOccupancy(int higherOccupancy) {
        this.higherOccupancy = higherOccupancy;
    }

    public int getExtaBed() {
        return extaBed;
    }

    public void setExtaBed(int extaBed) {
        this.extaBed = extaBed;
    }

    public int getKidsOccupancy() {
        return kidsOccupancy;
    }

    public void setKidsOccupancy(int kidsOccupancy) {
        this.kidsOccupancy = kidsOccupancy;
    }

    @Override
    public String toString() {
        return "RoomStructure{" + "baseOccupancy=" + baseOccupancy + ", higherOccupancy=" + higherOccupancy + ", extaBed=" + extaBed + ", kidsOccupancy=" + kidsOccupancy + '}';
    }
}
