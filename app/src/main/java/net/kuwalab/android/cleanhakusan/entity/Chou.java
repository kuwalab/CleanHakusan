package net.kuwalab.android.cleanhakusan.entity;

public class Chou {
    /** id */
    private Integer id;
    /** 町名 */
    private String chouName;
    /** ゴミ回収番号 */
    private Integer trashNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChouName() {
        return chouName;
    }

    public void setChouName(String chouName) {
        this.chouName = chouName;
    }

    public Integer getTrashNo() {
        return trashNo;
    }

    public void setTrashNo(Integer trashNo) {
        this.trashNo = trashNo;
    }
}
