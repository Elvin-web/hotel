package az.elvin.hotel.model;
import java.util.Date;
    public class Hotel extends HotelModel {

        private String hotelName;
        private String hotelAddress;
        private Star star;
        private String hotelPhone;
        private String hotelEmail;
        private String logo;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getHotelName() {
            return hotelName;
        }

        public void setHotelName(String hotelName) {
            this.hotelName = hotelName;
        }

        public String getHotelAddress() {
            return hotelAddress;
        }

        public void setHotelAddress(String hotelAddress) {
            this.hotelAddress = hotelAddress;
        }

        public Star getStar() {
            return star;
        }

        public void setStar(Star star) {
            this.star = star;
        }

        public String getHotelPhone() {
            return hotelPhone;
        }

        public void setHotelPhone(String hotelPhone) {
            this.hotelPhone = hotelPhone;
        }

        public String getHotelEmail() {
            return hotelEmail;
        }

        public void setHotelEmail(String hotelEmail) {
            this.hotelEmail = hotelEmail;
        }

        @Override
        public String toString() {
            return "Hotel{" +
                    "hotelName='" + hotelName + '\'' +
                    ", hotelAddress='" + hotelAddress + '\'' +
                    ", star=" + star +
                    ", hotelPhone='" + hotelPhone + '\'' +
                    ", hotelEmail='" + hotelEmail + '\'' +
                    ", logo='" + logo + '\'' +
                    '}';
        }
    }


