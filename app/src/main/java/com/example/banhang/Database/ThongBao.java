package com.example.banhang.Database;

public class ThongBao {
    int id;
    private String title, note, start, end;
    private boolean noti;

    public ThongBao(int id, String title, String note, String start, String end, boolean noti) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.start = start;
        this.end = end;
        this.noti = noti;
    }
    public ThongBao(int id, String title, String start, String end) {
        this.id = id;

        this.title = title;
        this.start = start;
        this.end = end;
    }

    public ThongBao(int id, String title, String start, String end, boolean noti) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.noti = noti;
    }

    public ThongBao(int id, String title, String note, String start, String end) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.start = start;
        this.end = end;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isNoti() {
        return noti;
    }

    public void setNoti(boolean noti) {
        this.noti = noti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
