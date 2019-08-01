package com;


import com.StressTest.Benchmark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Benchmark benchmark = new Benchmark();

    public static void main(String[] args) throws IOException {

        while(true) {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            int useCase = Integer.valueOf(reader.readLine());

            benchmark.useCase(useCase);
        }
    }
}
