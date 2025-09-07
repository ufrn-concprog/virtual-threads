# Benchmarking Processes and Threads in Java

[![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
![Build](https://img.shields.io/badge/build-manual-lightgrey)
[![Docs](https://img.shields.io/badge/docs-Javadoc-green)](./doc/index.html)

This benchmark measures the performance of creating platform and virtual threads in Java. The goal is to demonstrate that creating virtual threads is more efficient than creating platform threads, and that this process is not limited by the operating system's capacity.

This project is part of the **Concurrent Programming** module at the [Federal University of Rio Grande do Norte (UFRN)](https://www.ufrn.br), Natal, Brazil.

## 📃 Description

This benchmark comprises two parts:

1. **Measurement of the overhead in terms of time to create 1,000 platform and virtual threads.** This number is currently fixed and hard-coded, but it can be adjusted or even provided as an input to the program. All the threads execute the very same task, which does almost nothing to allow for focusing on the overhead. The benchmark currently runs for only one, so that future work involves modifying the implementation to support multiple runs (at least 20) for empirical validity, and recording the mean and standard deviation.
2. **Exploration of how many platform and virtual threads can be created subject to the available computing resources.** This assessment is continually executed by increasing the number of threads to create with a 500-unit step (i.e., 500, 1,000, 1,500, and so on) until a [`java.lang.OutOfMemoryError`](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/lang/OutOfMemoryError.html) exception is raised indicated that the JVM exceeded memory limits.

---

## 📂 Repository Structure

```
.
├── src/                                # Source code
│   ├── benchmark/                      # Benchmark to measure the overhead of creating threads
│   │   ├── BenchmarkCreate.java        # Class with the implementation of the two benchmarks
│   │   ├── Main.java                   # Main driver class of the benchmark
│   ├── limits/                         # Benchmark to explore the limits of creating threads
│   │   ├── BenchmarkLimits.java        # Class with the implementation of the two benchmarks
│   │   ├── MainPlatformThreads.java    # Main driver class of the benchmark with platform threads
│   │   ├── MainVirtualThreads.java     # Main driver class of the benchmark with virtual threads
└── README.md
```

---

## 🚀 Getting Started

Ensure these prerequisites are followed:

- Java 11+ (works with any modern JDK)
- A terminal or IDE (IntelliJ, Eclipse, VS Code, etc.)

## 🤝 Contributing

Contributions are welcome! Fork this repository and submit a pull request 🚀

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).
