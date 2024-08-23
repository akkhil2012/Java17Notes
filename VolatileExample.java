public class VolatileExample {


    public static void main(String args[]) {

        SharedResource sharedResource = new SharedResource();
        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sharedResource.setFlagTrue();
                System.out.println(" Write thread sets the flag to true.....");
            }
        });

        writer.start();


        Runnable r = new Runnable() {
            @Override
            public void run() {
                sharedResource.printIfFlagIsTrue();
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(r).start();
        }


    }


}

class SharedResource {

    volatile boolean flag = false;

    public void setFlagTrue() {
        System.out.println(" Flag set to true.....");
        flag = true;
    }

    public void printIfFlagIsTrue() {
        while (!flag) {
            //
        }

        System.out.println(" Flag is TRUE**********.....");
    }


}
