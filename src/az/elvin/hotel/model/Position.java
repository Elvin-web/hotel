package az.elvin.hotel.model;

    public class Position extends HotelModel {

        private String positionValue;

        public String getPositionValue() {
            return positionValue;
        }

        public void setPositionValue(String positionValue) {
            this.positionValue = positionValue;
        }

        @Override
        public String toString() {
            return "Position{" + "positionValue=" + positionValue + '}';
        }
}
