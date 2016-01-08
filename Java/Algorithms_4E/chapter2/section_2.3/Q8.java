/*
 * ~c*N*lg(N):
 *      c < 1.39
 */

public class Q8 {
    public static void main (String[] args) {
        int N = Integer.parseInt(args[0]);
        for (int i=1; i<=N; i++) {
            String[] a = new String[i];
            for (int j=0; j<i; j++) {
                a[j] = args[1];
            }
            Quick.sort(a);
            System.out.println(a.length + " " + Quick.count() + " " + 1.39*a.length*Math.log(a.length));
        }
    }
}

class Quick {
    private static int count;
    public static void sort(Comparable[] a) {
        count = 0;
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo ) {
            return;
        }
        int j = partion(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static int partion(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static int count() {
        return count;
    }

    public static boolean less(Comparable v, Comparable w) {
        count ++;
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (int i=0; i<a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i=1; i<a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    public static void main (String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
