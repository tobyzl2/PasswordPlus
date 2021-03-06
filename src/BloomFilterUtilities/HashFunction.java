package BloomFilterUtilities;

import org.apache.commons.codec.digest.MurmurHash3;

/**
 * A hash function class to generate k-independent hash functions.
 */
public class HashFunction {
    private int M;
    private int seed;

    /**
     * Constructor that initializes hash function with a seed.
     * @param M max value of hashcode
     * @param seed unique hash function seed
     */
    public HashFunction(int M, int seed) throws IllegalArgumentException {
        if (M <= 0) {
            throw new IllegalArgumentException("M must be positive.");
        }

        this.M = M;
        this.seed = seed;
    }

    /**
     * Function used to hash data based on MurmurHash3.
     * @param data data to be hashed
     * @return hashcode
     */
    public int hash(byte[] data) {
        int hash = MurmurHash3.hash32x86(data, 0, data.length, this.seed);
        hash = (hash & 0x7fffffff) % this.M;    // handle negative values and shrink range to M

        return hash;
    }
}
