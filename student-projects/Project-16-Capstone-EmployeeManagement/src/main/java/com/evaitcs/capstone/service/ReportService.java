package com.evaitcs.capstone.service;

import com.evaitcs.capstone.model.*;
import java.io.*;
import java.util.List;

/**
 * ============================================================================
 * CLASS: ReportService
 * TOPICS: File I/O, String formatting, SRP
 * ============================================================================
 *
 * Generates formatted reports and saves them to text files.
 * ============================================================================
 */
public class ReportService {

    /**
     * TODO 1: Generate a complete company report as a String
     * Format:
     * ================================
     * EMPLOYEE MANAGEMENT REPORT
     * Date: 2026-02-23
     * ================================
     * Total Employees: 25
     * Active: 22 | On Leave: 2 | Terminated: 1
     *
     * Department Breakdown:
     *   Engineering: 10
     *   Marketing: 5
     *   ...
     *
     * Payroll Summary:
     *   Monthly Payroll: $125,000.00
     *   Annual Payroll: $1,500,000.00
     *   Average Salary: $60,000.00
     *   Highest Paid: John Smith ($120,000.00)
     * ================================
     *
     * @param employees all employees
     * @param payrollService for payroll calculations
     * @return the formatted report string
     */
    public String generateReport(List<Employee> employees, PayrollService payrollService) {
        // TODO: Build the report using StringBuilder
        // StringBuilder sb = new StringBuilder();
        // sb.append("================================\n");
        // ... etc.
        return ""; // Replace this line
    }

    /**
     * TODO 2: Save a report to a text file
     * Use BufferedWriter with try-with-resources!
     *
     * @param report the report content
     * @param filePath where to save
     */
    public void saveReportToFile(String report, String filePath) {
        // TODO: Write the report string to a file using BufferedWriter
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        //     writer.write(report);
        //     System.out.println("Report saved to: " + filePath);
        // } catch (IOException e) {
        //     System.err.println("Error saving report: " + e.getMessage());
        // }
    }

    /**
     * TODO 3: Generate an employee directory (list all employees sorted by name)
     * @param employees all employees
     * @return formatted directory string
     */
    public String generateDirectory(List<Employee> employees) {
        // TODO: Sort employees by name, format as a directory listing
        return ""; // Replace this line
    }
}

