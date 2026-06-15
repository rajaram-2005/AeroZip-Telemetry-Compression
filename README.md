AeroZip Analytics: Dynamic SCADA Telemetry Compression

Overview

AeroZip is a Java-based edge-computing simulation designed to optimize Industrial IoT (IIoT) and SCADA telemetry networks. It provides dynamic, logic-driven data compression to substantially reduce network bandwidth and cloud storage loads, specifically targeting renewable energy infrastructure like wind turbines.

The Problem

Modern industrial assets generate gigabytes of high-frequency telemetry daily. Transmitting continuous, uncompressed data overloads wireless bandwidth and inflates server costs. Fixed-interval downsampling risks missing critical transient anomalies (e.g., micro-structural thermal spikes or vibration anomalies).

The Solution

AeroZip processes raw sensor data locally at the edge.

Normal Operation: It applies a heavy compression protocol, sending only lightweight "Summary Packets."

Anomaly Detection: If safety thresholds (e.g., Temperature > 75°C, Vibration > 0.85) are breached, the system bypasses compression and transmits uncompressed raw data to ensure high-fidelity diagnostic availability.

Technical Effect & Results

During a simulation run of 1,000 SCADA packets, the AeroZip logic successfully isolated anomalous events while compressing baseline data, yielding an approximate 80-85% reduction in total transmitted file size.

Files in this Repository

AeroZipSimulator.java: The core logic engine demonstrating the bandwidth reduction algorithm.

AeroZip_Architecture.pdf: Block diagrams of the data flow (upload your flowcharts here).

Author

Rajaram Kuttalingam Pillai  
Date: June 15, 2026
