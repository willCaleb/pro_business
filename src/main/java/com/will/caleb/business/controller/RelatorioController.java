package com.will.caleb.business.controller;

import com.will.caleb.business.model.entity.FinancialExpense;
import com.will.caleb.business.pdf.PdfReportGenerator;
import com.will.caleb.business.service.FinancialExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/relatorios")
public class RelatorioController {

    private final PdfReportGenerator pdfGenerator;
    private final FinancialExpenseService expenseService; // Exemplo de serviço

    @GetMapping("/vendas")
    public ResponseEntity<byte[]> gerarRelatorioVendas(Pageable pageable) {
        Page<FinancialExpense> list = expenseService.list(pageable);

        byte[] pdf = pdfGenerator.generateReport(
                list.toList(),
                FinancialExpense.class,
                "Vendas de Produtos",
                "Empresa XPTO",
                "Junho 2025"
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-vendas.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
