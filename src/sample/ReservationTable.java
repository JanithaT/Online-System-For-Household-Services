package sample;

public class ReservationTable {
    Integer reservation_id,home_owner_id,worker_id;
    String worker_name,reservation_date,reservation_start_time,reservation_end_time,reservation_details,submit_dateTime,
    worker_remark,worker_reply,homeowner_reply;

    public ReservationTable(Integer reservation_id, Integer home_owner_id, String worker_name, String reservation_date, String reservation_start_time, String reservation_end_time, String reservation_details, String submit_dateTime, String worker_remark, String worker_reply, String homeowner_reply) {
        this.reservation_id = reservation_id;
        this.home_owner_id = home_owner_id;
        this.worker_name = worker_name;
        this.reservation_date = reservation_date;
        this.reservation_start_time = reservation_start_time;
        this.reservation_end_time = reservation_end_time;
        this.reservation_details = reservation_details;
        this.submit_dateTime = submit_dateTime;
        this.worker_remark = worker_remark;
        this.worker_reply = worker_reply;
        this.homeowner_reply = homeowner_reply;
    }

    public Integer getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Integer reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Integer getHome_owner_id() {
        return home_owner_id;
    }

    public void setHome_owner_id(Integer home_owner_id) {
        this.home_owner_id = home_owner_id;
    }
/*
    public Integer getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Integer worker_id) {
        this.worker_id = worker_id;
    }
*/

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public String getReservation_start_time() {
        return reservation_start_time;
    }

    public void setReservation_start_time(String reservation_start_time) {
        this.reservation_start_time = reservation_start_time;
    }

    public String getReservation_end_time() {
        return reservation_end_time;
    }

    public void setReservation_end_time(String reservation_end_time) {
        this.reservation_end_time = reservation_end_time;
    }

    public String getReservation_details() {
        return reservation_details;
    }

    public void setReservation_details(String reservation_details) {
        this.reservation_details = reservation_details;
    }

    public String getSubmit_dateTime() {
        return submit_dateTime;
    }

    public void setSubmit_dateTime(String submit_dateTime) {
        this.submit_dateTime = submit_dateTime;
    }

    public String getWorker_remark() {
        return worker_remark;
    }

    public void setWorker_remark(String worker_remark) {
        this.worker_remark = worker_remark;
    }

    public String getWorker_reply() {
        return worker_reply;
    }

    public void setWorker_reply(String worker_reply) {
        this.worker_reply = worker_reply;
    }

    public String getHomeowner_reply() {
        return homeowner_reply;
    }

    public void setHomeowner_reply(String homeowner_reply) {
        this.homeowner_reply = homeowner_reply;
    }
}
