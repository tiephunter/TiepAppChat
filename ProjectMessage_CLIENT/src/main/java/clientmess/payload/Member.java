package clientmess.payload;

public class Member {
    private int idMember;
    private String memberName;

    public Member() {
    }

    public Member(int idMember, String memberName) {
        this.idMember = idMember;
        this.memberName = memberName;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
