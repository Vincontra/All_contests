package CC_Starters244;

import java.io.*;
import java.util.*;
import java.math.*;

public class CarrotCollection{
    // ---------- Constants ----------
    static final int MOD = (int) 1e9 + 7;
    static final long LMOD = 1000000007L;
    static final int INF = (int) 1e9;
    static final long LINF = (long) 1e18;

    // ---------- Fast I/O ----------
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = fr.nextInt();
        while (t-- > 0) {
            solve();
        }
        out.flush();
    }

    // ---------- Solve ----------
    static void solve() {
        int n=fr.nextInt();
        int l=fr.nextInt();
        int r=fr.nextInt();
        l--;
        r--;
        int b=0;
        int c=0;
        int arr[]=new int[n];
        for (int i=0;i<n;i++){
            arr[i]=fr.nextInt();
        }

        for (int i=0;i<l;i++){
            c+=arr[i];
        }
        for (int i=r+1;i<n;i++){
            b+=arr[i];
        }
        System.out.println(Math.max(c,b));


    }

    // ---------- Math Helpers ----------
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int lcm(int a, int b) {
        return (int) ((1L * a * b) / gcd(a, b));
    }

    static long modPow(long a, long b, long mod) {
        long res = 1;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }

    static long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod);
    }

    static long[] fact, invFact;

    static void precomputeFactorials(int n, long mod) {
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % mod;
        invFact[n] = modInverse(fact[n], mod);
        for (int i = n - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % mod;
    }

    static long nCr(int n, int r, long mod) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % mod * invFact[n - r] % mod;
    }

    static long nCrPlain(int n, int r) {
        if (r > n) return 0;
        if (r > n - r) r = n - r;
        long res = 1;
        for (int i = 0; i < r; i++) {
            res *= (n - i);
            res /= (i + 1);
        }
        return res;
    }

    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (long i = 3; i * i <= n; i += 2)
            if (n % i == 0) return false;
        return true;
    }

    static boolean[] sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= n; j += i)
                    prime[j] = false;
            }
        }
        return prime;
    }

    static long totient(long n) {
        long res = n;
        for (long p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                while (n % p == 0) n /= p;
                res -= res / p;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

    static ArrayList<Long> primeFactors(long n) {
        ArrayList<Long> factors = new ArrayList<>();
        for (long p = 2; p * p <= n; p++) {
            while (n % p == 0) {
                factors.add(p);
                n /= p;
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    static ArrayList<Integer> divisors(int n) {
        ArrayList<Integer> divs = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divs.add(i);
                if (i != n / i) divs.add(n / i);
            }
        }
        Collections.sort(divs);
        return divs;
    }

    static long modMul(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }

    static long modAdd(long a, long b, long mod) {
        return (a % mod + b % mod + mod) % mod;
    }

    static long modSub(long a, long b, long mod) {
        return (a % mod - b % mod + mod) % mod;
    }

    static long modDiv(long a, long b, long mod) {
        return modMul(a, modInverse(b, mod), mod);
    }

    static long binaryGCD(long a, long b) {
        if (a == 0) return b;
        if (b == 0) return a;
        int shift = Long.numberOfTrailingZeros(a | b);
        a >>= Long.numberOfTrailingZeros(a);
        while (b != 0) {
            b >>= Long.numberOfTrailingZeros(b);
            if (a > b) {
                long tmp = a;
                a = b;
                b = tmp;
            }
            b -= a;
        }
        return a << shift;
    }

    static long fibonacci(long n, long mod) {
        if (n == 0) return 0;
        long[][] F = {{1, 1}, {1, 0}};
        long[][] res = matrixPow(F, n - 1, mod);
        return res[0][0];
    }

    // ---------- Matrix Exponentiation ----------
    static long[][] multiply(long[][] A, long[][] B, long mod) {
        int n = A.length, m = B[0].length;
        long[][] res = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    res[i][j] = (res[i][j] + A[i][k] * B[k][j]) % mod;
                }
            }
        }
        return res;
    }

    static long[][] matrixPow(long[][] base, long exp, long mod) {
        int n = base.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, base, mod);
            base = multiply(base, base, mod);
            exp >>= 1;
        }
        return res;
    }

    // ---------- Array Helpers ----------
    static int[] compress(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        HashMap<Integer, Integer> map = new HashMap<>();
        int id = 1;
        for (int x : sorted) {
            if (!map.containsKey(x)) map.put(x, id++);
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) res[i] = map.get(arr[i]);
        return res;
    }

    static void debug(int[] arr) {
        out.println(Arrays.toString(arr));
    }

    static void debug(long[] arr) {
        out.println(Arrays.toString(arr));
    }

    // ---------- Multiset (using TreeMap) ----------
    static void add(TreeMap<Integer, Integer> map, int key, int val) {
        map.put(key, map.getOrDefault(key, 0) + val);
    }

    static void remove(TreeMap<Integer, Integer> map, int key, int val) {
        int cnt = map.getOrDefault(key, 0);
        if (cnt <= val) map.remove(key);
        else map.put(key, cnt - val);
    }

    // ---------- Pair / Triple ----------
    static class Pair implements Comparable<Pair> {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair o) {
            if (this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    static class Triple implements Comparable<Triple> {
        int x, y, z;

        Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int compareTo(Triple o) {
            if (x != o.x) return x - o.x;
            if (y != o.y) return y - o.y;
            return z - o.z;
        }

        public String toString() {
            return "(" + x + "," + y + "," + z + ")";
        }
    }

    // ---------- FastReader ----------
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }

        long[] readLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) arr[i] = nextLong();
            return arr;
        }

    }

}
