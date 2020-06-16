package az.elvin.hotel.model;

    public class RoomStatus extends HotelModel {

        private String roomStatus;

        public String getRoomStatus() {
            return roomStatus;
        }

        public void setRoomStatus(String roomStatus) {
            this.roomStatus = roomStatus;
        }

        @Override
        public String toString() {
            return "RoomStatus{" + "roomStatus=" + roomStatus + '}';
        }
}
