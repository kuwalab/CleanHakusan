package net.kuwalab.android.cleanhakusan.entity;

public class Trash {
    /** ID */
    private int id;
    /** ゴミ回収番号 */
    private int trashNo;
    /** ゴミ回収タイプ */
    private int trashType;
    /** 年 */
    private int year;
    /** 月 */
    private int month;
    /** 日 */
    private int date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrashNo() {
        return trashNo;
    }

    public void setTrashNo(int trashNo) {
        this.trashNo = trashNo;
    }

    public int getTrashType() {
        return trashType;
    }

    public void setTrashType(int trashType) {
        this.trashType = trashType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
