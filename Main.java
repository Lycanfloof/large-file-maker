import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Main {
    private static final long ONE_GIB = 1073741824;
    private static final long CHUNK = 134217728;
    private static final  char[] symbolArray = initializeSymbolArray();

    private static char[] initializeSymbolArray() {
        char[] symbols = new char[26 * 2 + 10];
        int index = 0;

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            symbols[index++] = ch;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            symbols[index++] = ch;
        }

        for (char ch = '0'; ch <= '9'; ch++) {
            symbols[index++] = ch;
        }

        return symbols;
    }

    public static void main(String[] args) {
        long size;

        if (args.length <= 0) {
            size = ONE_GIB;
        } else {
            size = ONE_GIB * Long.valueOf(args[0]);
        }

        try {
            createFile(size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(long size) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("unsorted_file"));

        long chunks = (long) Math.ceil(size / CHUNK);
        for (long i = 0; i < chunks; i++) {
            bw.write(createChunk());
        }

        bw.close();
    }

    private static char[] createChunk() {
        char[] chunk = new char[(int) CHUNK];
        int length = (int) (Math.random() * 12 + 4);

        for (int i = 0; i < CHUNK; i++) {
            if (length <= 0) {
                chunk[i] = '\n';
                length = (int) (Math.random() * 12 + 4);
            } else {
                int random = (int) (Math.random() * 35);
                chunk[i] = symbolArray[random];
                length--;
            }
        }

        return chunk;
    }
}