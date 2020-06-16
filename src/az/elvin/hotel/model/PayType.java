package az.elvin.hotel.model;

    public class PayType extends HotelModel{
        private String payType;

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        @Override
        public String toString() {
            return "PayType{" + "payType=" + payType + '}';
        }
    }

