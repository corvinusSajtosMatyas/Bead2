package com.sajtos.bead2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BeadandoServerTest {
    BeadandoClient beadandoClient = null;

    @BeforeEach
    public void setUp() throws Exception {
        beadandoClient = new BeadandoClient();
    }

    @Test
    @DisplayName("Starting the server")
    public void testServerStart() {
        BeadandoServer beadandoServer = new BeadandoServer();
        beadandoServer.start(8888);
        beadandoServer.stop();
    }

    @Test
    @DisplayName("Starting the server and write hello")
    public void testServerStartWrite() {
        BeadandoServer beadandoServer = new BeadandoServer();
        beadandoServer.start(8889);

        beadandoClient.writeToServer("localhost", 8889, "Hello");

        beadandoServer.stop();
    }

    @Test
    @DisplayName("Starting the server and write hello and bello")
    public void testServerStartWrite3Times() {
        BeadandoServer beadandoServer = new BeadandoServer();
        beadandoServer.start(8887);

        beadandoClient.writeToServer("localhost", 8887, "Hello");
        beadandoClient.writeToServer("localhost", 8887, "Bello");
        beadandoClient.writeToServer("localhost", 8887, "Test");

        beadandoServer.stop();
    }

    @Test
    @DisplayName("Starting the server and write hello and bello")
    public void testServerStartWriteAtTheSameTime() {
        BeadandoServer beadandoServer = new BeadandoServer();
        beadandoServer.start(8886);

        Runnable r = new Runnable() {
            public void run() {
                String rand = String.valueOf(Math.random());
                beadandoClient.writeToServer("localhost", 8886, rand);
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

        beadandoClient.writeToServer("localhost", 8886, "T2");
        beadandoServer.stop();
    }

    @Test
    @DisplayName("Write a very long text to the server")
    public void testServerStartWriteLong() {
        BeadandoServer beadandoServer = new BeadandoServer();
        beadandoServer.start(8885);

        String text = "\n" +
                "\n" +
                "Scene: A cafe. One table is occupied by a group of Vikings with horned helmets on. A man and his wife enter.\n" +
                "\n" +
                "Man: You sit here, dear.\n" +
                "\n" +
                "Wife: All right.\n" +
                "\n" +
                "Man: (to Waitress) Morning!\n" +
                "\n" +
                "Waitress: Morning!\n" +
                "\n" +
                "Man: Well, what've you got?\n" +
                "\n" +
                "Waitress: Well, there's egg and bacon; egg sausage and bacon; egg and spam; egg bacon and spam; egg bacon sausage and spam; spam bacon sausage and spam; spam egg spam spam bacon and spam; spam sausage spam spam bacon spam tomato and spam;\n" +
                "\n" +
                "Vikings: (starting to chant) Spam spam spam spam...\n" +
                "\n" +
                "Waitress: ...spam spam spam egg and spam; spam spam spam spam spam spam baked beans spam spam spam...\n" +
                "\n" +
                "Vikings: (singing) Spam! Lovely spam! Lovely spam!\n" +
                "\n" +
                "Waitress: ...or Lobster Thermidor au Crevettes with a mornay sauce served in a Provencale manner with shallots and aubergines garnished with truffle pate, brandy and with a fried egg on top and spam.\n" +
                "\n" +
                "Wife: Have you got anything without spam?\n" +
                "\n" +
                "Waitress: Well, there's spam egg sausage and spam, that's not got much spam in it.\n" +
                "\n" +
                "Wife: I don't want ANY spam!\n" +
                "\n" +
                "Man: Why can't she have egg bacon spam and sausage?\n" +
                "\n" +
                "Wife: THAT'S got spam in it!\n" +
                "\n" +
                "Man: Hasn't got as much spam in it as spam egg sausage and spam, has it?\n" +
                "\n" +
                "Vikings: Spam spam spam spam (crescendo through next few lines)\n" +
                "\n" +
                "Wife: Could you do the egg bacon spam and sausage without the spam then?\n" +
                "\n" +
                "Waitress: Urgghh!\n" +
                "\n" +
                "Wife: What do you mean 'Urgghh'? I don't like spam!\n" +
                "\n" +
                "Vikings: Lovely spam! Wonderful spam!\n" +
                "\n" +
                "Waitress: Shut up!\n" +
                "\n" +
                "Vikings: Lovely spam! Wonderful spam!\n" +
                "\n" +
                "Waitress: Shut up! (Vikings stop) Bloody Vikings! You can't have egg bacon spam and sausage without the spam.\n" +
                "\n" +
                "Wife: (shrieks) I don't like spam!\n" +
                "\n" +
                "Man: Sshh, dear, don't cause a fuss. I'll have your spam. I love it. I'm having spam spam spam spam spam spam spam beaked beans spam spam spam and spam!\n" +
                "\n" +
                "Vikings: (singing) Spam spam spam spam. Lovely spam! Wonderful spam!\n" +
                "\n" +
                "Waitress: Shut up!! Baked beans are off.\n" +
                "\n" +
                "Man: Well could I have her spam instead of the baked beans then?\n" +
                "\n" +
                "Waitress: You mean spam spam spam spam spam spam... (but it is too late and the Vikings drown her words)\n" +
                "\n" +
                "Vikings: (singing elaborately) Spam, spam, spam, spam. Lovely spam! Wonderful spaaam! Lovely spam! Wonderful spam. Spa-a-a-a-a-a-a-am! Spa-a-a-a-a-a-a-am! Spa-a-a-a-a-a-a-am! Spa-a-a-a-a-a-a-am! Lovely spam! (Lovely spam!) Lovely spam! (Lovely spam!) Lovely spaaam! Spam, spam, spam, spaaaaam!\n" +
                "\n";

        beadandoClient.writeToServer("localhost", 8885, text);
        beadandoServer.stop();
    }
}
