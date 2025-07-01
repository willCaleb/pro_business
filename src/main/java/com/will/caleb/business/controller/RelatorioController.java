package com.will.caleb.business.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final PdfReportGenerator pdfGenerator;
    private final VendaService vendaService; // Exemplo de serviço

    public RelatorioController(PdfReportGenerator pdfGenerator, VendaService vendaService) {
        this.pdfGenerator = pdfGenerator;
        this.vendaService = vendaService;
    }

    @GetMapping("/vendas")
    public ResponseEntity<byte[]> gerarRelatorioVendas() {
        List<Venda> vendas = vendaService.buscarTodas();

        byte[] pdf = pdfGenerator.generateReport(
                vendas,
                Venda.class,
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
