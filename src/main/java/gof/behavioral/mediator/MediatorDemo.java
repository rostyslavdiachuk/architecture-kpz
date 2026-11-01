package gof.behavioral.mediator;

import common.Console;

import java.util.List;

public class MediatorDemo {

    public static void main(String[] args) {
        Console.header("GoF / Mediator -- every party member talks to every other directly");

        Tank tank = new Tank();
        Healer healer = new Healer();
        Mage mage = new Mage();
        Rogue rogue = new Rogue();

        Console.step("Introducing everyone: 9 references to wire by hand, and nobody has met the rogue");
        tank.meet(healer, mage);
        healer.meet(tank, mage);
        mage.meet(tank, healer);
        rogue.meet(tank, healer, mage);

        Console.step("The boss winds up its fire breath, and the tank warns the party");
        tank.warnParty();

        Console.step("Fire breath!");
        for (PartyMember member : List.of(tank, healer, mage, rogue)) {
            if (member.inTheFire()) {
                Console.fail(member.name() + " burns -- nobody told them, the warning code "
                        + "was written before they joined");
            } else {
                Console.ok(member.name() + " is safe");
            }
        }

        Console.note("Refactor task: add a Raid mediator; members know only the raid and call "
                + "raid.warnParty(); the raid knows all members.");
    }
}
