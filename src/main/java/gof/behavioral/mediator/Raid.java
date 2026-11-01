package gof.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Mediator: the only object that knows who is in the party.
 *
 * <p>Members talk to the raid, not to each other. A new member is one
 * {@link #join} call, and no other class changes.
 */
public class Raid {

    private final List<PartyMember> members = new ArrayList<>();

    public void join(PartyMember member) {
        members.add(member);
    }

    public void warnParty(PartyMember from) {
        for (PartyMember member : members) {
            member.moveOutOfTheFire();
        }
    }
}
