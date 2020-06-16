package az.elvin.hotel.model;

    public class Role extends HotelModel{
        private  String roleName;

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public String toString() {
            return "Role{" + "roleName=" + roleName + '}';
        }
}
