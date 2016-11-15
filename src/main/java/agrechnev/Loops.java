package agrechnev;

/**
 * Check if a unidirectional linked list (chain of linked nodes) is looped on itself
 * Note that there can be a lead-in linear chain before the loop starts,
 * a shape like  the number '9'
 */
public class Loops {

    static class Node {
        // No data in the node for simplicity, it does not matter
        public Node next;

        public Node(Node next) {
            this.next = next;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Create a linear and a looped chain
        Node linearChain = createLinearChain(1991);
        Node loopedChain = createLoopedChain(1859, 419);

        System.out.println(isLooped(linearChain));
        System.out.println(isLooped(loopedChain));

        // Now let's try a short lead-in and a long loop
        Node loopedChain2 = createLoopedChain(17, 3419);
        System.out.println(isLooped(loopedChain2));
    }

    /**
     * Check if a chain is looped into itself
     * The idea is to compare each element to the last saved marker
     * We save markers at position 100, 200, 400, 800, 1600 etc
     * Once we run out of the lead-in and into the loop,
     * And once the distance between neighboring markers exceeds the loop length
     * We find the repetition
     *
     * @param chain   The chain to check
     * @return
     */
    private static boolean isLooped(Node chain) {
        Node node = chain; // The node pointer
        Node marker = null; // The marker to compare each element to

        System.out.println("isLooped() starting");

        // Nearly infinite loop counting the nodes, starting from 1
        for (long i = 1; i > 0; i++) {
            if (node.next == null) {
                // Tail found, thus the chain is not looped
                System.out.println("Found tail at i=" + i);
                return false;
            }

            if (node == marker) {
                // Repetition of the marker means a loop
                System.out.println("Found repetition at i=" + i);
                return true;
            }

            if (i % 100 == 0 && isPowerOf2(i / 100)) {
                // Time to set a new marker
                System.out.println("Setting marker at i=" + i);
                marker = node;
            }

            node = node.next; // Go to the next node
        }
        // If reached Long.MAX_VALUE, which is practically impossible
        return false;
    }

    /**
     * Check if a long number is a power of 2 (1 included, 0 excluded)
     *
     * @param number
     * @return   true if number is a power of 2
     */

    private static boolean isPowerOf2(long number) {
        return (number > 0) && ((number & (number - 1)) == 0);
    }

    /**
     * Create a chain looped on itself
     *
     * @param leadInSize  Size of the led-in linear chain
     * @param loopSize    Size of the loop
     * @return  Looped chain
     */
    private static Node createLoopedChain(long leadInSize, long loopSize) {
        if (loopSize < 1) throw new RuntimeException("Loop must contain at least 1 element");

        // Head and tail for making the loop
        Node tail = new Node(null);
        Node head = tail;

        // Create the loop first, add loopSize-1 elements
        for (long i = 0; i < loopSize - 1; i++) {
            head = new Node(head);
        }

        // Now loop it on itself
        tail.next = head;

        // Now add the linear lead-in if needed
        for (long i = 0; i < leadInSize; i++) {
            head = new Node(head);
        }


        return head;
    }

    /**
     * Create a proper linear chain (not looped)
     *
     * @param size = number of nodes
     * @return
     */
    private static Node createLinearChain(long size) {
        Node head = null; // Head of the chain

        for (long i = 0; i < size; i++) {
            head = new Node(head);
        }

        return head;
    }


}
