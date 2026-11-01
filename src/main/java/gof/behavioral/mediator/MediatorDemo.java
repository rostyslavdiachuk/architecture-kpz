package gof.behavioral.mediator;

import common.Console;

import java.util.List;

public class MediatorDemo {

    public static void main(String[] args) {
        Console.header("GoF / Mediator -- party members talk only to the raid");

        Raid raid = new Raid();
        Tank tank = new Tank(raid);
        Healer healer = new Healer(raid);
        Mage mage = new Mage(raid);
        Rogue rogue = new Rogue(raid);

        Console.step("The boss winds up its fire breath, and the tank warns the party");
        tank.warnParty();

        Console.step("Fire breath!");
        for (PartyMember member : List.of(tank, healer, mage, rogue)) {
            if (member.inTheFire()) {
                Console.fail(member.name() + " burns");
            } else {
                Console.ok(member.name() + " is safe");
            }
        }

        Console.note("4 members, 4 links to the raid, 0 links to each other. A new member joins "
                + "the raid, and no other class changes.");
    }
}
