package com.Slattery.Travis;

public class Main {
    //private static File lockFile = new File("Blackjack.lock");

    public Main() {
    }

    public static void main(String[] args) {
        BlackJackGUI black =new BlackJackGUI();
        black.loadGame();
        BlackJackGUI.frame.setVisible(true);
    }

    public void setVisible() {
    }

    /*private static void LockFile(File lockFile) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(lockFile));

            try {
                objectOutputStream.writeObject(lockFile);
            } catch (Throwable var5) {
                try {
                    objectOutputStream.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            objectOutputStream.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public static void setLockFile(File lockFile) {
        Main.lockFile = lockFile;
    }*/
}