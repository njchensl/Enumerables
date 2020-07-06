package me.nanjingchj.test.math.enumerability;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ImmutableStreamSet<BigInteger> integers = () -> Stream.generate(new Supplier<BigInteger>() {
            private boolean positive = true;
            private BigInteger nextNumber = BigInteger.ZERO;

            @Override
            public BigInteger get() {
                BigInteger next = nextNumber;
                if (positive) {
                    nextNumber = nextNumber.negate().add(BigInteger.ONE);
                } else {
                    nextNumber = nextNumber.negate();
                }
                positive = !positive;
                return next;
            }
        });
        ImmutableStreamSet<BigDecimal> rationals = () -> Stream.generate(new Supplier<BigDecimal>() {
            private boolean positive = true;
            private BigDecimal nextNumber = BigDecimal.ZERO;
            private BigInteger sum = BigInteger.TWO;
            private BigInteger numerator = BigInteger.ONE;

            @Override
            public BigDecimal get() {
                BigDecimal next = nextNumber;
                if (positive) {
                    nextNumber = nextNumber.negate();
                    if (sum.subtract(numerator).compareTo(BigInteger.ONE) == 0) {
                        // next sum
                        sum = sum.add(BigInteger.ONE);
                        numerator = BigInteger.ONE;
                    } else {
                        numerator = numerator.add(BigInteger.ONE);
                    }
                    nextNumber = new BigDecimal(numerator).divide(new BigDecimal(sum.subtract(numerator)), new MathContext(8));
                } else {
                    nextNumber = nextNumber.negate();
                }
                positive = !positive;
                return next;
            }
        });
        for (; ; ) {
            System.out.println(rationals.contains(BigDecimal.valueOf(new Random().nextInt() / 10000000).divide(BigDecimal.valueOf(new Random().nextInt() / 1000000), new MathContext(8))));
        }
    }
}
