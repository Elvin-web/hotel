package az.elvin.hotel.model;

    import java.sql.Time;
    import java.util.Date;

    public class Staff extends HotelModel {

        private Hotel hotel;
        private String name;
        private String surname;
        private String address;
        private Date dob;
        private String phone;
        private Double salary;
        private Position position;
        private String jobStart;
        private String jobEnd;
        private String gender;
        private String image;
        private String email;
        private String username;
        private String password;
        private String ID;


        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Hotel getHotel() {
            return hotel;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setHotel(Hotel hotel) {
            this.hotel = hotel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public String getJobStart() { return jobStart; }

        public void setJobStart(String jobStart) { this.jobStart = jobStart; }

        public String getJobEnd() {
            return jobEnd;
        }

        public void setJobEnd(String jobEnd) {
            this.jobEnd = jobEnd;
        }

        @Override
        public String toString() {
            return "Staff{" +
                    "hotel=" + hotel +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", address='" + address + '\'' +
                    ", dob=" + dob +
                    ", phone='" + phone + '\'' +
                    ", salary=" + salary +
                    ", position=" + position +
                    ", jobStart='" + jobStart + '\'' +
                    ", jobEnd='" + jobEnd + '\'' +
                    ", gender='" + gender + '\'' +
                    ", image='" + image + '\'' +
                    ", email='" + email + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", ID=" + ID +
                    '}';
        }
    }

