package az.elvin.hotel.model;

    public class Star extends HotelModel{
        private long star;

        public long getStar() {
            return star;
        }

        public void setStar(long star) {
            this.star = star;
        }

        @Override
        public String toString() {
            return "Star{" + "star=" + star + '}';
        }
}
