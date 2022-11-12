package sample;

public class WorkersTable {
        Integer workerID,personalno,homeno;
        String firstname,lastname,title;

        public WorkersTable(Integer workerID, String firstname, String lastname,Integer personalno, Integer homeno) {
            this.workerID = workerID;
            this.firstname = firstname;
            this.lastname = lastname;
            this.personalno = personalno;
            this.homeno=homeno;
        }

        public Integer getWorkerID() {
            return workerID;
        }

        public void setWorkerID(Integer workerID) {
            this.workerID = workerID;
        }

        public Integer getPersonalno() {
            return personalno;
        }

        public void setPersonalno(Integer personalno) {
            this.personalno = personalno;
        }

        public Integer getHomeno() {
            return homeno;
        }

        public void setHomeno(Integer homeno) {
            this.homeno = homeno;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    }


