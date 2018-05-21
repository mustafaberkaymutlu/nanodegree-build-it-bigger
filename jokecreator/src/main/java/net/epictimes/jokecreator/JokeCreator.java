package net.epictimes.jokecreator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Mustafa Berkay Mutlu on 21.05.18.
 */
public class JokeCreator {
    private static final String[] JOKES = new String[]{
            "Jon Skeet is immutable. If something's going to change, it's going to have to be the rest of the universe.",
            "Jon Skeet's addition operator doesn't commute; it teleports to where he needs it to be.",
            "Anonymous methods and anonymous types are really all called Jon Skeet. They just don't like to boast.",
            "Jon Skeet's code doesn't follow a coding convention. It is the coding convention.",
            "Jon Skeet doesn't have performance bottlenecks. He just makes the universe wait its turn.",
            "Users don't mark Jon Skeet's answers as accepted. The universe accepts them out of a sense of truth and justice.",
            "Jon Skeet can divide by zero.",
            "Jon Skeet coded his last project entirely in Microsoft Paint, just for the challenge.",
            "When Jon Skeet's code fails to compile the compiler apologises.",
            "Jon Skeet does not use revision control software. None of his code has ever needed revision.",
            "When you search for \"guru\" on Google it says \"Did you mean Jon Skeet?\"",
            "Jon Skeet is the traveling salesman. Only he knows the shortest route.",
            "Jon Skeet took the red pill and the blue pill, and can phase-shift in and out of the Matrix at will.",
            "Jon Skeet has root access to your system.",
            "The Dining Philosophers wait while Jon Skeet eats.",
            "Jon Skeet saved the Princess.",
            "Jon Skeet does not run his programs. He just whispers \"you better run\". And it runs."
    };

    public String createJoke() {
        final int randomNum = ThreadLocalRandom.current().nextInt(0, JOKES.length);
        return JOKES[randomNum];
    }
}
