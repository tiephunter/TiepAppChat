package clientmess.payload;

import java.util.ArrayList;

public class CreateGroupRequest extends BasePayload {
    private ArrayList<Member> membersList;

    public CreateGroupRequest() {
    }

    public CreateGroupRequest(int action, ArrayList<Member> membersList) {
        super(action);
        this.membersList = membersList;
    }

    public ArrayList<Member> getMembersList() {
        return membersList;
    }

    public void setMembersList(ArrayList<Member> membersList) {
        this.membersList = membersList;
    }
}
