package com.example.libback.controller;

import com.example.libback.dto.ReportMetricsDto;
import com.example.libback.model.Accession;
import com.example.libback.model.Loan;
import com.example.libback.repository.AccessionRepository;  
import com.example.libback.repository.LoanRepository;
import com.example.libback.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ReportController {

    private final ReportService reportService;
    private final LoanRepository loanRepository;
    private final AccessionRepository accessionRepository; // 1. ADD THIS FIELD

    // 2. ADD ACCESSIONREPOSITORY TO THE CONSTRUCTOR PARAMETERS
    public ReportController(ReportService reportService, 
                            LoanRepository loanRepository, 
                            AccessionRepository accessionRepository) {
        this.reportService = reportService;
        this.loanRepository = loanRepository;
        this.accessionRepository = accessionRepository; // 3. ASSIGN THE INJECTED BEAN
    }

    @GetMapping("/reports")
    public String showReportsPage(Model model) {
        ReportMetricsDto metrics = reportService.generateSystemMetrics();
        model.addAttribute("metrics", metrics);

        List<Loan> overdueLoans = loanRepository.findTop5ByOrderByLoanIdDesc(); 
        model.addAttribute("overdueLoans", overdueLoans);

        return "reports";
    }

    // Your export endpoint can now safely use the repository!
    @GetMapping("/reports/export-inventory")
    public void exportInventoryManifest(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=inventory_audit_manifest.csv");

        List<Accession> allCopies = accessionRepository.findAll();

        PrintWriter writer = response.getWriter();
        writer.println("Accession ID,Barcode,ISBN,Title,Shelf Location,Availability Status");

        for (Accession copy : allCopies) {
            writer.println(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                copy.getAccessionId(),
                copy.getBarcode(),
                copy.getItem().getIsbn(),
                copy.getItem().getTitle().replace("\"", "\"\""),
                copy.getShelfLocation(),
                copy.getAvailabilityStatus().name()
            ));
        }
        
        writer.flush();
        writer.close();
    }
}